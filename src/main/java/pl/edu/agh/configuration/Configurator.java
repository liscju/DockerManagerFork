package pl.edu.agh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.dao.ImageDAO;
import pl.edu.agh.dao.LibvirtServerDAO;

@Service
public class Configurator {
    private String address;
    
    @Autowired
    private ContainerDAO containerDAO;
    
    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private LibvirtServerDAO libvirtServerDAO;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        containerDAO.connect(address);
        imageDAO.connect(address);
        libvirtServerDAO.connect(address);
    }
}
