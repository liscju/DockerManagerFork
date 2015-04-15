package pl.edu.agh.model;

public class Container {
    private int id;
    private String image;

    public Container(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
