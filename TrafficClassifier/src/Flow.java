
public class Flow {
	public String sourceIp;
	public String destIp;
	public Integer sourcePort;
	public Integer destPort;
	public String transportProtocol;	
	
	public Flow(String sourceIp, String destIp, Integer sourcePort,
					   Integer destPort, String transportProtocol) {
		
		this.sourceIp = sourceIp;
		this.destIp = destIp;
		this.sourcePort = sourcePort;
		this.destPort = destPort;
		this.transportProtocol = transportProtocol;
	}
	
	public String toString() {
		return this.sourceIp + "," + this.destIp + "," + this.sourcePort + 
				"," + this.destPort + "," + this.transportProtocol;
	}
	
	public int hashCode() {
		return sourceIp.hashCode() ^ destIp.hashCode() ^ 
			   sourcePort.hashCode() ^ destPort.hashCode() ^
			   transportProtocol.hashCode();
	}
	
	public boolean equals(Object obj) {
		
		Flow f = (Flow)obj;
		if(sourceIp.equals(f.sourceIp) && destIp.endsWith(f.destIp) &&
		   sourcePort.equals(f.sourcePort) && destPort.equals(f.destPort) &&
		   transportProtocol.equals(f.transportProtocol)) {
			
			return true;
		}
		
		return false;
		
	}

}
