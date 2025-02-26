package io.turntabl.controller;
import static org.mockito.Mockito.when;
import io.turntabl.models.orderbook.FullOrderBook;
import io.turntabl.service.orderbook.ExchangeService;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MarketDataController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MarketDataControllerTest {
    @MockBean
    private ExchangeService exchangeService;

    @Autowired
    private MarketDataController marketDataController;
    @Test
    public void testGetOrderBook() throws Exception {
        // Arrange
        when(exchangeService.getOrderBook()).thenReturn(new FullOrderBook[]{new FullOrderBook()});
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orderbook/");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(marketDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"fullOrderBook\":[]}]"));
    }
    @Test
    public void testGetAllExecutedOrders() throws Exception {
        // Arrange
        when(exchangeService.getAllExecutedOrders(Mockito.anyString(), Mockito.anyString())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/v1/orderbook/all/{ticker}/{side}", "Ticker", "Side");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(marketDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    @Test
    public void testGetAvailableTickers() throws Exception {
        // Arrange
        when(exchangeService.getAllTickers()).thenReturn(new HashSet<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/orderbook/tickers");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(marketDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

