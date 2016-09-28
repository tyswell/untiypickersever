package com.tagtrade.batch.processor.thaimtb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import com.tagtrade.App;
import com.tagtrade.constant.UrlConstant;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public class ThaimtbUtil {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ThaimtbUtil.class);
	
	public static List<EThaimtbContent> extract(String url, File file) {
		Document doc = webScapping(url);
//		Document doc = fileScapping(file);
		
		List<EThaimtbContent> contents = new ArrayList<EThaimtbContent>();

		// get all links
		if (doc != null) {
			Elements linka = doc.select("div.forumbg").not("div.forumbg.announcement");
			Elements linkb = linka.select("dt.mtb:not(:has(b:contains(ปักหมุด!)))");
			Elements linkc = linkb.select("a[class^=topictitle]");
			
			for (Element link : linkc) {
				EThaimtbContent content = new EThaimtbContent();
				content.setDescription(link.text());
				content.setUrlCode(UrlConstant.THAIMTB_URL_CODE);
				content.setUrlContentLink(link.attr("href"));

				contents.add(content);
			}
		} else {
			logger.debug("!!!!!! HAS a PROBLEM @ URL : "+url );
		}
		

		return contents;
	}
	
	public static List<EThaimtbContent> getUnInsertRow(String url, EThaimtbContent lastestRow, File file) {
		List<EThaimtbContent> allRows = ThaimtbUtil.extract(url, file);
		
		if (lastestRow != null) {
			return ThaimtbUtil.getUnInsertRow(allRows, lastestRow);
		} else {
			return allRows;
		}
	}
	
	public static List<EThaimtbContent> getUnInsertRow(List<EThaimtbContent> rows, EThaimtbContent lastestRow) {
		List<EThaimtbContent> unInsertRows = new ArrayList<>();
		for (EThaimtbContent row : rows) {
			if (! extractId(row.getUrlContentLink()).equals(extractId(lastestRow.getUrlContentLink()))) {
				unInsertRows.add(row);
			} else {
				return unInsertRows;
			}
		}
		
		return unInsertRows;
	}

	private static Document webScapping(String url) {
		Document doc = null;

		try {
			System.setProperty("http.proxyHost", "192.168.5.24");
			System.setProperty("http.proxyPort", "3128");

			Map<String, String> map = new HashMap<String, String>();
			map.put("phpbb3_9rs6_u", "64679");
			map.put("phpbb3_9rs6_k", "93947380127967cc");
			map.put("phpbb3_9rs6_sid", "bbe12f218e60d91981261bdb6dc9a99b");
			
			doc = Jsoup
					.connect(url)
					.cookies(map)
					.userAgent(
							"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.timeout(20 * 1000).referrer("http://www.google.com").get();
			checkLoginSession(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}
	
	private static void checkLoginSession(Document doc) {
		 Elements linka =
				 doc.select("div.header-profile.dropdown-container");
		 Elements linkb =
				 linka.select("a[class^=header-avatar dropdown-trigger]");
		 Elements linkc =
				 linkb.select("span[class^=username]");
		 
		 if ("deksen".equals(linkc.text())) {
			 logger.debug("SESSION CONTINUE");
		 } else {
			 logger.debug("SESSION END");
		 }
	}

	private static Document fileScapping(File file) {
		try {
			return Jsoup.parse(file,"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String extractId(String urlContentLink) {
		if (urlContentLink.contains("&t=")) {
			int beginIndex = urlContentLink.indexOf("&t=") + 3;
			int endIndex = urlContentLink.indexOf("&sid");
			
			if (endIndex < 0) {
				endIndex = urlContentLink.length();
			}
			
			return urlContentLink.substring(beginIndex, endIndex);
		} else {
			return "";
		}
	}
		
//	public static void main(String [] args) {
//		System.out.println(extractId("./viewtopic.php?f=3&t=1475138&sid=081a3595f4948d4e5d607c7a869df96d"));
//	}

}
