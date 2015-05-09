package pl.edu.agh.model;

public class DockerServer {
	
	private int serverID;
	private String name;
	private String address;
	
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
