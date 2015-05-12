package pl.edu.agh.docker;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.edu.agh.configuration.WebMVCConfigurator;
import pl.edu.agh.dao.DockerServerDAO;
import pl.edu.agh.docker.InfoItem;
import pl.edu.agh.model.DockerServer;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.command.CreateContainerResponse;



public class DockerManager {
	private DockerServerDAO dockerServerDAO;
	
	private DockerClient dockerClient;
	private String instanceAddress;
	private List<CreateContainerResponse> containers;
	private HashMap<Integer,DockerClient> dockerServers;

	public DockerManager(String address){
		instanceAddress=address;
		dockerClient=DockerClientBuilder.getInstance(instanceAddress).build();
	}
	
	
	public Info getInfo(){
		return dockerClient.infoCmd().exec();
	}
	
	public List<InfoItem> getInfoAsList(){
		List<String> tmplist;
		Info info = getInfo();
		List<InfoItem> items= new ArrayList<InfoItem>();
		try{
		items.add(new InfoItem("Containers",Integer.toString(info.getContainers())));
		items.add(new InfoItem("Images",Integer.toString(info.getImages())));
		items.add(new InfoItem("IPv4 Forwarding",info.getIPv4Forwarding()));
		items.add(new InfoItem("Kernel",info.getKernelVersion()));


		items.add(new InfoItem("Driver",info.getDriver()));

		Iterator it = info.getDriverStatuses().iterator();
		while (it.hasNext()){
			tmplist=(List<String>) it.next();
			
			items.add(new InfoItem(tmplist.get(0),tmplist.get(1)));
		}
		}catch (Exception e){
			items.add(new InfoItem("Cannot Retrieve Data","Check connection!"));
		}

		return items;
	}
	
	public List<SearchItem> searchForImage(String name){
		return dockerClient.searchImagesCmd(name).exec();
	}
	
	
	
	
	public CreateContainerResponse  createContainer(String name,String[] commands,Volume[] volumes){
		CreateContainerResponse newContainer=dockerClient.createContainerCmd(name).withCmd(commands).withVolumes(volumes).exec();
		containers.add(newContainer);
		return newContainer;
	}
	
	public void startContainer(CreateContainerResponse container){
		dockerClient.startContainerCmd(container.getId()).exec();
	}
	
	public void stopContainer(CreateContainerResponse container){
		dockerClient.stopContainerCmd(container.getId()).exec();
	}
	
	public void buildContainer(String dockerfile){
		InputStream stream = new ByteArrayInputStream(dockerfile.getBytes(Charset.forName("UTF-8")));
		dockerClient.buildImageCmd(stream).exec();
	}
	
	public void pullContainer(String container){
		dockerClient.pullImageCmd(container).withTag("latest").exec();
	}
	
	public void listImages(){
		dockerClient.listImagesCmd().exec();
	}
	

}
