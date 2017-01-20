package com.tagtrade.batch.writer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tagtrade.bean.BatchOutput;
import com.tagtrade.bean.FirebaseNotificaiton;
import com.tagtrade.bean.MessageDataNotification;
import com.tagtrade.bean.thaimtb.SearchMapContent;
import com.tagtrade.constant.WebNameConstant;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.util.DateUtil;

public class TestPushNotification {
	
	public static void sendNoti(FirebaseNotificaiton data) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set(requestHeaders.AUTHORIZATION, "key=AIzaSyBtlo0b2yC0o5wCQV1qguWwdQCHJdoigNM");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		HttpEntity<String> piResponse = new HttpEntity<String>(json, requestHeaders);

		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

	    Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("192.168.5.24", 3128));
	    requestFactory.setProxy(proxy);
		
		String baseUrl = "https://fcm.googleapis.com/fcm/send";
		RestTemplate rest = new RestTemplate(requestFactory);
		rest.getMessageConverters()
        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String tokenResponse = rest.postForObject(baseUrl, piResponse,
				String.class);
	}
	
	public static void pushNoti() {				
		FirebaseNotificaiton firebase = new FirebaseNotificaiton();
		firebase.setTo("eVJLj-lsjYM:APA91bEpytIuK1d02w2Bkk1_UhQoUC2KXCuATl6jYBjwtKn_7tfiK6Xa7sY7ljkj36ZchmlyD1D8p8R2kaIiL9Yg6Fkb2f7Pw1fGqIrytOd0bytlp7ZxALYu89QpEojXf_c0f6m6Fy7h");
		MessageDataNotification msg = new MessageDataNotification();
		msg.setMatching_date(DateUtil.formatDateTime(DateUtil.getNow()));
		msg.setSeacrh_word_id(1426200614);
		msg.setSearch_word_desc("ยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยยย");
		msg.setContent_id(50);
		msg.setTitle_content("ไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททันไททัน");
		msg.setUrl("http://www.thaimtb.com/forum/viewforum.php?f=3");
		msg.setUser_id("LNQT3FvvYXeJkYnPzww1ng9R5bf2");
		msg.setWeb_name(WebNameConstant.THAIMTB_DESC);
		firebase.setData(msg);
		
		sendNoti(firebase);
	}
	
	public static void main(String[] args) {
		pushNoti();
	}

}
