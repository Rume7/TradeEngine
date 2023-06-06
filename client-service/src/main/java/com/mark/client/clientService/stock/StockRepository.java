package com.mark.client.clientService.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    List<Stock> findAllByPortfolioId(Integer portfolioId);
}