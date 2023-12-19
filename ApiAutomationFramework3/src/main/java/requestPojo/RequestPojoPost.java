package requestPojo;

public class RequestPojoPost {
	
	public String name;
    public String job;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	
	// Here we are overriding the toString() method so that we get the output as how we require.
	// i.e here we require output in json format, so that we write the code such that this method returns a json string
	public String toString() {

		return "{\r\n"
				+ "    \"name\": \""+this.name+"\",\r\n"
				+ "    \"job\": \""+this.job+"\"\r\n"
				+ "}";
		
		
	}
	
	
    
	
	
    

}
