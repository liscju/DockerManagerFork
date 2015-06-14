package pl.edu.agh.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomainXMLBuilder {
    private final String DOMAIN_XML_TEMPLATE =
            "<domain type='qemu'>\n" +
            "  <name>%domain_name%</name>\n" +
            "  <uuid></uuid>\n" +
            "  <memory>%domain_memory%</memory>\n" +
            "  <vcpu>%domain_vcpu%</vcpu>\n" +
            "  <os>\n" +
            "    <type arch='i686' machine='pc'>hvm</type>\n" +
            "  </os>\n" +
            "  <devices>\n" +
            "    <emulator>%domain_emulator%</emulator>\n" +
            "    <disk type='file' device='disk'>\n" +
            "      <source file='%domain_sourcefile%'/>\n" +
            "      <target dev='hda'/>\n" +
            "    </disk>\n" +
            "    <interface type='network'>\n" +
            "      <source network='default'/>\n" +
            "    </interface>\n" +
            "    <graphics type='vnc' port='-1'/>\n" +
            "  </devices>\n" +
            "</domain>";

    private final String NAME_PATTERN = "%domain_name%";
    private final String MEMORY_PATTERN = "%domain_memory%";
    private final String VPU_PATTERN = "%domain_vcpu%";
    private final String EMULATOR_PATTERN = "%domain_emulator%";
    private final String SOURCEFILE_PATTERN = "%domain_sourcefile%";

    private String name = null;
    private String memory = "100000";
    private String vcpu = "1";
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
        if (name == null || emulator == null || sourceFile == null) {
            throw new IllegalArgumentException("You must supply name,emulator and source file");
        }

        String generatedXML = new String(DOMAIN_XML_TEMPLATE);
        generatedXML = generatedXML.replaceFirst(NAME_PATTERN, name);
        generatedXML = generatedXML.replaceFirst(MEMORY_PATTERN,memory);
        generatedXML = generatedXML.replaceFirst(VPU_PATTERN,vcpu);
        generatedXML = generatedXML.replaceFirst(EMULATOR_PATTERN,emulator);
        generatedXML = generatedXML.replaceFirst(SOURCEFILE_PATTERN, sourceFile);

        return generatedXML;
    }
    
    
    public String createXML(String name,String memory,String cpu,String emulator,String source) throws ParserConfigurationException{
    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
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
		os.appendChild(vcpu);
		
		
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
		disk.appendChild(target);
		
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
		
		
		return doc.toString();
    }
}
