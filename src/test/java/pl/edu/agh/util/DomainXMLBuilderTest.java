package pl.edu.agh.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DomainXMLBuilderTest {

    private final static String ANY_DOMAIN = "DOMAIN";
    private final static int ANY_MEMORY = 200;
    private final static int ANY_VCPU = 1;
    private final static String ANY_EMULATOR = "/usr/bin/quemu";
    private final static String ANY_SOURCE = "/home/images/REact.vmdk";

    @Test
    public void testShouldThrowExceptionWhenUserNotSuppliedName() throws Exception {
        DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();
        try {
            String xml = domainXMLBuilder.buildXML();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("You must supply name of domain",e.getMessage());
        }
    }

    @Test
    public void testShouldThrowExceptionWhenUserNotSuppliedMemory() throws Exception {
        DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();
        try {
            String xml = domainXMLBuilder
                    .withName(ANY_DOMAIN)
                    .buildXML();

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("You must supply amount of memory",e.getMessage());
        }
    }

    @Test
    public void testShouldThrowExceptionWhenUserNotSuppliedVcpu() throws Exception {
        DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();
        try {
            String xml = domainXMLBuilder
                    .withName(ANY_DOMAIN)
                    .withMemory(ANY_MEMORY)
                    .buildXML();

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("You must supply amount of vcpu of domain",e.getMessage());
        }
    }

    @Test
    public void testShouldThrowExceptionWhenUserNotSuppliedEmulator() throws Exception {
        DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();
        try {
            String xml = domainXMLBuilder
                    .withName(ANY_DOMAIN)
                    .withMemory(ANY_MEMORY)
                    .withVCpu(ANY_VCPU)
                    .buildXML();

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("You must supply emulator to create domain",e.getMessage());
        }
    }

    @Test
    public void testShouldThrowExceptionWhenUserNotSuppliedSourceFile() throws Exception {
        DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();
        try {
            String xml = domainXMLBuilder
                    .withName(ANY_DOMAIN)
                    .withMemory(ANY_MEMORY)
                    .withVCpu(ANY_VCPU)
                    .withEmulator(ANY_EMULATOR)
                    .buildXML();

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("You must supply source file of the vm",e.getMessage());
        }
    }

    @Test
    public void testForSimpleTestCase() throws Exception {
        DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();
        String domainXML = domainXMLBuilder
                .withName("ReactOS-on-QEMU")
                .withMemory(100000)
                .withVCpu(1)
                .withEmulator("/usr/bin/qemu-system-x86_64")
                .withSourceFile("/home/liscju/LibVirtExercises/images/ReactOS.vmdk")
                .buildXML();

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                        "<domain type=\"qemu\">" +
                            "<name>ReactOS-on-QEMU</name>" +
                            "<uuid/>" +
                            "<memory>100000</memory>" +
                            "<vcpu>1</vcpu>" +
                            "<os>" +
                                "<type arch=\"x86_64\" machine=\"pc\">hvm</type>" +
                            "</os>" +
                            "<devices>" +
                                "<emulator>/usr/bin/qemu-system-x86_64</emulator>" +
                                "<disk device=\"disk\" type=\"file\">" +
                                    "<source file=\"/home/liscju/LibVirtExercises/images/ReactOS.vmdkReactOS-on-QEMU.img\"/>" +
                                    "<target bus=\"ide\" dev=\"hda\"/>" +
                                "</disk>" +
                                "<interface type=\"network\">" +
                                    "<source network=\"default\"/>" +
                                "</interface>" +
                                "<graphics autoport=\"yes\" listen=\"0.0.0.0\" port=\"-1\" type=\"vnc\"/>" +
                            "</devices>" +
                        "</domain>",
                domainXML);
    }
}