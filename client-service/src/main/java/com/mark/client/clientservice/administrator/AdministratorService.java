package com.mark.client.clientservice.administrator;
import com.mark.client.clientservice.user.Client;
import com.mark.client.clientservice.user.ClientRepo;
import com.mark.client.clientservice.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorService {
private final ClientRepo clientRepo;
    public List<Client> getAllClients(){
        return clientRepo.findAllByRole(Role.CLIENT);
    }

    // list all trades

}
