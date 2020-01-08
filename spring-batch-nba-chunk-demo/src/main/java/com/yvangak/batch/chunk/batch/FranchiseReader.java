package com.yvangak.batch.chunk.batch;

import com.yvangak.batch.chunk.clients.FranchiseFileHandler;
import com.yvangak.batch.chunk.clients.model.Franchise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class FranchiseReader implements ItemReader<Franchise>, StepExecutionListener {
    private Logger logger = LoggerFactory.getLogger(FranchiseReader.class);
    private FranchiseFileHandler franchiseFileHandler;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        franchiseFileHandler = new FranchiseFileHandler("files/teams.csv");
        logger.info("[Completed] FranchiseReader-beforeStep franchise file reader initialized");
    }

    @Override
    public Franchise read() {
        Franchise franchise = franchiseFileHandler.readFranchise();

        if (franchise != null)
            logger.info("[Completed] FranchiseReader-read first record read records read ");

        return franchise;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        franchiseFileHandler.closeFranchiseFileReader();

        logger.info("[Completed] FranchiseReader-afterStep franchise file reader closed ");

        return ExitStatus.COMPLETED;
    }
}
