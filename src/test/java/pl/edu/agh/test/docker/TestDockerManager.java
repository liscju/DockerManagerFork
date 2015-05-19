package pl.edu.agh.test.docker;

import java.util.List;

import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.SearchItem;

import pl.edu.agh.docker.DockerManager;
import junit.framework.Assert;

public class TestDockerManager {

	DockerManager dm = new DockerManager("http://127.0.0.1:2375");
	
	
	public void testStartup(){
		Assert.assertNotNull(dm);
		
	}
	
	public void testGetInfo(){
		Info info = dm.getInfo();
		Assert.assertNotNull(info.toString());
		Assert.assertNotNull(info.getExecutionDriver());
		Assert.assertNotNull(info.getKernelVersion());	
	}
	
	public void testSearchForImage(){
		List<SearchItem> l = dm.searchForImage("base/archlinux");
		Assert.assertEquals("base/archlinux",l.get(0).getName());
	}
	
	public void testPullContainer(){
		dm.pullContainer("base/archlinux");
		Assert.assertFalse(dm.listImages().isEmpty());	
	}
	
	public void testStartContainer(){
		CreateContainerResponse r =dm.createContainer("base/archlinux","/bin/hostname");
		Assert.assertNotNull(r);
		dm.startContainer(r);
		List<Container> c = dm.listContainers();
		Assert.assertFalse(c.isEmpty());
	}
	
}
