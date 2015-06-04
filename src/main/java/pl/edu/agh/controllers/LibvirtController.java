package pl.edu.agh.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;
import org.libvirt.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.dao.LibvirtServerDAO;
import pl.edu.agh.model.Image;

@Controller
@RequestMapping("/")
public class LibvirtController {
	
	@Autowired
	LibvirtServerDAO LSD;
	
	private String filePath = "/resources/iso/current.iso";
	private static final int BUFFER_SIZE = 4096;


	
    @RequestMapping(value="/home/libvirt_server",method = RequestMethod.GET)
    public String getServers(ModelMap model) {
        java.util.Map<String, String> info = LSD.getServerInfo();
        model.addAttribute("server_info",info);
        List<Domain> domains = LSD.getAllDomains();
        model.addAttribute("domains",domains);
        String[] defined = LSD.getDefinedDomains();
        model.addAttribute("defined",defined);
        return "home/libvirt_server";
    }
    
    @RequestMapping(value="/home/domains_r/{domain_name}",method = RequestMethod.GET)
    public String getRunning(@PathVariable String domain_name,ModelMap model) {
    	model.addAttribute("domain_name",domain_name);
    	java.util.Map<String, String> info = LSD.getRunningDomainInfo(domain_name);
    	model.addAttribute("rd_info",info);

    	
    	
        return "home/libvirt_r_details";
    }
    @RequestMapping(value="/home/domains_d/{domain_name}",method = RequestMethod.GET)
    public String getDefined(@PathVariable String domain_name,ModelMap model) {
    	
    	
        return "home/libvirt_d_details";
    }


	@RequestMapping(value="/iso",method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ServletContext context = request.getServletContext();
	
		String appPath = context.getRealPath("");
		String fullPath = appPath + filePath;		
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}

}
