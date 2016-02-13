import net.sourceforge.jpcap.capture.*;

public class Test {
	
	private static final String INPUT_FILE = "samples/Enterprise_Application_Profile.pcap";
	private static final String OUTPUT_FILE_UNIDIRECTIONAL = "output/Enterprise_Application_Profile1.csv";
	private static final String OUTPUT_FILE_BIDIRECTIONAL = "output/Enterprise_Application_Profile2.csv";
	private static final int INFINITE = -1;
	private static final int PACKET_COUNT = INFINITE; 
	private static final String FILTER = "";

	public Test() throws Exception {
		
		// Initialize jpcap
		PacketCapture pcap = new PacketCapture();
		pcap.openOffline(INPUT_FILE);
		
		pcap.setFilter(FILTER, true);
		pcap.addPacketListener(new PacketHandler());
		
		// Start capturing packets...
		pcap.capture(PACKET_COUNT);
	}

	public static void main(String[] args) {
		try {
			
			long t1 = System.currentTimeMillis();
			
			Test example = new Test();
			
			ResultsWriter rw = new ResultsWriter(OUTPUT_FILE_UNIDIRECTIONAL);
			rw.writeToFileUniFlows(FlowDatabase.uniFlows);
			
			rw = new ResultsWriter(OUTPUT_FILE_BIDIRECTIONAL);
			rw.writeToFileBiFlows(FlowDatabase.biFlows);
			
			long t2 = System.currentTimeMillis();
			System.out.println("Run time: " + (t2 - t1));
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
	}
}
