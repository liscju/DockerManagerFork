package pl.edu.agh.dao;

import com.github.dockerjava.api.model.SearchItem;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Image;
import pl.edu.agh.model.Task;
import pl.edu.agh.util.RunnableTask;
import pl.edu.agh.util.TaskRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ImageDAO {
    private DockerConnector dockerConnector;

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    TaskRunner taskRunner;

    public ImageDAO() {
    }

    public void connect(String address) {
        dockerConnector = new DockerConnector("http://" + address);
    }

    public List<Image> getAllImages() {
        List<com.github.dockerjava.api.model.Image> allImages = dockerConnector.getAllImages();
        List<Image> imagesToReturn = new LinkedList<Image>();
        for (com.github.dockerjava.api.model.Image image : allImages) {
            imagesToReturn.add( new Image(image.getId(), Joiner.on(":").join(image.getRepoTags()) ) );
        }
        return imagesToReturn;
    }

    public Task addImageFromDockerfile(final String name, final String content) {

        Task createContainerTask = new Task("Create Container from docker:"+name);
        final Task savedCreateContainerTask = taskDAO.saveTask(createContainerTask);
        taskRunner.runSimpleTask(savedCreateContainerTask, new RunnableTask() {
            public void run() throws Exception{
                dockerConnector.createImageFromDockerFile(name, content);
            }
        });

        return savedCreateContainerTask;
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

    public Task createImageForWar(final String image_name, final String war_name, final MultipartFile war_content) {

        Task createContainerTask = new Task("Create Container for War:"+image_name);
        final Task savedCreateContainerTask = taskDAO.saveTask(createContainerTask);
        taskRunner.runSimpleTask(savedCreateContainerTask, new RunnableTask() {
            public void run() throws Exception{
                dockerConnector.createImageForWar(image_name, war_name, war_content.getBytes());
            }
        });

        return savedCreateContainerTask;
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

    public String runImageInContainer(String imageId) {
        return dockerConnector.runImageInContainer(imageId);
    }
}
