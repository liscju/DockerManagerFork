package pl.edu.agh.dao;

import org.springframework.stereotype.Service;
import pl.edu.agh.docker.DockerConnector;
import pl.edu.agh.model.Container;

import java.util.LinkedList;
import java.util.List;

@Service
public class ContainerDAO {
    private final DockerConnector dockerConnector;

    public ContainerDAO() {
        dockerConnector = new DockerConnector("http://192.168.0.2:2375");
    }

    public List<Container> getAllContainers() {
        List<com.github.dockerjava.api.model.Container> downloadContainers = dockerConnector.getAllContainers();
        List<Container> containerList = new LinkedList<Container>();
        for (com.github.dockerjava.api.model.Container container : downloadContainers) {
            List<String> exposedInterfaces = getExposedInterfaces(container);
            containerList.add(new Container(container.getId(),container.getImage(),container.getStatus(), exposedInterfaces) );
        }
        return containerList;
    }

    public Container getContainer(String containerId) {
        com.github.dockerjava.api.model.Container container = dockerConnector.getContainer(containerId);
        List<String> exposedInterfaces = getExposedInterfaces(container);
        return new Container(container.getId(),container.getImage(), container.getStatus(),exposedInterfaces);
    }

    private List<String> getExposedInterfaces(com.github.dockerjava.api.model.Container container) {
        com.github.dockerjava.api.model.Container.Port[] ports = container.getPorts();
        List<String> exposedInterfaces = new LinkedList<String>();
        for (com.github.dockerjava.api.model.Container.Port port : ports) {
            exposedInterfaces.add(port.getIp() + ":" + port.getPublicPort() + "->" + port.getPrivatePort() );
        }
        return exposedInterfaces;
    }

    public void stopContainer(String containerId) {
        dockerConnector.stopContainer(containerId);
    }

    public void deleteContainer(String containerId) {
        dockerConnector.deleteContainer(containerId);
    }
}
