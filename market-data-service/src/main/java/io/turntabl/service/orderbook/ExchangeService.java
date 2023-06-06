package io.turntabl.service.orderbook;

import io.turntabl.models.orderbook.FullOrderBook;
import io.turntabl.models.orderbook.OrderDTO;

import java.util.List;
import java.util.Set;

public interface ExchangeService {

    Set<String> getAllTickers();

    FullOrderBook[] getOrderBook() ;

    OrderDTO[] getOrderBookForATicker(String ticker);

    OrderDTO[] getOrderBookForATickerWithBuyOrSell(String ticker, String action);

    List<OrderDTO> getAllExecutedOrders(String ticker, String action);

    OrderDTO getLastExecutedOrder(String ticker, String action);
}
