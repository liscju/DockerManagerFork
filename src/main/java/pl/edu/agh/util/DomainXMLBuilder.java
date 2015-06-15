package pl.edu.agh.util;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomainXMLBuilder {

    private String name = null;
    private String memory = null;
    private String vcpu = null;
    private String emulator = null;
    private String sourceFile = null;

    public DomainXMLBuilder withName(String name) {
        if (name != null && !name.equals(""))
            this.name = name;
        return this;
    }

    public DomainXMLBuilder withMemory(int memory) {
        this.memory = Integer.toString(memory);
        return this;
    }

    public DomainXMLBuilder withVCpu(int vCpu) {
        this.vcpu = Integer.toString(vCpu);
        return this;
    }

    public DomainXMLBuilder withEmulator(String emulator) {
        if (emulator != null && !emulator.equals("")) {
            this.emulator = emulator;
        }
        return this;
    }

    public DomainXMLBuilder withSourceFile(String sourceFile) {
        if (sourceFile != null && !sourceFile.equals("")) {
            this.sourceFile = sourceFile;
        }
        return this;
    }

    public String buildXML() {
        if (name == null) {
            throw new IllegalArgumentException("You must supply name of domain");
        }

        if (memory == null) {
            throw new IllegalArgumentException("You must supply amount of memory");
        }

        if (vcpu == null) {
            throw new IllegalArgumentException("You must supply amount of vcpu of domain");
        }
        
        if (emulator == null) {
            throw new IllegalArgumentException("You must supply emulator to create domain");
        }

        if (sourceFile == null) {
            throw new IllegalArgumentException("You must supply source file of the vm");
        }

        return createXML(name,memory,vcpu,emulator,sourceFile);
    }
    
    
    public String createXML(String name,String memory,String cpu,String emulator,String source){
    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		String res = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("domain");
            rootElement.setAttribute("type", "qemu");
            doc.appendChild(rootElement);

            Element vmname = doc.createElement("name");
            vmname.appendChild(doc.createTextNode(name));
            rootElement.appendChild(vmname);

            Element uuid = doc.createElement("uuid");
            rootElement.appendChild(uuid);

            Element vmem = doc.createElement("memory");
            vmem.appendChild(doc.createTextNode(memory));
            rootElement.appendChild(vmem);

            Element vcpu = doc.createElement("vcpu");
            vcpu.appendChild(doc.createTextNode(cpu));
            rootElement.appendChild(vcpu);

            Element os = doc.createElement("os");
            rootElement.appendChild(os);

            Element ostype = doc.createElement("type");
            ostype.setAttribute("arch", "x86_64");
            ostype.setAttribute("machine", "pc");
            ostype.appendChild(doc.createTextNode("hvm"));
            os.appendChild(ostype);


            Element dev = doc.createElement("devices");
            rootElement.appendChild(dev);

            Element emu = doc.createElement("emulator");
            emu.appendChild(doc.createTextNode(emulator));
            dev.appendChild(emu);

            Element disk = doc.createElement("disk");
            disk.setAttribute("type", "file");
            disk.setAttribute("device", "disk");
            dev.appendChild(disk);

            Element sourcef = doc.createElement("source");
            sourcef.setAttribute("file", source);
            disk.appendChild(sourcef);

            Element target = doc.createElement("target");
            target.setAttribute("dev", "hda");
            target.setAttribute("bus", "ide");
            disk.appendChild(target);
            
            Element driver = doc.createElement("driver");
            driver.setAttribute("name", "qemu");
            driver.setAttribute("type", "raw");
            driver.setAttribute("cache", "none");
            disk.appendChild(driver);
          
            Element address = doc.createElement("address");
            address.setAttribute("type", "drive");
            address.setAttribute("controller", "0");
            address.setAttribute("bus", "0");
            address.setAttribute("target", "0");
            address.setAttribute("unit", "0");
            disk.appendChild(address);

            Element nic = doc.createElement("interface");
            nic.setAttribute("type", "network");
            dev.appendChild(nic);

            Element nicsource = doc.createElement("source");
            nicsource.setAttribute("network", "default");
            nic.appendChild(target);

            Element vnc = doc.createElement("graphics");
            vnc.setAttribute("type", "vnc");
            vnc.setAttribute("port","-1");
            dev.appendChild(vnc);

            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            DOMSource sourcex = new DOMSource(doc);
            trans.transform(sourcex, result);
            res=writer.toString();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return res;
    }
}
