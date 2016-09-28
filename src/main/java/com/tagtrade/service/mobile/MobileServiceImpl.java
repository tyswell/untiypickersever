package com.tagtrade.service.mobile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.tagtrade.bean.mobile.MobileBody;

public class MobileServiceImpl implements MobileService {
	
	String GCM_URL = "https://gcm-http.googleapis.com/gcm/send";
	String API_KEY = "AIzaSyAuJcRkg2Va8ia68KnTucazLG2mgm1L0fg";
	 String collpaseKey = "gcm_message";

	@Override
	public void pushNotification(MobileBody mobile) {

	    
		GCMProxySender sender = new GCMProxySender(API_KEY);
	    Message.Builder builder = new Message.Builder();
	    builder.collapseKey(collpaseKey);
	    builder.timeToLive(30);
	    builder.delayWhileIdle(true);
	    builder.addData("body", "เทสเทสเทสเ");
	    builder.addData("title", "ลองลองลอง");
	    builder.addData("icon", "ic_launcher");
	    Message message = builder.build();
	    
	    List<String> androidTargets = new ArrayList<String>();
	    androidTargets.add(mobile.getTo());
	    
	
	    
		MulticastResult result;
		try {
			System.setProperty("http.proxyHost", "192.168.5.24");
			System.setProperty("http.proxyPort", "3128");
			
			result = sender.send(message, androidTargets, 1);
			System.out.println("result = "+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
