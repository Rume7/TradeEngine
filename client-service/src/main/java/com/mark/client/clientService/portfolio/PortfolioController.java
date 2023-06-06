package com.mark.client.clientService.portfolio;
import com.mark.client.clientService.stock.StockInputBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping("/create-portfolio")
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody PortfolioDTOName portfolioDTOName) {
        Portfolio createdPortfolio = portfolioService.createPortfolio(portfolioDTOName.portfolioName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPortfolio);
    }
    @PostMapping("/add-stock")
    public ResponseEntity<String> addStockToPortfolio(@RequestBody StockInputBodyDTO stockInputBodyDTO){
        String addedStock = portfolioService.addStockToPortfolio(stockInputBodyDTO.portfolioId(), stockInputBodyDTO.ticker());
        return ResponseEntity.status(HttpStatus.OK).body(addedStock);
    }
    @GetMapping("/list-all-portfolio/{clientId}")
    public ResponseEntity<List> viewPortfolios(@PathVariable Integer clientId){
        return ResponseEntity.ok(portfolioService.viewAllPortfolio(clientId));
    }

    @GetMapping("/list-stocks/{portfolioId}")
    public ResponseEntity<List> getStocks(@PathVariable("portfolioId") Integer portfolioId){
        return ResponseEntity.ok(portfolioService.listOfStocksInPortfolio(portfolioId));
    }

    // add the controller of the closing portfolio over here..


}