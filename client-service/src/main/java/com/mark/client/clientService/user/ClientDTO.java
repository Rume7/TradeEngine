package com.mark.client.clientService.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.ToString;
import java.time.LocalDateTime;
@Builder
@ToString
public class ClientDTO {
    private int clientId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Integer accountId;
    private LocalDateTime dateRegistered ;
}
