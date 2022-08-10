package com.example.hellobatch.job.racecsv.item;

import com.example.hellobatch.model.Race;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class RacerCsvWriter {

    @Autowired
    private DataSource dataSource;

    @Bean("RacerCsvWriter")
    public JdbcBatchItemWriter<Race> raceJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Race> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO CHAMPIONSHIP(position, pilot) VALUES(:position, :pilot)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }
}
