package pl.edu.agh.model;

import java.util.List;

public class Container {

    private final String id;
    private final List<String> names;
    private final String image;
    private final String status;
    private final List<String> interfaces;

    public Container(String id, List<String> names, String image, String status, List<String> exposedInterfaces) {
        this.id = id;
        this.image = image;
        this.status = status;
        this.interfaces = exposedInterfaces;
        this.names = names;
    }

    public String getId() {
        return id;
    }

    public List<String> getNames() {
        return names;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }
}
