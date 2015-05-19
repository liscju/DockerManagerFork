package pl.edu.agh.dao;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Image;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImageDAO {

    private final DockerConnector dockerConnector;

    public ImageDAO() {
        dockerConnector = new DockerConnector("http://192.168.0.2:2375");
    }

    public List<Image> getAllImages() {
        List<com.github.dockerjava.api.model.Image> allImages = dockerConnector.getAllImages();
        List<Image> imagesToReturn = new LinkedList<Image>();
        for (com.github.dockerjava.api.model.Image image : allImages) {
            imagesToReturn.add( new Image(image.getId(), Joiner.on(":").join(image.getRepoTags()) ) );
        }
        return imagesToReturn;
    }

    public void addImageFromDockerfile(String name,String content) {
        try {
            dockerConnector.createImageFromDockerFile(name,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String runQuickCommandInImage(String imageId, String command) {
        try {
            return dockerConnector.runImageCommand(imageId,command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
