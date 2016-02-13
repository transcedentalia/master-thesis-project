import java.util.ArrayList;

public class BiFlowProperties {
	public double startTime;
	public double endTime;
	public ArrayList<Integer> firstTenSenders;
	public ArrayList<Long> firstTenInterPacketArrivalTimes;
	public ArrayList<Long> packetsArrivalTimes;
	public long minInterPacketArrivalTime;
	public long maxInterPacketArrivalTime;
	public long avgInterPacketArrivalTime;
	public long stdDevInterPacketArrivalTime;
	public ArrayList<Integer> firstTenDifferencesBetweenConsecutivePacketsLength;
	public ArrayList<Integer> firstTenPacketsLengths;
	public int numberOfPackets;
	public ArrayList<Integer> packetsLength;
	public int minPacketLength;
	public int maxPacketLength;
	public float avgPacketLength;
	public double stdDevPacketLenght;
	
	public BiFlowProperties(double startTime,
							double endTime,
							ArrayList<Integer> firstTenSenders,
							ArrayList<Long> firstTenInterPacketArrivalTimes,
							ArrayList<Long> packetsArrivalTimes,
							long minInterPacketArrivalTime,
							long maxInterPacketArrivalTime,
							long avgInterPacketArrivalTime,
							long stdDevInterPacketArrivalTime,
							ArrayList<Integer> differenceBetweenConsecutivePacketsLength,
							ArrayList<Integer> firstTenPacketsLengths,
							int numberOfPackets,
							ArrayList<Integer> packetsLength,
							int minPacketLength,
							int maxPacketLength,
							float avgPacketLength,
							double stdDevPacketLenght) {
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.firstTenSenders = firstTenSenders;
		this.firstTenInterPacketArrivalTimes = firstTenInterPacketArrivalTimes;
		this.packetsArrivalTimes = packetsArrivalTimes;
		this.minInterPacketArrivalTime = minInterPacketArrivalTime;
		this.maxInterPacketArrivalTime = maxInterPacketArrivalTime;
		this.avgInterPacketArrivalTime = avgInterPacketArrivalTime;
		this.stdDevInterPacketArrivalTime = stdDevInterPacketArrivalTime;
		this.firstTenDifferencesBetweenConsecutivePacketsLength = differenceBetweenConsecutivePacketsLength;
		this.firstTenPacketsLengths = firstTenPacketsLengths;
		this.numberOfPackets = numberOfPackets;
		this.packetsLength = packetsLength;
		this.minPacketLength = minPacketLength;
		this.maxPacketLength = maxPacketLength;
		this.avgPacketLength = avgPacketLength;
		this.stdDevPacketLenght = stdDevPacketLenght;
								
	}
	
	
	private void computeResults() {
		
		ArrayList<Long> allInterPacketArrivalTimes = new ArrayList<Long>();
		
		if(firstTenSenders.size() < 10) {
			int toAdd = 10 - firstTenSenders.size();
			for(int i = 1; i <= toAdd; ++i) {
				firstTenSenders.add(2);
			}
		}
		
		if(allInterPacketArrivalTimes.size() < 1) {
			allInterPacketArrivalTimes.add((long)0);
		}
		
		for(int i = 1; i < packetsArrivalTimes.size(); ++i) {
			allInterPacketArrivalTimes.add(packetsArrivalTimes.get(i) - packetsArrivalTimes.get(i - 1));
		}
		
		for(int i = 0; i < 11; ++i) {
			
			if(i >= allInterPacketArrivalTimes.size()) {
				firstTenInterPacketArrivalTimes.add((long)0);
			}else {
			
				firstTenInterPacketArrivalTimes.add(allInterPacketArrivalTimes.get(i));
			}
		}
		
		minInterPacketArrivalTime = Util.getMinInterPacketArrivalTime(allInterPacketArrivalTimes);
		maxInterPacketArrivalTime = Util.getMaxInterPacketArrivalTime(allInterPacketArrivalTimes);
		avgInterPacketArrivalTime = Util.getAvgInterPacketArrivalTime(allInterPacketArrivalTimes);
		stdDevInterPacketArrivalTime = Util.getStdDevInterPacketArrivalTime(allInterPacketArrivalTimes);
		
		for(int i = 1; i < 11; ++i) {
						
			if(i >= firstTenPacketsLengths.size()) {
				firstTenDifferencesBetweenConsecutivePacketsLength.add(0);
			}else {

				firstTenDifferencesBetweenConsecutivePacketsLength.add(firstTenPacketsLengths.get(i) - 
																	   firstTenPacketsLengths.get(i - 1));
			}
		}
		
	}
	
	public String getBiFlowResults() {
		String result = "";
		
		computeResults();
		
		result += numberOfPackets + "," + (endTime - startTime) + ",";
		
		for(int i = 0; i < firstTenSenders.size(); ++i) {
			result += firstTenSenders.get(i) + ",";
		}
		
		for(int i = 0; i < firstTenInterPacketArrivalTimes.size(); ++i) {
			result += firstTenInterPacketArrivalTimes.get(i) + ",";
		}
		
		for(int i = 0; i < firstTenDifferencesBetweenConsecutivePacketsLength.size(); ++i) {
			result += firstTenDifferencesBetweenConsecutivePacketsLength.get(i) + ",";
		}
		
		result += minInterPacketArrivalTime + "," + maxInterPacketArrivalTime + "," +
				  avgInterPacketArrivalTime + "," + stdDevInterPacketArrivalTime + ",";
		
		result += minPacketLength + "," + maxPacketLength + "," +
				  avgPacketLength + "," + stdDevPacketLenght;
		
		return result;
		
	}
}
