package com.tcc.moradiaestudantil.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

	@Bean
	Job job(JobRepository jobRepository, Step step) {
		return new JobBuilder("cadastrarFaculdade", jobRepository).start(step).build();
	}
	
	@Bean
	Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, @Autowired @Name("taskCadastrarFaculdades") Tasklet task) {
		return new StepBuilder("step", jobRepository).tasklet(task, platformTransactionManager).build();
	}
}
 