package io.turntabl.service.orderbook;

import io.turntabl.config.ExchangeUrlSettings;
import io.turntabl.models.orderbook.FullOrderBook;
import io.turntabl.models.orderbook.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExchangeTickerService {

    private final Logger logger = LoggerFactory.getLogger(ExchangeTickerService.class);

    @Autowired
    private final RestTemplate restTemplate;

    private final ExchangeUrlSettings exchangeUrl;

    @Autowired
    ExchangeTickerService(RestTemplate restTemplate, ExchangeUrlSettings exchangeUrl) {
        this.restTemplate = restTemplate;
        this.exchangeUrl = exchangeUrl;
    }

    boolean validateTicker(String ticker) {
        if (ticker.isEmpty() || ticker == null) return false;
        return getAllTickers().contains(ticker);
    }

    Set<String> getAllTickers() {
         logger.info("Service: Retrieve all available tickers.");
         String url = exchangeUrl.getExchange1();
         FullOrderBook[] allFullOrderBooks = restTemplate.getForObject(url, FullOrderBook[].class);
         Set<String> allProducts = new HashSet<>();

         if (allFullOrderBooks != null && allFullOrderBooks.length != 0) {
            for (FullOrderBook orderBook : allFullOrderBooks) {
                List<OrderDTO> orderDTOs = orderBook.getFullOrderBook();
                if (orderDTOs != null) {
                    allProducts.add(orderDTOs.get(0).getProduct());
                }
            }
        }
        return allProducts;
    }
}
