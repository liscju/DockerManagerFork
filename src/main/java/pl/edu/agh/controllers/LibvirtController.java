package pl.edu.agh.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.dao.LibvirtServerDAO;
import pl.edu.agh.model.Image;
import pl.edu.agh.model.Task;
import pl.edu.agh.util.DomainXMLBuilder;

@Controller
@RequestMapping("/")
public class LibvirtController {
	
	@Autowired
	LibvirtServerDAO LSD;
	
	private String filePath = "/resources/iso/current.iso";
	private static final int BUFFER_SIZE = 4096;
	
	
	
    @RequestMapping(value = "home/domain_action",method = RequestMethod.POST)
    public String addImageFromDockerfile(ModelMap modelMap, @RequestParam("domain_action") String action,
    		@RequestParam("domain_name") String name) {
    	
    	Domain d = LSD.getDomain(name);
    	    	
    	if(action.equals("Destroy")){
    		try {
				d.destroy();
			} catch (LibvirtException e) {
				e.printStackTrace();
			}
    	}else if(action.equals("Create")){
    		try {
				d.create();
			} catch (LibvirtException e) {
				e.printStackTrace();
			}}else if(action.equals("Undefine")){
	    		try {
					d.undefine();
					File f =new File("VNC/"+name+".html");
					if(f.exists()){
						f.delete();
					}
					
					return "redirect:libvirt_server";
				} catch (LibvirtException e) {
					e.printStackTrace();
				}
			
    	}

		return "redirect:/home/libvirt_server";
    }


	
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

	@RequestMapping(value = "/home/domains_r/create",method = RequestMethod.GET)
	public String showCreateDomain(ModelMap model) {
		return "home/libvirt_domain_create";
	}
	
	public void createVNCFile(String port, String name){
		
		File f =new File("VNC/"+name+".html");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String xml = "<html><title>VNC desktop</title><body><applet archive=\"tightvnc-jviewer.jar\" "
					+ "code=\"com.glavsoft.viewer.Viewer\" width=\"1\" height=\"1\">"
					+ "<param name=\"Host\" value=\""+LSD.getIPAddress()+"\" /><param name=\"Port\" value=\""+port+"\" /></applet></body></html>";
			try {
				FileWriter fw = new FileWriter(f.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(xml);
				bw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	@RequestMapping(value = "/home/domains_r/create_from_xml", method = RequestMethod.POST)
	public String createDomainFromXML(ModelMap model, @RequestParam("domain_xml") String domain_xml) {
		LSD.createDomainFromXML(domain_xml);
		return "redirect:/home/libvirt_server";
	}

	@RequestMapping(value = "/home/domains_r/create_from_properties", method = RequestMethod.POST)
	public String createDomainFromProperties(ModelMap model, @RequestParam("domain_name") String domainName,
											 @RequestParam("domain_memory") String domainMemory,
											 @RequestParam("domain_vcpu") String domainVcpu,
											 @RequestParam("domain_emulator") String domainEmulator,
											 @RequestParam("domain_sourcefile") String domainSourceFile

	) {
		String domainXml;
		try {
			DomainXMLBuilder domainXMLBuilder = new DomainXMLBuilder();

			if (domainName != null && !domainName.equals(""))
				domainXMLBuilder = domainXMLBuilder.withName(domainName);

			if (domainMemory != null && !domainMemory.equals(""))
				domainXMLBuilder = domainXMLBuilder.withMemory(Integer.parseInt(domainMemory));

			if (domainVcpu != null && !domainVcpu.equals(""))
				domainXMLBuilder = domainXMLBuilder.withVCpu(Integer.parseInt(domainVcpu));

			if (domainEmulator != null && !domainEmulator.equals(""))
				domainXMLBuilder = domainXMLBuilder.withEmulator(domainEmulator);

			if (domainEmulator != null && !domainEmulator.equals(""))
				domainXMLBuilder = domainXMLBuilder.withSourceFile(domainSourceFile);
			

			domainXml = domainXMLBuilder.buildXML();
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			return "redirect:/home/domains_r/create";
		}

		LSD.createDomainFromXML(domainXml);
		return "redirect:/home/libvirt_server";
	}

	@RequestMapping(value="/home/domains_r/{domain_name}",method = RequestMethod.GET)
    public String getRunning(@PathVariable String domain_name,ModelMap model) {
    	model.addAttribute("domain_name",domain_name);
    	java.util.Map<String, String> info = LSD.getRunningDomainInfo(domain_name);
    	model.addAttribute("rd_info",info);
    	model.addAttribute("vnc_server",LSD.getIPAddress());
    	createVNCFile(info.get("VNCPort"),domain_name);
    	
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
