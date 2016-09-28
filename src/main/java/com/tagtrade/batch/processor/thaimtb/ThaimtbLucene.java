package com.tagtrade.batch.processor.thaimtb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.ParseException;


import com.tagtrade.bean.SearchThaimtbResult;
import com.tagtrade.constant.LuceneConstant;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public class ThaimtbLucene {
	
	private static final String FOLDER_INDEX = "MTB";
	private Analyzer analyzer = new StandardAnalyzer();
	private IndexWriter writer;
	private IndexSearcher searcher;
		
	public ThaimtbLucene(List<EThaimtbContent> contents) {
		writer = initIndex();
		addDataToIndex(contents); // close writer
		searcher = initIndexSearch();
	}
		
	public List<SearchThaimtbResult> search(String word, String field) {
		List<SearchThaimtbResult> contents = new ArrayList<>();
		QueryParser queryP = new QueryParser(field, analyzer);
		int numResults = 100;

		try {
			Query query = queryP.parse(word);
			TopDocs hits = searcher.search(query, numResults);
			ScoreDoc [] scores = hits.scoreDocs;
			
			for (int i = 0; i < scores.length; i++) {
				Document doc = searcher.doc(scores[i].doc);
				SearchThaimtbResult result = new SearchThaimtbResult();
				EThaimtbContent content = new EThaimtbContent();
				content.setThaimtbId( doc.getField(LuceneConstant.ID).numericValue().intValue() );
				content.setDescription( doc.getField(LuceneConstant.TITLE_CONTENT).stringValue() );
				content.setUrlCode( doc.getField(LuceneConstant.URL_CODE).numericValue().intValue() );
//				content.setDateContentCreate( doc.getField(LuceneConstant.DATE_CONTENT_CREATE).stringValue() );
				
				result.seteThaimtbContent(content);
				result.setScoreHit(scores[i].score);
				contents.add(result);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contents;
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

	private void addDataToIndex(List<EThaimtbContent> datas) {
		try {
			for (EThaimtbContent data : datas) {
				Document doc = new Document();
				doc.add(new IntField(LuceneConstant.ID, data.getThaimtbId(), Field.Store.YES));
				doc.add(new TextField(LuceneConstant.TITLE_CONTENT, data.getDescription(), Field.Store.YES));
				doc.add(new IntField(LuceneConstant.URL_CODE, data.getUrlCode(), Field.Store.YES));
				doc.add(new TextField(LuceneConstant.URL_CONTENT_LINK, data.getUrlContentLink(), Field.Store.YES));
//				doc.add(new TextField(LuceneConstant.DATE_CONTENT_CREATE, data.getDateContentCreate(), Field.Store.YES));
				writer.addDocument(doc);
			}
			
			writer.commit();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
