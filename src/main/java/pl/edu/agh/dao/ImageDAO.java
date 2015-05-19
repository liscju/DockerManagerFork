package pl.edu.agh.dao;

import com.github.dockerjava.api.model.SearchItem;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Image;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public Image getImage(String imageId) {
        com.github.dockerjava.api.model.Image image = dockerConnector.getImage(imageId);
        return new Image(image.getId(),Joiner.on(":").join(image.getRepoTags()) );
    }

    public void createImageForWar(String image_name,byte[] war_content) {
        try {
            dockerConnector.createImageForWar(image_name,war_content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,String> searchForImageByName(String name){
        List<SearchItem> searchItems = dockerConnector.searchForImageByName(name);
        Map<String,String> foundImages = new HashMap<String, String>();
        for (SearchItem searchItem : searchItems) {
            foundImages.put(searchItem.getName(),searchItem.getDescription());
        }
        return foundImages;
    }

    public void pullImage(String imageName) {
        dockerConnector.pullImage(imageName);
    }

}
