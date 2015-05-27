package pl.edu.agh.dao;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.configuration.Configurator;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Container;
import pl.edu.agh.model.Task;
import pl.edu.agh.model.TaskStatus;
import pl.edu.agh.util.RunnableTask;
import pl.edu.agh.util.TaskRunner;

import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    TaskRunner taskRunner;

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
        Task stopContainerTask = new Task("StopContainer:"+containerId);
        final Task savedStopContainerTask = taskDAO.saveTask(stopContainerTask);
        taskRunner.runSimpleTask(savedStopContainerTask, new RunnableTask() {
            public void run() throws Exception{
                dockerConnector.stopContainer(containerId);
            }
        });

        return savedStopContainerTask;
    }

    public Task deleteContainer(final String containerId) {
        Task deleteContainerTask = new Task("DeleteContainer:"+containerId);
        final Task savedDeleteContainerTask = taskDAO.saveTask(deleteContainerTask);
        taskRunner.runSimpleTask(savedDeleteContainerTask, new RunnableTask() {
            public void run() throws Exception{
                dockerConnector.deleteContainer(containerId);
            }
        });

        return savedDeleteContainerTask;
    }

    public String createContainerForConsole(final String imageId) {
        String containerId = dockerConnector.createContainer(imageId);
        dockerConnector.startContainer(containerId);
        return containerId;
    }

    public String execCommand(final String containerId,final String command) {
        String commandId = dockerConnector.createCommand(containerId, command);
        InputStream inputStream = dockerConnector.execCommand(containerId, commandId);
        try {
            inputStream.read(new byte[8]); // pomijanie naglowka
            List<String> strings = IOUtils.readLines(inputStream);
            return StringUtils.join(strings,"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
