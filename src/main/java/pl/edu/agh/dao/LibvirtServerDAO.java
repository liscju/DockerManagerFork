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
		try {
			Map<String,String> info = new HashMap<String,String>();
			Domain d = lc.domainFromName(domain_name);
			DomainInfo i =d.getInfo();
			String xml = d.getXMLDesc(0);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xml)));
			Element rootElement = document.getDocumentElement();
			NodeList nl = rootElement.getElementsByTagName("graphics");
			String vncport = nl.item(0).getAttributes().getNamedItem("port").getNodeValue();
	
			info.put("Memory",Long.toString(i.memory));
			info.put("vCpus", Integer.toString(i.nrVirtCpu));
			info.put("VNCPort", vncport);
			info.put("DomainID", Integer.toString(d.getID()));
			
			return info;
			
		} catch (LibvirtException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return null;
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
}
