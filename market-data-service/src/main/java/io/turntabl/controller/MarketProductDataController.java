package io.turntabl.controller;

import io.turntabl.models.productdata.ProductData;
import io.turntabl.service.productdata.ProductDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/product")
public class MarketProductDataController {

    private final Logger controllerLogger = LoggerFactory.getLogger(MarketProductDataController.class);

    private final ProductDataService productDataService;

    public MarketProductDataController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @PostMapping(value = "/pd/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
    public void subscribeToMarketData() {
        productDataService.subscribeForProductData();
    }

    @DeleteMapping(value = "/pd/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
    public void unsubscribeToMarketData() {
        productDataService.unsubscribeForProductData();
    }

    @GetMapping(value = "/pd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProductData() {
        Set<ProductData> allProductData = productDataService.getAllProductData();
        return ResponseEntity.status(HttpStatus.OK).body(allProductData);
    }

    @GetMapping(value = "/pd/1/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductData> getProductDataFromExchange1(@PathVariable("ticker") String ticker) {
        ProductData product = productDataService.getProductDataForATicker(ticker);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping(value = "/pd/2/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductData> getProductDataFromExchange2(@PathVariable("ticker") String ticker) {
        ProductData product = productDataService.getProductDataForATicker(ticker);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
