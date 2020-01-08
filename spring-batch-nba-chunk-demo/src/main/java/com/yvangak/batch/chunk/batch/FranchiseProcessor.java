package com.yvangak.batch.chunk.batch;

import com.yvangak.batch.chunk.clients.model.Franchise;
import com.yvangak.batch.chunk.domain.FranchiseStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static com.yvangak.batch.chunk.batch.utility.BatchHelperCalculator.*;

@Component
public class FranchiseProcessor implements ItemProcessor<Franchise, FranchiseStats>, StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseProcessor.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.debug("[Completed] FranchiseProcessor-beforeStep Franchise Processor initialized");
    }

    @Override
    public FranchiseStats process(Franchise item) {
        return FranchiseStats.builder()
                .franchiseName(item.getFranchiseName())
                .yearsInActivity(computeYearsInActivity(item.getStartYear(), item.getEndYear()))
                .isActive(isStillActive(item.getEndYear()))
                .lossesPercentage(computePercentage(item.getLosses(), item.getWins()))
                .winsPercentage(computePercentage(item.getWins(), item.getLosses()))
                .totalTitlesWon(computeTotal(item.getConferenceTitles(),
                        item.getDivisionTitles(),
                        item.getLeagueTitles()))
                .build();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.debug("[Completed] FranchiseProcessor-afterStep Processor after step completed");
        return ExitStatus.COMPLETED;
    }
}
