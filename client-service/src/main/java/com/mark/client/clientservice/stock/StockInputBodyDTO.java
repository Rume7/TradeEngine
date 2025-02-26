package com.mark.client.clientservice.stock;

public record StockInputBodyDTO(Integer portfolioId, StockType ticker) {
}