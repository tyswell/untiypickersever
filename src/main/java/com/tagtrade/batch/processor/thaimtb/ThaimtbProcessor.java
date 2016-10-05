package com.tagtrade.batch.processor.thaimtb;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tagtrade.constant.UrlConstant;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.batch.processor.BaseProcessor;

public class ThaimtbProcessor extends BaseProcessor {

	private Logger logger = LoggerFactory.getLogger(ThaimtbProcessor.class);
	
	@Override
	public List<EContent> extractData(String url) {
		Document doc = webScapping(url);
		
		List<EContent> contents = new ArrayList<EContent>();

		// get all links
		if (doc != null) {
			Elements linka = doc.select("div.forumbg").not("div.forumbg.announcement");
			Elements linkb = linka.select("dt.mtb:not(:has(b:contains(ปักหมุด!)))");
			Elements linkc = linkb.select("a[class^=topictitle]");
			
			for (Element link : linkc) {
				EContent content = new EContent();
				content.setTitle(link.text());
				content.setUrlCode(UrlConstant.THAIMTB_URL_CODE);
				content.setUrlContentLink(link.attr("href"));
				content.setContentWebId(extractId(content.getUrlContentLink()));
				contents.add(content);
			}
		} else {
			logger.debug("!!!!!! HAS a PROBLEM @ URL : "+url );
		}
		

		return contents;
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

}
