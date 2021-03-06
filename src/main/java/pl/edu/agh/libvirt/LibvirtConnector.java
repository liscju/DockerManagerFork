package pl.edu.agh.libvirt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.libvirt.Connect;
import org.libvirt.ConnectAuth;
import org.libvirt.ConnectAuthDefault;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import org.libvirt.NodeInfo;
import org.libvirt.StoragePool;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

import com.sun.jna.*;

public class LibvirtConnector {

	private static final Logger log = Logger.getLogger(LibvirtConnector.class.getName());
	
    private ConnectAuth ca;
    private Connect conn;
    private String address;
	
	public LibvirtConnector(String address){
	  ca = new ConnectAuthDefault();
	  this.address=address;
	  try {
		conn = new Connect("qemu+tcp://"+address+"/system", ca, 0);
		} catch (LibvirtException e) {
			e.printStackTrace();
		}
	}
	
	
	public Map<String,String> serverInfo(){
		Map<String,String> info = new HashMap<String,String>();
		try {
			NodeInfo ni = conn.nodeInfo();
			info.put("Hostname", conn.getHostName() + " [" + ni.model+"]");
			info.put("Cores", Integer.toString(ni.cores));
			info.put("Memory", Long.toString(ni.memory));
			info.put("Running / defined domains", Integer.toString(conn.numOfDomains()) + "/" + Integer.toString(conn.numOfDefinedDomains()));
			
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
		return info;

	}
	
	public void startVm(Domain domain){
        try {
			domain.create();
			
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}

	}
	
	public void stopVm(Domain domain){
		try {
			domain.destroy();
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
	}
	
	
	public void startVm(String domains){
        try {
        	Domain domain = domainFromName(domains);
			domain.create();
			
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}

	}
	
	public void stopVm(String domains){
		try {
        	Domain domain = domainFromName(domains);
			domain.destroy();
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
	}
	
	public Domain domainFromName(String name){
		try {
			return conn.domainLookupByName(name);
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
			return null;
		}
	}
	
	public Domain domainFromID(int id){
		try {
			return conn.domainLookupByID(id);
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
			return null;
		}
	}
	
	public String[] getDefinedDomains(){
		try {
			return conn.listDefinedDomains();
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
			return null;
		}
	}
	
	public List<Domain> getAllDomains(){
		List<Domain> d = new ArrayList<Domain>();
	      try {
			for(int i : conn.listDomains())
			  {
			    d.add(conn.domainLookupByID(i));
			   
			  }
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
	    return d;
	}
	

	
	public String getDomainXML(Domain d){
		try {
			
			return d.getXMLDesc(0);
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
			return null;
		}
	}
	
	public void checkConnection(){
		try {
			if (!conn.isConnected()){
				conn = new Connect("qemu+tcp://"+address+"/system", ca, 0);
			}
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
	}
	
	public void createDiskFromXML(String xml){

		StoragePool s;
		try {
			s = conn.storagePoolLookupByName("default");
			s.storageVolCreateXML(xml, 0);

		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
	}

	public void createDomainFromXML(String domainXml) {
		try {
			conn.domainDefineXML(domainXml);
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
	}

	public String[] getDefinedStoragePoolNames() {
		String[] storagePools = new String[0];
		try {
			storagePools = conn.listDefinedStoragePools();
			return storagePools;
		} catch (LibvirtException e) {
			log.severe(e.getMessage());
		}
		return storagePools;
	}




}
















