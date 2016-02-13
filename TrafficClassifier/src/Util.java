import java.util.ArrayList;


public class Util {

	public static long getMinInterPacketArrivalTime(ArrayList<Long> allInterPacketArrivalTimes) {
		long min = allInterPacketArrivalTimes.get(0);
		
		for(int i = 1; i < allInterPacketArrivalTimes.size(); ++i) {
			if(allInterPacketArrivalTimes.get(i) < min) {
				min = allInterPacketArrivalTimes.get(i);
			}
		}
		
		return min;
	}
	
	public static long getMaxInterPacketArrivalTime(ArrayList<Long> allInterPacketArrivalTimes) {
		long max = allInterPacketArrivalTimes.get(0);
		
		for(int i = 1; i < allInterPacketArrivalTimes.size(); ++i) {
			if(allInterPacketArrivalTimes.get(i) > max) {
				max = allInterPacketArrivalTimes.get(i);
			}
		}
		
		return max;
	}
	
	public static long getAvgInterPacketArrivalTime(ArrayList<Long> allInterPacketArrivalTimes) {
		long avg = 0;
		
		for(int i = 0; i < allInterPacketArrivalTimes.size(); ++i) {
			avg = ((i * avg) + allInterPacketArrivalTimes.get(i) ) / (i + 1);
			
		}
		
		return avg;
	}
	
	public static long getStdDevInterPacketArrivalTime(ArrayList<Long> allInterPacketArrivalTimes) {
		long mean = getAvgInterPacketArrivalTime(allInterPacketArrivalTimes);
		
		ArrayList<Long> temp = new ArrayList<Long>();
		for (Long item : allInterPacketArrivalTimes) {
		    temp.add((item - mean) * (item - mean));
		}
		
		return (long)Math.sqrt(getAvgInterPacketArrivalTime(temp));
	}
	
	public static float sumArrayIntegerElements(ArrayList<Integer> ai) {
		
		int sum = 0;
		
		for (Integer item : ai) {
		    sum += item.floatValue();
		}
		
		return sum;
	}
	
	private static float sumArrayFloatElements(ArrayList<Float> af) {
		
		float sum = 0;
		
		for (Float item : af) {
		    sum += item.floatValue();
		}
		
		return sum;
	}
	
	public static double calculateStdDev(ArrayList<Integer> packetsLength) {
		float mean = sumArrayIntegerElements(packetsLength) / packetsLength.size();
		
		ArrayList<Float> temp = new ArrayList<Float>();
		for (Integer item : packetsLength) {
		    temp.add((item - mean) * (item - mean));
		}
		
		return Math.sqrt(sumArrayFloatElements(temp) / temp.size());
	}
	
}
