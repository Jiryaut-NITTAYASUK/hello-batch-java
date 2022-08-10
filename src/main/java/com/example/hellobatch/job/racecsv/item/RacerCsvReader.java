package com.example.hellobatch.job.racecsv.item;

import com.example.hellobatch.model.Race;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class RacerCsvReader implements ItemReader<FlatFileItemReader<Race>> {

    @Bean("RacerCsvReader")
    @Override
    public FlatFileItemReader<Race> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return new FlatFileItemReaderBuilder<Race>()
                .name("RacerCsvReader")
                .resource(new ClassPathResource("race1_results.csv"))
                .delimited()
                .names("position", "pilot")
                .targetType(Race.class)
                .build();
    }

}
