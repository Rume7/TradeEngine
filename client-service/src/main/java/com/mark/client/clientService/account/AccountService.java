package com.mark.client.clientService.account;
import com.mark.client.clientService.user.Client;
import com.mark.client.clientService.user.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AccountService {
    private final ClientRepo clientRepo;
    private  final AccountRepo accountRepo;

    public Double getCurrentBalance(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Client client = clientRepo.findByEmail(userEmail).orElseThrow();
        System.out.println(client.getAccountId());
        Account account = accountRepo.findById(client.getAccountId()).orElseThrow();
        return account.getBalance();
    }

    // after the order service executes a successful sell,whatever money it earns ,
    // it will call this method and pass the amount here
    public Double balanceAfterSell(double amount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Client client = clientRepo.findByEmail(userEmail).orElseThrow();

        Account account = accountRepo.findById(client.getAccountId()).orElseThrow();
        account.setBalance(account.getBalance() + amount);

        accountRepo.save(account);
        return account.getBalance();
    }

    // after a buy is executed,whatever money involved will be passed inside this method when order p calls  it
    public Double balanceAfterBuy(double amount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Client client = clientRepo.findByEmail(userEmail).orElseThrow();
        Account account = accountRepo.findById(client.getAccountId()).orElseThrow();
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);
        return account.getBalance();
    }



}
