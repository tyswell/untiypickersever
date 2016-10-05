package com.tagtrade.search;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.tagtrade.bean.SearchContentResult;
import com.tagtrade.dataacess.entity.bean.EContent;

public class SearchTool {
	
	private List<EContent> contents;
	
	public SearchTool(List<EContent> contents) {
		this.contents = contents;
	}
	
	public List<SearchContentResult> search(String word) {
		List<EContent> matchContents = new ArrayList<>();
		List<String> searchWords = tokenize(word);
		
		for (EContent content : contents) {			
			if (searchByTitle(searchWords, content.getTitle())) {
				matchContents.add(content);
			} else if (searchByDescription(searchWords, content.getDescription())){
				matchContents.add(content);
			}
		}
		
		return convert(matchContents);
	}
	
	private boolean searchByDescription(List<String> searchWords, String description) {
		if (description == null) {
			return false;
		}
		
		for (String line : seperateByLine(description)) {
			if (line!= null &&
					line.length() > 0 &&
					(
							Character.isDigit(line.charAt(0)) ||
							"-".equals(line.charAt(0))
					)) {
				boolean isMatch = true;
				for (String searchWord : searchWords) {
					if (!line.contains(searchWord)) {
						isMatch = false;
						break;
					}
				}
				
				if (isMatch) {
					return true;
				}
			}
		}
		
		return false;
		
	}

	private boolean searchByTitle(List<String> searchWords, String title) {
		if (title == null) {
			return false;
		}
		
		for (String searchWord : searchWords) {
			if (!title.contains(searchWord)) {
				return false;
			}
		}
		
		return true;
	}
	
	private String[] seperateByLine(String word) {
		String newline = System.getProperty("line.separator");
		
		if (word.contains("\n")) {
			return word.split("\n");
		}
		
		return new String[]{word};
	}
	
	private SearchContentResult convert(EContent contents) {
		SearchContentResult result = new SearchContentResult();
		result.seteContent(contents);
		result.setScoreHit(1F);
		return result;
	}
	
	private List<SearchContentResult> convert(List<EContent> contents) {
		List<SearchContentResult> results = new ArrayList<>();
		
		for (EContent content : contents) {
			results.add(convert(content));
		}
		
		return results;
	}
	
	private List<String> tokenize(String text) {
		List<String> results = new ArrayList<>();
		Analyzer analyzer = new ThaiAnalyzer();
		
		TokenStream ts = analyzer.tokenStream(null, new StringReader(text));
		
		CharTermAttribute cattr = ts.addAttribute(CharTermAttribute.class);
		try {
			ts.reset();
			while (ts.incrementToken()) {
				results.add(cattr.toString());
			}
			ts.end();
			ts.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 		
		return results;
	}

}
