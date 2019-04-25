package cn.bjjoy.bms.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchTest {

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step buildDemoStep1(){
		return stepBuilderFactory.get("buildDemoStep1")
		.tasklet(new Tasklet(){
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
					throws Exception {
				System.out.println("buildDemoStep1");
				return null;
			}
		}).build();
	}
	
	@Bean
	public Step buildDemoStep2(){
		return stepBuilderFactory.get("buildDemoStep2")
		.tasklet(new Tasklet(){
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
					throws Exception {
				System.out.println("buildDemoStep2");
				return null;
			}
		}).build();
	}
	
	@Bean
	public Step buildDemoStep3(){
		return stepBuilderFactory.get("buildDemoStep3")
		.tasklet(new Tasklet(){
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
					throws Exception {
				System.out.println("buildDemoStep3");
				return null;
			}
		}).build();
	}
	
	@Bean
	public Flow buildDemoFlow(){
		return new FlowBuilder<Flow>("buildDemoFlow")
				.start(buildDemoStep1())
				.next(buildDemoStep2())
				.build();
	}
	
	@Bean
	public Job buildDemoJob(){
		return jobBuilderFactory.get("buildDemoJob")
				.start(buildDemoFlow())
				.next(buildDemoStep3())
				.end()
				.build();
	}
	
	
	@Autowired  
	private JobLauncher jobLauncher;  
	@Autowired  
	private Job job;  
	
	
	//https://weiqingfei.iteye.com/blog/2307165
//	@Scheduled(initialDelay=3000,fixedRate = 1000)  
	public void run(){  
	    try {  
	        JobExecution execution = jobLauncher.run(job, new JobParameters());  
	        System.out.println("Execution status: "+ execution.getStatus());  
	    } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException  
	            | JobParametersInvalidException e) {  
	        e.printStackTrace();  
	    }   
	}
	
	
}
