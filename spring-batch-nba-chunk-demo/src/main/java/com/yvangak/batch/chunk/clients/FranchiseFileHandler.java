package com.yvangak.batch.chunk.clients;

import com.opencsv.CSVReader;
import com.yvangak.batch.chunk.clients.model.Franchise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class FranchiseFileHandler {
    private final static Logger logger = LoggerFactory.getLogger(FranchiseFileHandler.class);

    private String fileName;
    private File file;
    private FileReader fileReader;
    private CSVReader csvReader;

    public FranchiseFileHandler(String fileName) {
        this.fileName = fileName;
    }

    public Franchise readFranchise() {
        Franchise franchise = null;
        try {
            if (csvReader == null) {
                initializeFranchiseFileReader();
            }
            String[] franchiseLine = csvReader.readNext();

            if (franchiseLine != null && franchiseLine.length == 8) {
                franchise = Franchise.builder()
                        .franchiseName(franchiseLine[0])
                        .startYear(franchiseLine[1] != null ? getNumericValue(franchiseLine[1]) : null)
                        .endYear(franchiseLine[2] != null ? getNumericValue(franchiseLine[2]) : null)
                        .wins(franchiseLine[3] != null ? getNumericValue(franchiseLine[3]) : null)
                        .losses(franchiseLine[4] != null ? getNumericValue(franchiseLine[4]) : null)
                        .divisionTitles(franchiseLine[5] != null ? getNumericValue(franchiseLine[5]) : null)
                        .conferenceTitles(franchiseLine[6] != null ? getNumericValue(franchiseLine[6]) : null)
                        .leagueTitles(franchiseLine[7] != null ? getNumericValue(franchiseLine[7]) : null)
                        .build();
            }
        } catch (Exception ex) {
            logger.error("[Error] readFranchise 500 couldn't read franchise {}",
                    ex.getMessage());
        }

        return franchise;
    }

    public void closeFranchiseFileReader() {
        try {
            if (csvReader != null) {
                csvReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        } catch (IOException ex) {
            logger.error("[Error] closeFranchiseFileReader 500 can't close the file reader and csv reader: {}",
                    ex.getMessage());
        }
    }

    private void initializeFranchiseFileReader() throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        if (file == null) {
            file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        }
        if (fileReader == null) {
            fileReader = new FileReader(file);
        }
        if (csvReader == null) {
            csvReader = new CSVReader(fileReader);
        }
    }

    private Integer getNumericValue(String text) {
        Integer value = null;
        if (text.isEmpty()) {
            return value;
        }
        try {
            value = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            logger.error("[Error] getNumericValue 500 couldn't get the number value. Ex: {}",
                    ex.getMessage());
        }
        return value;
    }
}
