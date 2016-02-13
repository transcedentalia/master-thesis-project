
public class Endpoint {
	private String sourceIp;
	private String destIp;
	
	public Endpoint(String sourceIp, String destIp) {
		this.sourceIp = sourceIp;
		this.destIp = destIp;
	}
	
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getDestIp() {
		return destIp;
	}
	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}
}
