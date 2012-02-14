package eurecom.phonegap;

import org.jivesoftware.smack.packet.Packet;

/**
 * A stanza in all its simplicity... 
 * @author alex
 *
 */
public class Stanza extends Packet {
	private String xml;
	
	
	public Stanza(String xml) {
		super();
		this.setXML(xml);
	}

	@Override
	public String toXML() {
		return xml;
	}

	public void setXML(String xml) {
		this.xml = xml;
	}
}
