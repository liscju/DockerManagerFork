package pl.edu.agh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.dao.ContainerDAO;
import pl.edu.agh.dao.ImageDAO;

@Service
public class Configurator {
    private String address;
    
    @Autowired
    private ContainerDAO containerDAO;
    
    @Autowired
    private ImageDAO imageDAO;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        containerDAO.connect(address);
        imageDAO.connect(address);
    }
}
