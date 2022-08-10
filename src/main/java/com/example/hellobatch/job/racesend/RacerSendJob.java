package com.example.hellobatch.job.racesend;

import com.example.hellobatch.job.racesend.item.RacerSendProcessor;
import com.example.hellobatch.job.racesend.item.RacerSendWriter;
import com.example.hellobatch.model.Race;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RacerSendJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Qualifier("RacerSendReader")
    @Autowired
    private ItemReader<? extends Race> racerSendReader;

    @Autowired
    private RacerSendProcessor racerSendProcessor;

    @Autowired
    private RacerSendWriter racerSendWriter;

    private Step racerSenderStep() {
        return stepBuilderFactory.get("racerSendStep").<Race, Race>chunk(1000)
                .reader(racerSendReader)
                .processor(racerSendProcessor)
                .writer(racerSendWriter)
                .build();
    }

    @Bean("RacerSendJob")
    public Job job() {
        return jobBuilderFactory.get("RacerSendJob")
                .start(racerSenderStep())
                .build();
    }

}
