package com.mark.client.clientservice.portfolio1;

import com.mark.client.clientservice.stock.Stock;
import lombok.Data;

import java.util.List;

@Data
public class Portfolio {

    private String portfolioName;
    private List<Stock> stockList;
    private double portfolioWorth;
}
