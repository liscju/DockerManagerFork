package pl.edu.agh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.configuration.Configurator;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.Task;
import pl.edu.agh.model.TaskStatus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class ContainerDAO {
    private DockerConnector dockerConnector;

    @Autowired
    Configurator configurator;

    @Autowired
    TaskDAO taskDAO;

    public ContainerDAO() {
    }

    public void connect(String address) {
        dockerConnector = new DockerConnector("http://" + address);
    }

    public List<Container> getAllContainers() {
        List<com.github.dockerjava.api.model.Container> downloadContainers = dockerConnector.getAllContainers();
        List<Container> containerList = new LinkedList<Container>();
        for (com.github.dockerjava.api.model.Container container : downloadContainers) {
            List<String> exposedInterfaces = getExposedInterfaces(container);
            containerList.add(new Container(container.getId(), Arrays.asList(container.getNames()), container.getImage(),container.getStatus(), exposedInterfaces) );
        }
        return containerList;
    }

    public Container getContainer(String containerId) {
        com.github.dockerjava.api.model.Container container = dockerConnector.getContainer(containerId);
        List<String> exposedInterfaces = getExposedInterfaces(container);
        return new Container(container.getId(),Arrays.asList(container.getNames()),container.getImage(), container.getStatus(),exposedInterfaces);
    }

    private List<String> getExposedInterfaces(com.github.dockerjava.api.model.Container container) {
        com.github.dockerjava.api.model.Container.Port[] ports = container.getPorts();
        List<String> exposedInterfaces = new LinkedList<String>();
        for (com.github.dockerjava.api.model.Container.Port port : ports) {
            exposedInterfaces.add(configurator.getAddress() + " with addr:" + port.getPublicPort() + "->" + port.getPrivatePort() );
        }
        return exposedInterfaces;
    }

    public Task stopContainer(final String containerId) {
        Task stopContainerTask = new Task("StopContainer:"+containerId, TaskStatus.INQUEUE);
        final Task savedStopContainerTask = taskDAO.saveTask(stopContainerTask);
        final TaskDAO lastTaskDAO = taskDAO;
        Runnable stopJob = new Runnable() {
            public void run() {
                dockerConnector.stopContainer(containerId);
                Task closedStopContainerTask = new Task(savedStopContainerTask.getProperties(),
                                                        TaskStatus.SUCCESS_END);
                lastTaskDAO.updateTask(closedStopContainerTask);
            }
        };

        Thread commandThread = new Thread(stopJob);
        commandThread.start();

        return savedStopContainerTask;
    }

    public void deleteContainer(String containerId) {
        dockerConnector.deleteContainer(containerId);
    }
}
