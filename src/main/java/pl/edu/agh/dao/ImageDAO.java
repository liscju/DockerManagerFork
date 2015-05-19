package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Image;

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
            imagesToReturn.add( new Image(image.getId() ) );
        }
        return imagesToReturn;
    }
}
