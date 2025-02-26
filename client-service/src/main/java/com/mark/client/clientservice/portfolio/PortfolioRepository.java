package com.mark.client.clientservice.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Integer>{

    List<Portfolio> findAllByClientId(int clientId);
//    Portfolio getPortfolioByPortfolioId(int portfolioId);
////    Portfolio getPortfolioByPortfolioId(Integer portfolioId);
//////    Portfolio getPortfolioByName(PortfolioDTOName portfolioDTOName);
//////    Set<Stock> getPortfolioByPortfolioName(PortfolioDTOName portfolioDTOName);
}
