package pl.edu.agh.model;

import javax.persistence.*;

@Entity
@Table(name = "Docker_Servers")
public class DockerServer {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int serverID;

	@Column
	private String name;

	@Column
	private String address;

	public DockerServer() {
	}

	public DockerServer(int serverID,String name, String address){
		this.serverID=serverID;
		this.name=name;
		this.address=address;
	}
	
	
	public int getServerID(){
		return serverID;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}

}
