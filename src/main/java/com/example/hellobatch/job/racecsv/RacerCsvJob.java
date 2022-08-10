package com.example.hellobatch.job.racecsv;

import com.example.hellobatch.job.racecsv.item.RacerCsvProcessor;
import com.example.hellobatch.job.racecsv.item.RacerCsvReader;
import com.example.hellobatch.model.Race;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RacerCsvJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Qualifier("RacerCsvReader")
    @Autowired
    private FlatFileItemReader<Race> flatFileItemReader;

    @Autowired
    private RacerCsvProcessor racerCsvProcessor;

    @Qualifier("RacerCsvWriter")
    @Autowired
    private JdbcBatchItemWriter<Race> raceJdbcBatchItemWriter;

    public Step racerCsvStep() {
        return stepBuilderFactory.get("RacerCsvStep").<Race, Race>chunk(5)
                .reader(flatFileItemReader)
                .processor(racerCsvProcessor)
                .writer(raceJdbcBatchItemWriter)
                .build();
    }

    @Bean("RacerCsvJob")
    public Job job() {
        return jobBuilderFactory.get("RacerCsvJob")
                .start(racerCsvStep())
                .build();
    }
}
