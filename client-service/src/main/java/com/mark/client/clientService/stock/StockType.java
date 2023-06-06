package com.mark.client.clientService.stock;

public enum StockType {
    MSFT("MICROSOFT"),
    NFLX("NETFLIX"),
    GOOGL("GOOGLE"),
    AAPL("APPLE"),
    TSLA("TESLA"),
    IBM("International Business Machines Corporation"),
    ORCL("ORACLE"),
    AMZN("AMAZON");

    private String company;

    StockType(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}