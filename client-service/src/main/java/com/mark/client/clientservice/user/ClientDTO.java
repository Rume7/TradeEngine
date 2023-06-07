package com.mark.client.clientservice.user;

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
