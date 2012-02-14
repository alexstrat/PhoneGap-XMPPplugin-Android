package eurecom.phonegap;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;


public class XMPPPhoneGapPlugin extends Plugin implements PacketListener {

	private enum Action {
		connect("connect"), 
		login("login"), 
		onStanza("onStanza"),
		sendStanza("sendStanza");
		
		protected String label;
		Action(String label) {
			this.label = label;
		}
	}
	private XMPPConnection m_connection;
	private String listener_callbackID = null;

	@Override
	public PluginResult execute(String action, JSONArray data, String callbackID) {
		
		switch(Action.valueOf(action)) {
		case connect :
			try {
				String SERVER_HOST = data.getString(0);
				int SERVER_PORT = data.getInt(1);
				String SERVICE_NAME = data.getString(2);

				
				return this.actionConnect(SERVER_HOST, SERVER_PORT, SERVICE_NAME);
				
			} catch(JSONException e) {
				return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
			}
		case login :
			try {
				String login = data.getString(0);
				String password = data.getString(1);
				
				return this.actionLogin(login, password);
			} catch(JSONException e) {
				Log.e("xmppPlugin", "login FAIL", e);
				return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
			}

		case sendStanza :
				try {
					String xml = data.getString(0);
					
					return this.actionSendStanza(xml);
				} catch(JSONException e) {
					return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
				}
			
			
		case onStanza : 	
			//TODO handle more than one call back
			this.listener_callbackID = callbackID;
			
			PluginResult res = new PluginResult(PluginResult.Status.NO_RESULT);
			res.setKeepCallback(true);
			return res;
			
		default:
			return new PluginResult(PluginResult.Status.INVALID_ACTION);
		}
	}
	
	
	private PluginResult actionConnect(String host, int port, String service) {
		ConnectionConfiguration config = new ConnectionConfiguration(host, port, service);
		this.m_connection = new XMPPConnection(config);
		try {
			this.m_connection.connect();
		} catch(XMPPException e) {
			return new PluginResult(PluginResult.Status.ERROR);
		}
		Log.i("xmppPlugin", "connec OK");
		return new PluginResult(PluginResult.Status.OK);
	}
	
	private PluginResult actionLogin(String login, String password) {
		try {

			this.m_connection.addPacketListener(this, new PacketFilter() {
				public boolean accept(Packet arg0) {
					return true;
				}
			});
			this.m_connection.login(login, password);
			//Log.i("xmppPlugin", "login OK"); 
			return new PluginResult(PluginResult.Status.OK); 

		} catch (XMPPException e) {
			//Log.e("xmppPlugin", "login FAIL", e);
			return new PluginResult(PluginResult.Status.ERROR);
		}

	}
	
	private PluginResult actionSendStanza(String xml) {
		Packet stanza = new Stanza(xml);
		
		m_connection.sendPacket(stanza);
		return new PluginResult(PluginResult.Status.OK);
	}


	@Override
	public void processPacket(Packet packet) {
		
		
		try {
            JSONObject obj = new JSONObject();
            obj.put("stanza", packet.toXML());
            //Log.i("receive", packet.toXML());
            
            if (this.listener_callbackID != null) {
                PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
                result.setKeepCallback(true);
                this.success(result, this.listener_callbackID);
            }
        } catch (JSONException e) {
           // Log.d(LOG_TAG, "Should never happen");
        	// TODO : error result
        }				
	}
}
