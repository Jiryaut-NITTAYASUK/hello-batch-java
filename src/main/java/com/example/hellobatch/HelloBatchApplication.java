package com.example.hellobatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class HelloBatchApplication {

//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job job;

    public static void main(String[] args) {
        SpringApplication.run(HelloBatchApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        JobExecution execution = jobLauncher.run(job, new JobParameters());
//        System.out.println("STATUS :: " +execution.getStatus());
//    }
}
