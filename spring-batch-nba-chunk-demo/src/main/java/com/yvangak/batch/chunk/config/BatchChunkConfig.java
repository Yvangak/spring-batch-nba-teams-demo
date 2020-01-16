package com.yvangak.batch.chunk.config;

import com.yvangak.batch.chunk.batch.FranchiseProcessor;
import com.yvangak.batch.chunk.batch.FranchiseReader;
import com.yvangak.batch.chunk.batch.FranchiseWriter;
import com.yvangak.batch.chunk.clients.model.Franchise;
import com.yvangak.batch.chunk.domain.FranchiseStats;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchChunkConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private FranchiseReader franchiseReader;
    @Autowired
    private FranchiseProcessor franchiseProcessor;
    @Autowired
    private FranchiseWriter franchiseWriter;

    @Bean
    public Step processFranchise(ItemReader<Franchise> franchiseReader, ItemProcessor<Franchise,
            FranchiseStats> franchiseProcessor, ItemWriter<FranchiseStats> franchiseWriter) {
        return stepBuilderFactory.get("FranchiseStatsStep").<Franchise, FranchiseStats>chunk(5)
                .reader(franchiseReader)
                .processor(franchiseProcessor)
                .writer(franchiseWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("FranchiseStatsChunkJob")
                .incrementer(new RunIdIncrementer())
                .start(processFranchise(franchiseReader, franchiseProcessor, franchiseWriter))
                .build();
    }

}
