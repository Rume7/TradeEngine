package com.mark.client.clientservice.account;
import com.mark.client.clientservice.user.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;
    @GetMapping("/get-current-balance")
    public ResponseEntity<Double> getCurrentBalance(){
        return ResponseEntity.ok(accountService.getCurrentBalance());
    }
    @PostMapping("/balance-after-sell/{amount}")
    public ResponseEntity<Double> balanceAfterSell(@PathVariable Double amount){
        return ResponseEntity.ok(accountService.balanceAfterSell(amount));
    }
    @PostMapping("/balance-after-buy/{amount}")
    public ResponseEntity<Double> balanceAfterBuy(@PathVariable Double amount){
        return ResponseEntity.ok(accountService.balanceAfterBuy(amount));
    }

}
