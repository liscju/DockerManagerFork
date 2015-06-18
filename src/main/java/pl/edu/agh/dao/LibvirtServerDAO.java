package pl.edu.agh.dao;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pl.edu.agh.libvirt.LibvirtConnector;

@Service
public class LibvirtServerDAO {
	private String IPAddress;
	private LibvirtConnector lc;

	public LibvirtServerDAO(){
	}

	public Domain getDomain(String name){
		return lc.domainFromName(name);
	}

	public void connect(String address){
		this.IPAddress=address;
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
	
	public void detectDisconnectedLc(){
		lc.checkConnection();
	}
	
	public Map<String,String> getRunningDomainInfo(String domain_name){
		
			Map<String,String> info = new HashMap<String,String>();
			Domain d = getDomain(domain_name);
			DomainInfo i = null;
			try {
				i = d.getInfo();
			} catch (LibvirtException e) {
				e.printStackTrace();
			}
			String xml = null;
			try {
				xml = d.getXMLDesc(0);
			} catch (LibvirtException e) {
				e.printStackTrace();
			}

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document document = null;
			try {
				document = builder.parse(new InputSource(new StringReader(xml)));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Element rootElement = document.getDocumentElement();
			NodeList nl = rootElement.getElementsByTagName("graphics");
			String vncport = nl.item(0).getAttributes().getNamedItem("port").getNodeValue();
	
			info.put("Memory",Long.toString(i.memory));
			info.put("vCpus", Integer.toString(i.nrVirtCpu));
			info.put("VNCPort", vncport);
			info.put("MaxMemory", Long.toString(i.maxMem));
			try {
				info.put("MaxCpu", Integer.toString(d.getMaxVcpus()));
			} catch (LibvirtException e) {
				e.printStackTrace();
			}

			
			String id = "stopped";
			try {
				if(d.getID()!=-1){
					id=Integer.toString(d.getID());
				}
			} catch (LibvirtException e) {
				e.printStackTrace();
			}
			
			info.put("DomainID", id);
			
			return info;
			

	
		
	}

	protected String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }

        return null;
    }

	public void createDomainFromXML(String domain_xml) {
		
		lc.createDomainFromXML(domain_xml);
	}
	
	public void createDiskFromXML(String disk_xml) {
		
		lc.createDiskFromXML(disk_xml);
	}

	public String[] getDefinedStoragePoolNames() {
		return lc.getDefinedStoragePoolNames();
	}


}
