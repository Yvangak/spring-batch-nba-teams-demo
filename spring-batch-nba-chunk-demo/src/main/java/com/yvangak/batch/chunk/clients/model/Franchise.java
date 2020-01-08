package com.yvangak.batch.chunk.clients.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Franchise implements Serializable {
    private String franchiseName;
    private Integer startYear;
    private Integer endYear;
    private Integer wins;
    private Integer losses;
    private Integer divisionTitles;
    private Integer conferenceTitles;
    private Integer leagueTitles;
}
