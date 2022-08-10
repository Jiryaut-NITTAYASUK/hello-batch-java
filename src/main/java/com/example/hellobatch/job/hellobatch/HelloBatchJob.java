package com.example.hellobatch.job.hellobatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloBatchJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public HelloBatchJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        super();
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    public Step helloBatchStep() {
        return stepBuilderFactory.get("HelloBatchStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("HELLO BATCH");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean("HELLO_BATCH_JOB")
    public Job job() {
        return jobBuilderFactory.get("HELLO_BATCH_JOB")
                .start(helloBatchStep())
                .build();
    }
}
