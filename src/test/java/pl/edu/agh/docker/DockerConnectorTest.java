package pl.edu.agh.docker;

import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.SearchItem;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DockerConnectorTest {

    @Test
    public void testGetAllImages() throws Exception {
        DockerConnector dockerConnector = new DockerConnector("http://192.168.0.2:2375");
        List<Image> allImages = dockerConnector.getAllImages();
    }

    @Test
    public void runQuickCommand() throws Exception {
        DockerConnector dockerConnector = new DockerConnector("http://192.168.0.2:2375");
        String output = dockerConnector.runImageCommand("b1ecabaa04e0dae5ebb8f00e5bde3abab67f2f0357301bc18cbba845466a8e8e",
                "echo $PATH");
        System.out.println(output);
    }

    @Test
    public void searchImage() throws Exception {
        DockerConnector dockerConnector = new DockerConnector("http://192.168.0.2:2375");
        List<SearchItem> searchResult = dockerConnector.getImage("b1ecabaa04e0dae5ebb8f00e5bde3abab67f2f0357301bc18cbba845466a8e8e");
        System.out.println(searchResult);
    }
}