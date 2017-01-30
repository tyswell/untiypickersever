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
import org.springframework.batch.core.step.tasklet.Tasklet;
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
import com.tagtrade.batch.tasklet.ClearDBTasklet;
import com.tagtrade.batch.tasklet.GenLuceneIndexTasklet;
import com.tagtrade.batch.writer.CustomWriter;
import com.tagtrade.bean.BatchOutput;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ErSeachingMapContent;
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
		jdbc.setSql("select * from r_url where active = 'Y'");
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
	public ItemWriter<EContent> contentWriter(DataSource dataSource) {
		JdbcBatchItemWriter<EContent> writer = new JdbcBatchItemWriter<EContent>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<EContent>());
        writer.setSql("INSERT INTO `e_content`(`content_id`, `URL_CODE`, `facebook_grop_code`, `title`, `description`, `content_web_id`, `URL_CONTENT_LINK`, `DATE_CONTENT_CREATE`) VALUES (:contentId, :urlCode, :facebookGropCode, :title, :description, :contentWebId, :urlContentLink, :dateContentCreate)");
        writer.setDataSource(dataSource);
        return writer;
	}
	
	@Bean
	public ItemWriter<ErSeachingMapContent> searchingMapContentWriter(DataSource dataSource) {
		JdbcBatchItemWriter<ErSeachingMapContent> writer = new JdbcBatchItemWriter<ErSeachingMapContent>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ErSeachingMapContent>());
        writer.setSql("INSERT INTO `er_seaching_map_content` (`SEARCHING_ID`, `content_id`, `SCORE_HIT`) VALUES (:searchingId, :contentId, :scoreHit)");
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
	
	//------------------------------------------------------------------------------
	
	
	@Bean
	public Job clearDBJob(JobBuilderFactory jobs, Step stepClearDB) {
		return jobs.get("clearDBJob")
				.flow(stepClearDB)
				.end()
				.build();
	}
	
	@Bean
	public Step stepClearDB(StepBuilderFactory sbf) {
		return sbf.get("stepClearDB")
				.tasklet(clearDBTasklet())
				.build();
	}
	
	 @Bean
    public Tasklet clearDBTasklet() {
        return new ClearDBTasklet();
    }
	 
	//------------------------------------------------------------------------------
	 
		@Bean
		public Job genLuceneIndexJob(JobBuilderFactory jobs, Step stepGenLuceneIndex) {
			return jobs.get("genLuceneIndexJob")
					.flow(stepGenLuceneIndex)
					.end()
					.build();
		}
		
		@Bean
		public Step stepGenLuceneIndex(StepBuilderFactory sbf) {
			return sbf.get("stepGenLuceneIndex")
					.tasklet(genLuceneIndexTasklet())
					.build();
		}
		
		 @Bean
	    public Tasklet genLuceneIndexTasklet() {
	        return new GenLuceneIndexTasklet();
	    }


}
