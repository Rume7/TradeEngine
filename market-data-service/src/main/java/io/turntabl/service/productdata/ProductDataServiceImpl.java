package io.turntabl.service.productdata;

import io.turntabl.config.ExchangeUrlSettings;
import io.turntabl.models.productdata.ProductData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductDataServiceImpl implements ProductDataService {

    private final Logger logger = LoggerFactory.getLogger("Product Data Service: ");

    @Autowired
    private ExchangeUrlSettings exchangeUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Set<ProductData> getAllProductData() {
        String url = getUrl();
        logger.info("Get all product data method: ");
        ProductData[] products = restTemplate.getForObject(url, ProductData[].class);
        if (products == null) {
            return new HashSet<>();
        }
        return new HashSet<>(List.of(products));
    }

    @Override
    public ProductData getProductDataForATicker(String ticker) {
        logger.info("Get all product data for " + ticker);
        ResponseEntity<ProductData> product = restTemplate.getForEntity(getUrl(ticker), ProductData.class);
        if (product == null) return null;
        return product.getBody();
    }

    @Override
    public double getMaximumShiftPriceForATicker(String ticker) {
        logger.info("Get the maximum shift price for " + ticker);
        ProductData productData = getProductDataForATicker(ticker);
        if (productData != null) {
            return productData.MAX_PRICE_SHIFT();
        } else {
            return 0;       // throw Ticker isn't available error.
        }
    }

    @Override
    public void subscribeForProductData() {
        logger.info("Subscription for service: ") ;
        //restTemplate.getForEntity("", );

    }

    @Override
    public void unsubscribeForProductData() {

    }

    private String getUrl(String... args) {
        StringBuilder url = new StringBuilder(exchangeUrl.getExchangeMarketLink1());
        for (String arg: args) {
            url.append("/").append(arg);
        }
        return url.toString();
    }
}
