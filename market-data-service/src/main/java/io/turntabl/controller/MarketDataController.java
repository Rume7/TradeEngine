package io.turntabl.controller;

import io.turntabl.models.orderbook.FullOrderBook;
import io.turntabl.models.orderbook.OrderDTO;
import io.turntabl.service.orderbook.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/orderbook")
public class MarketDataController {

    private final Logger controllerLogger = LoggerFactory.getLogger(MarketDataController.class);
    private final ExchangeService exchangeService;

    @Autowired
    public MarketDataController(ExchangeService exchangeServiceImpl) {
        this.exchangeService = exchangeServiceImpl;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderBook() {
        controllerLogger.info("Controller: get all orders from exchange");
        FullOrderBook[] allOrders = exchangeService.getOrderBook();
        return ResponseEntity.status(HttpStatus.OK).body(allOrders);
    }

    @GetMapping(value = "/tickers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvailableTickers() {
        controllerLogger.info("Controller: All available tickers from the exchange");
        Set<String> allTickers = exchangeService.getAllTickers();
        return ResponseEntity.status(HttpStatus.OK).body(allTickers);
    }

    @GetMapping(value = "/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderBookOfTicker(@PathVariable("ticker") String ticker) {
        controllerLogger.info("Controller: order book of " + ticker);
        OrderDTO[] tickerOrderBook = exchangeService.getOrderBookForATicker(ticker);
        return ResponseEntity.status(HttpStatus.OK).body(tickerOrderBook);
    }

    @GetMapping(value = "/{ticker}/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderBookTickerWithBuyDetails(@PathVariable("ticker") String ticker) {
        controllerLogger.info("Controller: Buy orders for " + ticker);
        OrderDTO[] response = exchangeService.getOrderBookForATickerWithBuyOrSell(ticker, "buy");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{ticker}/sell", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderBookTickerWithSellDetails(@PathVariable("ticker") String ticker) {
        controllerLogger.info("Controller: Sell orders for " + ticker);
        OrderDTO[] response = exchangeService.getOrderBookForATickerWithBuyOrSell(ticker, "sell");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value="/all/{ticker}/{side}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllExecutedOrders(@PathVariable("ticker") String ticker,
                                                  @PathVariable("side") String side) {
        controllerLogger.info("Controller: for all executed orders.");
        List<OrderDTO>  allExecutedOrders = exchangeService.getAllExecutedOrders(ticker, side);
        return ResponseEntity.status(HttpStatus.OK).body(allExecutedOrders);
    }

    @GetMapping(value="/last/{ticker}/{side}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLastExecutedOrder(@PathVariable("ticker") String ticker,
                                                  @PathVariable("side") String side) {
        controllerLogger.info("Controller: get last executed order for " + ticker);
        OrderDTO orderDTO = exchangeService.getLastExecutedOrder(ticker, side);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }
}
