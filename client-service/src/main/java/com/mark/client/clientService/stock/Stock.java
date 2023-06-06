package com.mark.client.clientService.stock;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StockType ticker;
    private String companyName;
    private double stockPrice;
    private int stockQuantity;
    private int portfolioId;
    public Stock(StockType ticker, int portfolioId) {
        this.ticker = ticker;
        this.companyName = ticker.getCompany();
        this.portfolioId = portfolioId;
    }
}