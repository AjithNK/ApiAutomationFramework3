package requestPojo;

public class RequestPojoPatch {
	
	public String job;
	  
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}


	public String toString() {

		return "{\r\n"
					+ "    \"job\": \""+this.job+"\"\r\n"
					+ "}";

	}

}
