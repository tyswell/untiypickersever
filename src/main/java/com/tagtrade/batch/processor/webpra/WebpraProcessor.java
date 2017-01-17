package com.tagtrade.batch.processor.webpra;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tagtrade.batch.processor.BaseProcessor;
import com.tagtrade.constant.UrlConstant;
import com.tagtrade.dataacess.entity.bean.EContent;

public class WebpraProcessor extends BaseProcessor {

	@Override
	public List<EContent> extractData(String url) {
		Document doc = webScapping(url);
		
		List<EContent> contents = new ArrayList<EContent>();
		
		if (doc != null) {
			Elements linka = doc.select("table.hTable.footable");
			
			Elements linkb = linka.select("tr:not(.pinnedAll)");
			Elements linkd = linkb.select("tr:not(.pinned)");
			
			for (Element link : linkd) {
				if (!link.select("a.title").text().equals("")) {
					EContent content = new EContent();
					content.setTitle(link.select("a.title").text());
					content.setUrlCode(UrlConstant.WEBPRA_URL_CODE);
					content.setUrlContentLink(getLink(link.select("a[onclick]").attr("onclick")));
					content.setContentWebId(link.select("td.itemID.textCenter.first").text());
					
					contents.add(content);
				}

			}
		} else {
			System.out.println("!!!!!! HAS a PROBLEM @ URL : " );
		}
		return contents;
	}
	
	public static String getLink(String word) {
		if (word != null && !word.trim().equals("")) {
			int begin = word.indexOf("'");
			int last = word.lastIndexOf("'");
			return word.substring(begin + 1, last);
		} else {
			return null;
		}
		
	}

}
