package com.yvangak.batch.chunk.batch;

import com.yvangak.batch.chunk.domain.FranchiseStats;
import com.yvangak.batch.chunk.repository.IFranchiseStatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FranchiseWriter implements ItemWriter<FranchiseStats>, StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseWriter.class);

    private IFranchiseStatsRepository iFranchiseStatsRepository;

    @Autowired
    public FranchiseWriter(IFranchiseStatsRepository iFranchiseStatsRepository) {
        this.iFranchiseStatsRepository = iFranchiseStatsRepository;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.debug("[Completed] FranchiseWriter-beforeStep ");
    }

    @Override
    public void write(List<? extends FranchiseStats> items) {
        if (!items.isEmpty()) {
            for (FranchiseStats stat : items) {
                iFranchiseStatsRepository.save(stat);
            }

            LOGGER.debug("[Completed] FranchiseWriter-write all items in the chunk written");
        } else {
            LOGGER.debug("[Completed] FranchiseWriter-write This chunk was empty");
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.debug("[Completed] FranchiseWriter-afterStep");
        return ExitStatus.COMPLETED;
    }

}
