package com.yvangak.batch.chunk.clients;

import com.yvangak.batch.chunk.clients.model.Franchise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class FranchiseFileHandlerTest {

    private String testFileName;
    private String testEmptyFile;
    private String testFileNameWithEmptyValues;
    private FranchiseFileHandler subject;
    private Franchise expected;

    @BeforeEach
    void setUp() {
        testFileName = "test-input.csv";
        testEmptyFile = "empty-test-input.csv";
        testFileNameWithEmptyValues = "test-input-with-missing-values.csv";

        expected = Franchise.builder()
                .franchiseName("Atlanta Hawks")
                .startYear(1968)
                .endYear(2019)
                .losses(2094)
                .wins(2052)
                .leagueTitles(0)
                .conferenceTitles(0)
                .divisionTitles(5)
                .build();
    }

    @Test
    void givenAFileWithContent_shouldReadAndReturnAConstructedFranchise() {
        subject = new FranchiseFileHandler(testFileName);
        Franchise actual = subject.readFranchise();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void givenAnEmptyFile_shouldTryToReadAndReturnNull() {
        subject = new FranchiseFileHandler(testEmptyFile);
        Franchise actual = subject.readFranchise();
        assertNull(actual);
    }

    @Test
    void givenAFileWithSomeEmptyColumnValues_shouldReadAndReturnAConstructedFranchiseWithNullFieldsThatCorrespondsToEmptyColumn() {
        subject = new FranchiseFileHandler(testFileNameWithEmptyValues);
        Franchise actual = subject.readFranchise();
        assertEquals("Atlanta Hawks", actual.getFranchiseName());
        assertEquals(new Integer(1968), actual.getStartYear());
        assertEquals(new Integer(2019), actual.getEndYear());
        assertNull(actual.getWins());
        assertNull(actual.getLosses());
        assertEquals(new Integer(5), actual.getDivisionTitles());
        assertEquals(new Integer(0), actual.getConferenceTitles());
        assertNull(actual.getLeagueTitles());
    }
}