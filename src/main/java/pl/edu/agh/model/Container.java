package pl.edu.agh.model;

public class Container {

    private final String id;
    private final String image;
    private final String status;

    public Container(String id, String image, String status) {
        this.id = id;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }
}
