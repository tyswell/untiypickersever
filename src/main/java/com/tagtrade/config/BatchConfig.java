package com.tagtrade.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.tagtrade.batch.processor.CustomProcessor;
import com.tagtrade.batch.writer.CustomWriter;
import com.tagtrade.bean.BatchOutput;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;
import com.tagtrade.dataacess.entity.bean.ErSeachingMapThaimtb;
import com.tagtrade.dataacess.entity.bean.RUrl;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:application.properties")
public class BatchConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	@StepScope
	public JdbcCursorItemReader<RUrl> reader(DataSource dataSource) {
		JdbcCursorItemReader<RUrl> jdbc = new JdbcCursorItemReader<RUrl>();
		jdbc.setDataSource(dataSource);
		jdbc.setSql("select * from r_url");
		jdbc.setRowMapper(new BeanPropertyRowMapper<RUrl>(RUrl.class));
	
		return jdbc;
	}
	
	@Bean
	public ItemProcessor<RUrl, BatchOutput> processor() {
		return new CustomProcessor();
	}
	
	@Bean
	public ItemWriter<BatchOutput> writer() {
		return new CustomWriter();
	}
	
	@Bean
	public ItemWriter<EThaimtbContent> thaiMtbContentWriter(DataSource dataSource) {
		JdbcBatchItemWriter<EThaimtbContent> writer = new JdbcBatchItemWriter<EThaimtbContent>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<EThaimtbContent>());
        writer.setSql("INSERT INTO `e_thaimtb_content`(`THAIMTB_ID`, `URL_CODE`, `DESCRIPTION`, `URL_CONTENT_LINK`, `DATE_CONTENT_CREATE`) VALUES (:thaimtbId, :urlCode, :description, :urlContentLink, :dateContentCreate)");
        writer.setDataSource(dataSource);
        return writer;
	}
	
	@Bean
	public ItemWriter<ErSeachingMapThaimtb> searchingMapThaimtbWriter(DataSource dataSource) {
		JdbcBatchItemWriter<ErSeachingMapThaimtb> writer = new JdbcBatchItemWriter<ErSeachingMapThaimtb>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ErSeachingMapThaimtb>());
        writer.setSql("INSERT INTO `er_seaching_map_thaimtb` (`SEARCHING_ID`, `THAIMTB_ID`, `SCORE_HIT`) VALUES (:searchingId, :thaimtbId, :scoreHit)");
        writer.setDataSource(dataSource);
        return writer;
	}
	
	@Bean
	public Step step2(StepBuilderFactory sbf,
			ItemReader<RUrl> reader,
			ItemWriter<BatchOutput> writer,
			ItemProcessor<RUrl, BatchOutput> processor) {
		return sbf.get("step2")
				.<RUrl, BatchOutput> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public Job importUserJobx(JobBuilderFactory jobs, Step step2) {
		return jobs.get("importUserJobx")
				.flow(step2)
				.end()
				.build();
	}
	
	@Bean 
	public PlatformTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}
	
	@Bean
	public JobRepository jobRepository(DataSource ds, PlatformTransactionManager transactionManager) throws Exception {
		JobRepositoryFactoryBean jf = new JobRepositoryFactoryBean();
		jf.setDataSource(ds);
		jf.setTransactionManager(transactionManager);
		jf.setDatabaseType(env.getProperty("spring.datasource.platform"));
		return jf.getObject();
	}
	
	@Bean
	public JobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setJobRepository(jobRepository);
	    return simpleJobLauncher;
	}


}
