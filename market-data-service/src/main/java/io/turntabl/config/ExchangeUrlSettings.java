package io.turntabl.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("url")
public class ExchangeUrlSettings {

    @Value("${url.exchange1}")
    private String exchange1;

    @Value("${url.exchange2}")
    private String exchange2;

    @Value("${url.marketdata.exchange1}")
    private String exchangeMarketLink1;

    @Value("${url.marketdata.exchange2}")
    private String exchangeMarketLink2;

    public String getExchange1() {
        return exchange1;
    }

    public String getExchange2() {
        return exchange2;
    }

    public String getExchangeMarketLink1() {
        return exchangeMarketLink1;
    }

    public String getExchangeMarketLink2() {
        return exchangeMarketLink2;
    }

    @Override
    public String toString() {
        return "ExchangeUrlSettings{exchange1 = " + exchange1 +
                ", exchange2 = " + exchange2 + '}';
    }
}
