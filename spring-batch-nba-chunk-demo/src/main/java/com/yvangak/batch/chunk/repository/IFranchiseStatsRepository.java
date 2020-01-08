package com.yvangak.batch.chunk.repository;

import com.yvangak.batch.chunk.domain.FranchiseStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranchiseStatsRepository extends JpaRepository<FranchiseStats, String> {
}
