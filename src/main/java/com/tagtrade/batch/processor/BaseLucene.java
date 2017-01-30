package com.tagtrade.batch.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.ParseException;


import com.tagtrade.bean.SearchContentResult;
import com.tagtrade.constant.LuceneConstant;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.util.TimestampUtil;

public class BaseLucene {
	
	private static final String FOLDER_INDEX = "CONTENT";
	private Analyzer analyzer = new ThaiAnalyzer();
	private IndexWriter writer;
	private IndexSearcher searcher;
			
	
	public void initCreateIndex() {
		writer = initIndex();
	}
	
	public void initSearch() {
		searcher = initIndexSearch();
	}
		
	public List<SearchContentResult> search(String word, int indexSearchPage, int takeRecords) {
		List<SearchContentResult> matchDatas = new ArrayList<>();
//		QueryParser queryP = new QueryParser(field, analyzer);
		MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
                new String[] {LuceneConstant.TITLE_CONTENT, LuceneConstant.DESCRIP_CONTENT},
                analyzer);

		try {
			Query query = queryParser.parse(word);
			TopDocs hits = searcher.search(query, indexSearchPage + takeRecords);
			ScoreDoc [] scores = hits.scoreDocs;
			
			for (int i = indexSearchPage; i < hits.totalHits; i++) {
				if (i > (indexSearchPage + takeRecords) - 1) {
				      break;
				}
				
				Document doc = searcher.doc(scores[i].doc);
				SearchContentResult result = new SearchContentResult();
				EContent content = new EContent();
				content.setContentId( doc.getField(LuceneConstant.ID).numericValue().intValue() );
				content.setTitle( doc.getField(LuceneConstant.TITLE_CONTENT).stringValue() );
				content.setDescription( doc.getField(LuceneConstant.DESCRIP_CONTENT).stringValue() );
				content.setUrlCode( doc.getField(LuceneConstant.URL_CODE).numericValue().intValue() );
				content.setFacebookGropCode(doc.getField(LuceneConstant.FACEBOOK_GROUP_CODE).numericValue().intValue());
				content.setCreateDate( TimestampUtil.stringToTimestampThaiFormat(doc.getField(LuceneConstant.DATE_CONTENT_CREATE).stringValue()) );
				content.setUrlContentLink( doc.getField(LuceneConstant.URL_CONTENT_LINK).stringValue() );
				
				result.seteContent(content);
				result.setScoreHit(scores[i].score);
				
				matchDatas.add(result);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return matchDatas;
	}
	
	
	private IndexWriter initIndex() {
		File indexFolder = new File(FOLDER_INDEX);
		try {
			Directory dir = FSDirectory.open(indexFolder.toPath());
			IndexWriterConfig iws = new IndexWriterConfig(analyzer);
			iws.setWriteLockTimeout(20000);
			iws.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			return new IndexWriter(dir, iws);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private IndexSearcher initIndexSearch() {
		try {
			File indexFolder = new File(FOLDER_INDEX);
			
			Directory dir = FSDirectory.open(indexFolder.toPath());
			IndexReader reader = DirectoryReader.open(dir);
			return new IndexSearcher(reader);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addDataToIndex(List<EContent> datas) {
		try {
			for (EContent data : datas) {
				Document doc = new Document();
				doc.add(new IntField(LuceneConstant.ID, data.getContentId(), Field.Store.YES));
				doc.add(new TextField(LuceneConstant.TITLE_CONTENT, changeNullData(data.getTitle()), Field.Store.YES));
				doc.add(new TextField(LuceneConstant.DESCRIP_CONTENT, changeNullData(data.getDescription()), Field.Store.YES));
				doc.add(new IntField(LuceneConstant.URL_CODE, data.getUrlCode(), Field.Store.YES));
				doc.add(new IntField(LuceneConstant.FACEBOOK_GROUP_CODE, changeNullData(data.getFacebookGropCode()), Field.Store.YES));
				doc.add(new TextField(LuceneConstant.URL_CONTENT_LINK, changeNullData(data.getUrlContentLink()), Field.Store.YES));
				doc.add(new TextField(LuceneConstant.DATE_CONTENT_CREATE, TimestampUtil.timestampToStringThaiFormat( data.getCreateDate() ), Field.Store.YES));
				writer.addDocument(doc);
			}
			
			writer.commit();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String changeNullData(String data) {
		if (data == null) {
			return "";
		} else {
			return data;
		}
	}
	
	private Integer changeNullData(Integer data) {
		if (data == null) {
			return 0;
		} else {
			return data;
		}
	}
	
	
	public static void main(String[] args) {
		String a = "2560-01-25 21:21:06:000";
		System.out.println(TimestampUtil.parseToTimetamp(a));
	}


}
