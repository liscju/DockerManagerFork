package pl.edu.agh.dao;

import java.util.List;
import java.util.Map;

import org.libvirt.Domain;
import org.springframework.stereotype.Service;

import pl.edu.agh.libvirt.LibvirtConnector;

@Service
public class LibvirtServerDAO {
	private String IPAddress;
	private LibvirtConnector lc;
	
	public LibvirtServerDAO(){
		IPAddress="192.168.56.101";
		lc = new LibvirtConnector(IPAddress);	
	}
	
	public String getIPAddress(){
		return IPAddress; 
	}
	
	public LibvirtConnector getLibvirtConnector(){
		return lc;
	}

	public Map<String,String> getServerInfo() {
		
		return lc.serverInfo();
	}

	public List<Domain> getAllDomains() {
		return lc.getAllDomains();
	}

	public String[] getDefinedDomains() {
		return lc.getDefinedDomains();
	}



}
