package pl.edu.agh.docker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lee on 2015-05-19.
 */
public class DockerManagerTest {

    @Test
    public void testCreateImageFromDockerFile() throws Exception {
        DockerManager dockerManager = new DockerManager("http://192.168.0.2:2375");
        String dockerContent =
                "FROM ubuntu:14.04\n" +
                "ENV NAME PIOTREK\n";
        dockerManager.createImageFromDockerFile("misiek",dockerContent);
    }
}