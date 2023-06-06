package com.mark.client.clientService.portfolio;

import com.mark.client.clientService.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Integer>{

    List<Portfolio> findAllByClientId(int clientId);
//    Portfolio getPortfolioByPortfolioId(int portfolioId);
////    Portfolio getPortfolioByPortfolioId(Integer portfolioId);
//////    Portfolio getPortfolioByName(PortfolioDTOName portfolioDTOName);
//////    Set<Stock> getPortfolioByPortfolioName(PortfolioDTOName portfolioDTOName);
}
