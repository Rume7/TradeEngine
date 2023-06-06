package com.mark.client.clientService.stock;

public record StockInputBodyDTO(Integer portfolioId, StockType ticker) {
}