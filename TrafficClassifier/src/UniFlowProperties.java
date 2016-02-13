import java.util.ArrayList;


public class UniFlowProperties {
	public int numberOfPackets;
	public double startTime;
	public double endTime;
	public int firstTenPacketsLength;
	public int allPacketsLength;
	public ArrayList<Integer> packetsLength;
	public int minPacketLength;
	public int maxPacketLength;
	public float avgPacketLength;
	public double stdDevPacketLenght;
	public ArrayList<Long> packetArrivalTimes;
	public long minInterPacketArrivalTime;
	public long maxInterPacketArrivalTime;
	public long avgInterPacketArrivalTime;
	public long stdDevInterPacketArrivalTime;
	
	public UniFlowProperties(int numberOfPackets,
							 double startTime,
							 double endTime,
				   			 int firstTenPacketsLength,
				   			 int allPacketsLength,
				   			 ArrayList<Integer> packetsLength,
				   			 int minPacketLength,
				   			 int maxPacketLenght,
							 float avgPacketLength,
							 double stdDevPacketLenght,
							 ArrayList<Long> packetArrivalTimes) {
		
		this.numberOfPackets = numberOfPackets;
		this.startTime = startTime;
		this.endTime = endTime;
		this.firstTenPacketsLength = firstTenPacketsLength;
		this.allPacketsLength = allPacketsLength;
		this.packetsLength = packetsLength;
		this.minPacketLength = minPacketLength;
		this.maxPacketLength = maxPacketLenght;
		this.avgPacketLength = avgPacketLength;
		this.stdDevPacketLenght = stdDevPacketLenght;
		this.packetArrivalTimes = packetArrivalTimes;
		
	}
	
	public void computeResults() {
		ArrayList<Long> allInterPacketArrivalTimes = new ArrayList<Long>();
				
		if(allInterPacketArrivalTimes.size() < 1) {
			allInterPacketArrivalTimes.add((long)0);
		}
		
		for(int i = 1; i < packetArrivalTimes.size(); ++i) {
			allInterPacketArrivalTimes.add(packetArrivalTimes.get(i) - packetArrivalTimes.get(i - 1));
		}
				
		minInterPacketArrivalTime = Util.getMinInterPacketArrivalTime(allInterPacketArrivalTimes);
		maxInterPacketArrivalTime = Util.getMaxInterPacketArrivalTime(allInterPacketArrivalTimes);
		avgInterPacketArrivalTime = Util.getAvgInterPacketArrivalTime(allInterPacketArrivalTimes);
		stdDevInterPacketArrivalTime = Util.getStdDevInterPacketArrivalTime(allInterPacketArrivalTimes);
	}
	
	public String getUniFlowInterPackerTimeResults() {		
		computeResults();
			
		String result = minInterPacketArrivalTime + "," + maxInterPacketArrivalTime + "," +
				  avgInterPacketArrivalTime + "," + stdDevInterPacketArrivalTime;
		
		return result;
		
	}
}
