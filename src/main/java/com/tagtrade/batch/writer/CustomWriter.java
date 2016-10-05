package com.tagtrade.batch.writer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tagtrade.batch.processor.FacebookProcessor;
import com.tagtrade.bean.BatchOutput;
import com.tagtrade.bean.FirebaseNotificaiton;
import com.tagtrade.bean.MessageDataNotification;
import com.tagtrade.bean.Notification;
import com.tagtrade.bean.thaimtb.SearchMapContent;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ErSeachingMapContent;
import com.tagtrade.service.mobile.MobileService;
import com.tagtrade.util.DateUtil;

public class CustomWriter implements ItemWriter<BatchOutput>{

	@Autowired
	private ItemWriter<EContent> contentWriter;
	
	@Autowired
	private ItemWriter<ErSeachingMapContent> searchingMapContentWriter;
	
	@Autowired
	private MobileService mobileService;
	
	private Logger logger = LoggerFactory.getLogger(FacebookProcessor.class);
	
	@Override
	public void write(List<? extends BatchOutput> outputs) throws Exception {
		logger.debug("START WRITER");
		
		for (BatchOutput output : outputs) {
			contentWriter.write( output.geteContent() );
			searchingMapContentWriter.write( map(output.getSearchMapThaimtbs()) );
		}
//		pushNoti(output);
	}
	
	private List<ErSeachingMapContent> map(List<SearchMapContent> values){
		List<ErSeachingMapContent> result = new ArrayList<>();
		for (SearchMapContent value : values) {
			result.add( map(value) );
		}
		return result;
	}
	
	private ErSeachingMapContent map(SearchMapContent mtb) {
		ErSeachingMapContent result = new ErSeachingMapContent();
		result.setCreateDate(DateUtil.getTimestampNow());
		result.setScoreHit(mtb.getScoreHit());
		result.setSearchingId(mtb.geteSearching().getSearchingId());
		result.setContentId(mtb.geteContent().getContentId());
		
		return result;
	}
	
	private void pushNoti(BatchOutput output) {		
		FirebaseNotificaiton firebase = new FirebaseNotificaiton();
		firebase.setTo("fdFdxKzMi8w:APA91bF4vYC3xQOh-NL73x_-XMEWtIXEf1UdCb3ZFl1tU-EqPjEsuVI5cdMQ1Br_WsbGgC7elN24mpbJBvqfKDkV4kxhPLO7ZHa51N6IhaV6uifqrqDJOtB5okECRA9HyNznOIxoeceG");
		firebase.setPriority("high");
		Notification noti = new Notification();
		noti.setBody("โช๊คมาแล้ว");
		noti.setIcon("ic_launcher");
		noti.setTitle("BOT WHISPER");
		firebase.setNotification(noti);
		MessageDataNotification msg = new MessageDataNotification();
		msg.setId(1);
		msg.setMatching_date(DateUtil.getNow());
		msg.setSeacrh_word_id(2);
		msg.setSearch_word_desc("โช๊คมาแล้ว");
		msg.setTitle_content("ชุดขับภูเขาเทพๆๆๆ ขาRotor REX2 BB30 175 36 ชิฟเตอร์ XX1 ตีนผี XX1 ดิสเบรคXX ");
		msg.setUrl("http://www.thaimtb.com/forum/viewtopic.php?f=3&t=1504357&sid=0e12d9836df0681d0cc6e47538e7f97a");
		msg.setUsername("tys_te@hotmail.com");
		msg.setWeb_name("THAIMTB");
		firebase.setData(msg);
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set(requestHeaders.AUTHORIZATION, "key=AIzaSyBtlo0b2yC0o5wCQV1qguWwdQCHJdoigNM");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(firebase);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		System.out.println("jsonBody="+json);
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
		System.out.println("tokenResponse="+tokenResponse);
	}

}
