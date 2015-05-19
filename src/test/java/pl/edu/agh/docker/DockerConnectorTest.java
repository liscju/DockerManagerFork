package pl.edu.agh.docker;

import com.github.dockerjava.api.model.Image;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DockerConnectorTest {

    @Test
    public void testGetAllImages() throws Exception {
        DockerConnector dockerConnector = new DockerConnector("http://192.168.0.2:2375");
        List<Image> allImages = dockerConnector.getAllImages();
    }
}