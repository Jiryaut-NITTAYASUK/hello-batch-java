package com.example.hellobatch.controller;

import com.example.hellobatch.job.hellobatch.HelloBatchJob;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/trigger/{jobName}")
    public String triggerJob (@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Job job = (Job) applicationContext.getBean(jobName);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("timestamp", LocalDateTime.now().toString())
                .addString("work", jobName)
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        System.out.println("STATUS ::" + jobExecution.getStatus());
        return "200-OK";
    }

    @Scheduled( cron = "0/20 * * * * *")
    public void run() throws Exception {
        Job job = (Job) applicationContext.getBean("HELLO_BATCH_JOB");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .addString("pilot", "C")
                .toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);
        System.out.println("STATUS :: " + execution.getStatus());
    }

}
