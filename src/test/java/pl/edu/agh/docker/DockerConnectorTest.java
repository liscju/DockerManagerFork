package pl.edu.agh.docker;

import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.FrameReader;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lee on 2015-05-27.
 */
public class DockerConnectorTest {

    @Test
    public void testStart() throws Exception {
        DockerConnector dockerConnector = new DockerConnector("http://192.168.0.23:2375");

        String containerId = dockerConnector.createContainer("226fc3006c6440d4d66e901c471fc10fbc087a807bf4599f60da4e36c532b8af");
        dockerConnector.startContainer(containerId);
        String commandId = dockerConnector.createCommand(containerId, "cat /etc/fstab");
        InputStream inputStream = dockerConnector.execCommand(containerId, commandId);
        inputStream.read(new byte[8]);
        List<String> strings = IOUtils.readLines(inputStream);
        for (String str : strings) {
            System.out.println(str);
        }
    }
}














