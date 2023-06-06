package io.turntabl.service.productdata;

import io.turntabl.models.productdata.ProductData;

import java.util.Set;

public interface ProductDataService {

    Set<ProductData> getAllProductData();

    ProductData getProductDataForATicker(String ticker);

    double getMaximumShiftPriceForATicker(String ticker);

    void subscribeForProductData() ;

    void unsubscribeForProductData();

}
