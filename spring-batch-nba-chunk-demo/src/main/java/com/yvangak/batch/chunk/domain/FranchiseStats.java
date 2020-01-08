package com.yvangak.batch.chunk.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class FranchiseStats {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String franchiseName;
    private Integer yearsInActivity;
    private Double winsPercentage;
    private Double lossesPercentage;
    private Integer totalTitlesWon;
    private Boolean isActive;
}
