package pl.edu.agh.model;

public class User {
    private String name;
    private String password;
    private String role;
    private int enabled;

    public User(String name, String password, String role, int enabled) {
        this.name = name;
        this.password = password;
        this.role=role;
        this.enabled=enabled;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    public String getRole(){
    	return role;
    }
    
    public int getEnabled(){
    	return enabled;
    }
}
