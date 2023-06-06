package com.mark.client.clientService.portfolio;
import com.mark.client.clientService.user.Client;
import com.mark.client.clientService.user.ClientRepo;
import com.mark.client.clientService.stock.Stock;
import com.mark.client.clientService.stock.StockRepository;
import com.mark.client.clientService.stock.StockType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final ClientRepo clientRepo;
    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;
    public Portfolio createPortfolio(PortfolioName portfolioName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Client client = clientRepo.findByEmail(userEmail).orElseThrow();
        Portfolio portfolio1 = new Portfolio(portfolioName, client.getClientId());
        portfolioRepository.save(portfolio1);
        return portfolio1;
    }

    public String addStockToPortfolio(Integer portfolioId, StockType stockType){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Client client = clientRepo.findByEmail(userEmail).orElseThrow();
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow();

        if(portfolio.getClientId() == client.getClientId()){
            Stock stock = new Stock(stockType, portfolioId);
            stockRepository.save(stock);
            return stock.toString();
        }
        else {
            return "Stock Type is not available on our platform";
        }
    }


//    //TODO : after adding a stock to a portfolio, the worth on the portfolio needs to be updated
//    public Stock addStockToPortfolio(Integer portfolioId, StockType stockType) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//
//        Client client = clientRepo.findByEmail(userEmail).orElseThrow();
//        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow();
//
//        if (portfolio.getClientId() == (client.getClientId())) {
//            Stock existingStock = stockRepository.findByStockTypeAndPortfolioId(stockType, portfolioId);
//            if (existingStock != null) {
//                existingStock.setStockQuantity(existingStock.getStockQuantity() + 1);
//                stockRepository.save(existingStock);
//                return existingStock;
//            } else {
//                Stock newStock = new Stock(stockType, portfolioId);
//                stockRepository.save(newStock);
//                return newStock;
//            }
//        } else {
//            throw new RuntimeException("Stock Type is not available on our platform") ;
//        }
//    }


    public List viewAllPortfolio(Integer clientId){
        return portfolioRepository.findAllByClientId(clientId);
    }

    public List listOfStocksInPortfolio(Integer portfolioId){
        List <Stock> listOfStocks = stockRepository.findAllByPortfolioId(portfolioId);
        return listOfStocks;
    }



    // close a portfolio
//    public String closePortfolio(Integer portfolioId){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//
//        Client user = clientRepo.findByEmail(userEmail).orElseThrow();
//        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow();
//
//        if (Objects.equals(portfolio.getClientId(), user.getClientId())) {
//            // I need to add the logic to sell the stocks in the portfolio at the current market price
//            stockRepository.deleteById(portfolioId);
//        }
//        {
//            return "";
//        }
//    }

}