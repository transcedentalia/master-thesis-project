import java.util.ArrayList;
import java.util.HashMap;

public class FlowDatabase {
	
	public static HashMap<Flow, UniFlowProperties> uniFlows = new 
		           HashMap<Flow, UniFlowProperties>();
	
	public static HashMap<Flow, BiFlowProperties> biFlows = new 
	           HashMap<Flow, BiFlowProperties>();
		
	public static void processUniFlowsCase(Flow f, int length) {
		if(uniFlows.containsKey(f)) {
			
			UniFlowProperties fp = uniFlows.get(f);
			fp.numberOfPackets += 1;
			
			if(fp.numberOfPackets <= 10) {
				fp.firstTenPacketsLength += length;
			}
			
			fp.endTime = System.currentTimeMillis();
			
			fp.allPacketsLength += length;
			fp.packetsLength.add(length);
			
			fp.minPacketLength = Math.min(fp.minPacketLength, length);
			fp.maxPacketLength = Math.max(fp.maxPacketLength, length);
			fp.avgPacketLength = (((fp.numberOfPackets - 1) * fp.avgPacketLength) + length ) / fp.numberOfPackets;			
			fp.stdDevPacketLenght = Util.calculateStdDev(fp.packetsLength);
			
			fp.packetArrivalTimes.add(System.currentTimeMillis());
			
			uniFlows.put(f, fp);
			
		}else {
						
			ArrayList<Integer> packetsLength = new ArrayList<Integer>();
			packetsLength.add(length);
			
			long startTime = System.currentTimeMillis();
			
			ArrayList<Long> arrivalTimes = new ArrayList<Long>();
			arrivalTimes.add(startTime);
						
			UniFlowProperties fp = new UniFlowProperties(1,
														 startTime,
														 startTime,
														 length, 
														 length,
														 packetsLength,
														 length,
														 length,
														 length,
														 0,
														 arrivalTimes);
			
			fp.stdDevPacketLenght = Util.calculateStdDev(fp.packetsLength);
			uniFlows.put(f, fp);
			
		}
	}
	
	private static void updateBiFlow(Flow f, int length, int sender) {
		
		BiFlowProperties fp = biFlows.get(f);
		fp.numberOfPackets += 1;
		
		if(fp.numberOfPackets <= 10) {
			fp.firstTenPacketsLengths.add(length);
			fp.firstTenSenders.add(sender);
		}
		
		if(fp.numberOfPackets == 11) {
			fp.firstTenPacketsLengths.add(length);
		}
		
		long arrivalTime = System.currentTimeMillis(); 
		fp.endTime = arrivalTime;
		fp.packetsArrivalTimes.add(arrivalTime);
		fp.packetsLength.add(length);
		
		fp.minPacketLength = Math.min(fp.minPacketLength, length);
		fp.maxPacketLength = Math.max(fp.maxPacketLength, length);
		fp.avgPacketLength = (((fp.numberOfPackets - 1) * fp.avgPacketLength) + length ) / fp.numberOfPackets;			
		fp.stdDevPacketLenght = Util.calculateStdDev(fp.packetsLength);
		
		biFlows.put(f, fp);
	}
	
	private static void processBiFlowsCase(Flow f, int length) {
		Flow reverseFlow = new Flow(f.destIp, f.sourceIp, 
				                    f.destPort, f.sourcePort,
				                    f.transportProtocol);
		
		if(biFlows.containsKey(f)) {
			
			updateBiFlow(f, length, 0);
			
		}else if(biFlows.containsKey(reverseFlow)) {
			updateBiFlow(reverseFlow, length, 1);
			
		}else {
						
			double startTime = System.currentTimeMillis();
			ArrayList<Integer> packetsLength = new ArrayList<Integer>();
			packetsLength.add(length);
			
			BiFlowProperties fp = new BiFlowProperties(startTime,
													   startTime,
													   new ArrayList<Integer>(),
													   new ArrayList<Long>(),
													   new ArrayList<Long>(),
													   0, 0, 0, 0,
													   new ArrayList<Integer>(),
													   new ArrayList<Integer>(),
													   1,
													   packetsLength,
													   length,
													   length,
													   length,
													   0);
			
			fp.packetsArrivalTimes.add(System.currentTimeMillis());
			fp.firstTenSenders.add(0);
			fp.firstTenPacketsLengths.add(length);
			
			fp.stdDevPacketLenght = Util.calculateStdDev(fp.packetsLength);
					
			biFlows.put(f, fp);
			
		}
		
	}
	
	public static void addToDb(String srcHost, String dstHost, Integer srcPort, Integer dstPort, String transportProto, 
							   int length) {
		
		Flow f = new Flow(srcHost,dstHost, srcPort, dstPort, transportProto);
		processUniFlowsCase(f, length);
		
		processBiFlowsCase(f, length);
		
	}
}
