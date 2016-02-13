import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ResultsWriter {
	private String outputFile;
	
	public ResultsWriter(String outputFile) {
		this.outputFile = outputFile;
	}
	
	public void writeToFileUniFlows(HashMap<Flow, UniFlowProperties> results) {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		for (Map.Entry<Flow, UniFlowProperties> entry : results.entrySet()) {
		    Flow key = entry.getKey();
		    UniFlowProperties value = entry.getValue();
		
		    writer.println(/*key.sourceIp + "," + key.destIp + "," +*/
		    			   key.sourcePort + "," + key.destPort + "," +
		    			   key.transportProtocol + "," +
		    			   value.numberOfPackets + "," +
		    			   (value.endTime - value.startTime) + "," +
		    			   value.firstTenPacketsLength + "," +
		    			   value.allPacketsLength + "," +
		    			   value.minPacketLength + "," +
		    			   value.maxPacketLength + "," +
		    			   value.avgPacketLength + "," +
		    			   value.stdDevPacketLenght + "," +
		    			   value.getUniFlowInterPackerTimeResults()
		    );
		    		    
		}
		
		writer.close();
		
	}
	
	public void writeToFileBiFlows(HashMap<Flow, BiFlowProperties> results) {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		for (Map.Entry<Flow, BiFlowProperties> entry : results.entrySet()) {
		    Flow key = entry.getKey();
		    BiFlowProperties value = entry.getValue();
		
		    
		    
		    writer.println(/*key.sourceIp + "," + key.destIp + "," +*/
		    			   key.sourcePort + "," + key.destPort + "," +
		    			   key.transportProtocol + "," +
		    			   value.getBiFlowResults()
		    );
		    		    
		}
		
		writer.close();
		
	}
}
