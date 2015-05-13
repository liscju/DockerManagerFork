package pl.edu.agh.model;

import javax.persistence.*;

@Entity
@Table(name = "Containers")
public class Container {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String image;
    
    @Column
    private String onServer;

    @ManyToOne
    @JoinColumn(name = "owner",referencedColumnName = "name")
    private User owner;

    public Container() {
    }

    public Container(int id, String image,User owner, String onServer) {
        this.id = id;
        this.image = image;
        this.owner = owner;
        this.onServer=onServer;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public User getOwner() {
        return owner;
    }
    
    public String getOnServer(){
    	return onServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        if (id != container.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
