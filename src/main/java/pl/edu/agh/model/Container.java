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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        if (id != null ? !id.equals(container.id) : container.id != null) return false;
        if (names != null ? !names.equals(container.names) : container.names != null) return false;
        if (image != null ? !image.equals(container.image) : container.image != null) return false;
        if (status != null ? !status.equals(container.status) : container.status != null) return false;
        if (interfaces != null ? !interfaces.equals(container.interfaces) : container.interfaces != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (names != null ? names.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (interfaces != null ? interfaces.hashCode() : 0);
        return result;
    }
}
