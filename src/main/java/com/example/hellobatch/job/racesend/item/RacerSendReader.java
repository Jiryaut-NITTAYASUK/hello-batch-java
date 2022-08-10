package com.example.hellobatch.job.racesend.item;

import com.example.hellobatch.model.Race;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.H2PagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.HashMap;

@Component
public class RacerSendReader implements ItemReader<JdbcPagingItemReader<Race>> {

    @Autowired
    private DataSource dataSource;

    @Bean("RacerSendReader")
    @Override
    public JdbcPagingItemReader<Race> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return new JdbcPagingItemReaderBuilder<Race>()
                .name("RacerSendReader")
                .dataSource(dataSource)
                .pageSize(1000)
                .fetchSize(1000)
                .queryProvider(queryProvider())
                .rowMapper(new BeanPropertyRowMapper<>(Race.class))
                .build();
    }

    private H2PagingQueryProvider queryProvider() {
        H2PagingQueryProvider queryProvider = new H2PagingQueryProvider();
        queryProvider.setSelectClause("SELECT * ");
        queryProvider.setFromClause("FROM CHAMPIONSHIP");

        Map<String, Order> sort = new HashMap<>();
        sort.put("POSITION", Order.ASCENDING);

        queryProvider.setSortKeys(sort);

        return queryProvider;
    }
}
