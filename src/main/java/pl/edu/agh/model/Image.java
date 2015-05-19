package pl.edu.agh.model;

public class Image {
    private final String id;
    private final String tag;

    public Image(String id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }
}
