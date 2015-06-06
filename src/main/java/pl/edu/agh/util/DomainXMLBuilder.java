package pl.edu.agh.util;

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
}
