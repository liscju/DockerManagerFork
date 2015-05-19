package pl.edu.agh.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

public class DockerConnector {
    private DockerClient dockerClient;

    public DockerConnector(String dockerServerAddress) {
        dockerClient = DockerClientBuilder.getInstance(dockerServerAddress).build();
    }

    public List<Image> getAllImages() {
        return dockerClient.listImagesCmd().exec();
    }
}
