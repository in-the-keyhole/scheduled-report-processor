package com.solera.scheduledreportprocessor;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job sendScheduledReports(JobCompletionNotificationListener listener, Step sendEmail) {
        return jobBuilderFactory.get("sendScheduledReports")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(sendEmail)
                .end()
                .build();
    }

    /*
     * Send the email
     */
    @Bean
    public Step sendEmail() {
        return stepBuilderFactory.get("sendEmail")
                .<ScheduledReport, ScheduledReport>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    /*
     * Reader
     * 
     * Read the scheduled report data by opening a cursor and 'streaming'
     * the rows in batch
     */
    @Bean
    public JdbcCursorItemReader<ScheduledReport> reader() {
        JdbcCursorItemReader<ScheduledReport> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        // TODO - change SQL as needed
        reader.setSql("select name from scheduled_report");
        reader.setRowMapper(new ScheduledReportResultRowMapper());
        reader.setMaxRows(10);
        reader.setFetchSize(10);
        reader.setQueryTimeout(10000);
        return reader;
    }

    /*
     * Item Processor
     */
    public ScheduledReportItemProcessor processor() {
        return new ScheduledReportItemProcessor();
    }

    /*
     * Writer
     * 
     * Stubbed this out to flat file just to test the batch flow
     * 
     * TODO - This will need to change to a different writer implementation - i.e.
     * SimpleMailMessageItemWriter, MimeMessageItemWriter, or a custom writer
     */
    @Bean
    public FlatFileItemWriter<ScheduledReport> writer() {
        FlatFileItemWriter<ScheduledReport> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("batch-output-data.csv"));
        writer.setLineAggregator(getDelimitedLineAggregator());
        return writer;
    }

    // TODO - Remove this -- no longer be needed after FlatFileItemWriter is removed
    private DelimitedLineAggregator<ScheduledReport> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<ScheduledReport> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<ScheduledReport>();
        beanWrapperFieldExtractor.setNames(new String[] { "name" });
        DelimitedLineAggregator<ScheduledReport> aggregator = new DelimitedLineAggregator<ScheduledReport>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return aggregator;
    }

}