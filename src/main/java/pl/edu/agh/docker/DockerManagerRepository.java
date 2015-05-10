package pl.edu.agh.docker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;




import pl.edu.agh.dao.DockerServerDAO;
import pl.edu.agh.model.DockerServer;

//@Configuration
//@ComponentScan(basePackages = "pl.edu.agh")
public class DockerManagerRepository {
	
	private DockerServerDAO dockerServerDAO;
	private HashMap<Integer,DockerManager> dockerServers;
	
	//@Bean(name="dockerServers")
	public HashMap<Integer,DockerManager> getDockerServers(){
		return dockerServers;
	}
	
	

	public  DockerManagerRepository(){
		scanRepository();
	}
	
	public void scanRepository(){
		try{
			List<DockerServer> servers=dockerServerDAO.getDockerServers();
			Iterator it =servers.listIterator();
			DockerServer tmp;
			while(it.hasNext()){
				tmp=(DockerServer) it.next();
				dockerServers.put(tmp.getServerID(), new DockerManager(tmp.getAddress()) );
			}
		}catch (Exception e){
			
		}

	}
	

}
