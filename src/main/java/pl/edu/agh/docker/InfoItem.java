package pl.edu.agh.docker;

public class InfoItem {
	
	private String parameter;
	private String value;
	
	public InfoItem(String parameter,String value){
		this.parameter=parameter;
		this.value=value;
	}
	
	public String getParameter(){
		return parameter;
	}
	
	public String getValue(){
		return value;
	}

}
