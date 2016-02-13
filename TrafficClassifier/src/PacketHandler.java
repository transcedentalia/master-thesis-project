import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.TCPPacket;
import net.sourceforge.jpcap.net.UDPPacket;

public class PacketHandler implements PacketListener {
	
	public void packetArrived(Packet packet) {
		try {

			String srcHost = "", dstHost= "", transportProto ="";
			Integer srcPort = 0, dstPort = 0;
			Integer length = packet.getData().length;
			
			if(packet instanceof TCPPacket) {
				
				transportProto = "TCP";
				TCPPacket tcpPacket = (TCPPacket)packet;
		    
				srcHost = tcpPacket.getSourceAddress();
				dstHost = tcpPacket.getDestinationAddress();
				srcPort = tcpPacket.getSourcePort();
				dstPort = tcpPacket.getDestinationPort();

			}
			
			if(packet instanceof UDPPacket) {
				
				transportProto = "UDP";
				UDPPacket udpPacket = (UDPPacket)packet;
		    
				srcHost = udpPacket.getSourceAddress();
				dstHost = udpPacket.getDestinationAddress();
				srcPort = udpPacket.getSourcePort();
				dstPort = udpPacket.getDestinationPort();
				
			}
			
			System.out.println(transportProto + " " + srcHost + " " + dstHost +
					           " " + srcPort + " " + dstPort);
			
			FlowDatabase.addToDb(srcHost, dstHost, srcPort, dstPort,
					             transportProto, length);
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
}