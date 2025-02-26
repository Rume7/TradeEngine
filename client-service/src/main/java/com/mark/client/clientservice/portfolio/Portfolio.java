package com.mark.client.clientservice.portfolio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int portfolioId;

    @Enumerated(EnumType.STRING)
    private PortfolioName portfolioName;

    private LocalDateTime dateCreated =  LocalDateTime.now();

    private double portfolioWorth ;

    private int clientId;

    public Portfolio(PortfolioName portfolioName, int clientId) {
        this.portfolioName = portfolioName;
        this.clientId = clientId;
    }

}