package pl.edu.agh.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.core.DockerClientBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
}
