package pl.edu.agh.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.core.DockerClientBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DockerConnector {
    private DockerClient dockerClient;

    public DockerConnector(String dockerServerAddress) {
        dockerClient = DockerClientBuilder.getInstance(dockerServerAddress).build();
    }

    public List<Image> getAllImages() {
        return dockerClient.listImagesCmd().exec();
    }

    public Image getImage(final String imageId) {
        List<Image> allImages = getAllImages();
        Image foundImage = null;
        for (Image image : allImages) {
            if (image.getId().equals(imageId)) {
                foundImage = image;
            }
        }
        return foundImage;
    }

    public void pullImage(String imageName){
        dockerClient.pullImageCmd(imageName).exec();
    }

    public List<SearchItem> searchForImageByName(String name){
        return dockerClient.searchImagesCmd(name).exec();
    }

    public void createImageFromDockerFile(String name,String content) throws IOException {
        // Znowu mega obejscie.... , dockerClient.buildImageCmd oczekuje jako parametru
        // albo InputStream ktory bedzie spakowany gzipem,zipem
        // zobacz https://docs.docker.com/reference/api/docker_remote_api_v1.18/
        // albo katalogu. Z inputstreamem probowalem, nie szlo
        // nie mam cierpliwosci wiec zrobilem obejscie - ale ale wyglada ze dziala
        Path dockerManagerDir = Files.createTempDirectory("dockerManagerDir");
        File dockerFile = new File(dockerManagerDir.toString(),"Dockerfile");

        try {
            PrintWriter printWriter = new PrintWriter(dockerFile);
            printWriter.print(content);
            printWriter.close();

            dockerClient
                    .buildImageCmd(new File(dockerManagerDir.toString() ) )
                    .withNoCache()
                    .withTag(name)
                    .exec()
                    .close();
        } finally {
            dockerFile.delete();
            new File(dockerManagerDir.toString() ).delete();
        }
    }

    public String runImageCommand(String imageId, String command) throws IOException {
        CreateContainerResponse containerResponse = dockerClient
                .createContainerCmd(imageId)
                .withAttachStdin(true)
                .withTty(true)
                .exec();

        String containerId = containerResponse.getId();
        dockerClient.startContainerCmd(containerId).exec();
        ExecCreateCmdResponse checkFileCmdCreateResponse = dockerClient.execCreateCmd(containerId)
                .withAttachStdout()
                .withAttachStderr()
                .withCmd(command.split(" "))
                .exec();

        InputStream response1 = dockerClient.execStartCmd(containerId)
                .withExecId(checkFileCmdCreateResponse.getId())
                .exec();

        return IOUtils.toString(response1);
    }

    public void createImageForWar(String name, String war_name, byte[] war_content) throws IOException{
        Path dockerManagerDir = Files.createTempDirectory("dockerManagerDir");
        File dockerFile = new File(dockerManagerDir.toString(),"Dockerfile");
        File webapps_dir = new File(dockerManagerDir.toString(), "webapps");
        webapps_dir.mkdir();
        File war_file = new File(webapps_dir.toString(),war_name);

        String docker_content =
                "FROM ubuntu:14.04\n" +
                "MAINTAINER DockerManager <docker@example.com>\n" +
                "RUN apt-get -yqq update\n" +
                "RUN apt-get -yqq install tomcat7 default-jdk\n" +
                "ENV CATALINA_HOME /usr/share/tomcat7\n" +
                "ENV CATALINA_BASE /var/lib/tomcat7\n" +
                "ENV CATALINA_PID /var/run/tomcat7.pid\n" +
                "ENV CATALINA_SH /usr/share/tomcat7/bin/catalina.sh\n" +
                "ENV CATALINA_TMPDIR /tmp/tomcat7-tomcat7-tmp\n" +
                "RUN mkdir -p $CATALINA_TMPDIR\n" +
                "ADD [\"webapps\",\"/var/lib/tomcat7/webapps/\"]\n" +
                "EXPOSE 8080\n" +
                "ENTRYPOINT [\"/usr/share/tomcat7/bin/catalina.sh\",\"run\"]";

        PrintWriter printWriter = new PrintWriter(dockerFile);
        printWriter.print(docker_content);
        printWriter.close();

        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(war_file));
        stream.write(war_content);
        stream.close();

        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put(dockerFile.getAbsolutePath(),"Dockerfile");
        mapping.put(war_file.getAbsolutePath(), "webapps\\" + war_name);
        File tarFile = File.createTempFile("dockermanagerdir",".tar");
        CompressTarGz.compress(tarFile.getAbsolutePath(), mapping);

        dockerClient
                .buildImageCmd(new FileInputStream(tarFile))
                .withNoCache()
                .withTag(name)
                .exec()
                .close();

        new File(dockerManagerDir.toString() ).delete();
    }

    public List<Container> getAllContainers() {
        return dockerClient
                .listContainersCmd()
                .withShowAll(true)
                .exec();
    }

    public Container getContainer(String containerId) {
        List<Container> allContainers = getAllContainers();
        Container foundContainer = null;
        for (Container container : allContainers) {
            if (container.getId().equals(containerId)) {
                foundContainer = container;
            }
        }
        return foundContainer;
    }

    public String runImageInContainer(String imageId) {
        CreateContainerResponse createContainerResponse = dockerClient
                .createContainerCmd(imageId)
                .exec();

        dockerClient
                .startContainerCmd(createContainerResponse.getId())
                .withPublishAllPorts(true)
                .exec();

        return createContainerResponse.getId();
    }
}
