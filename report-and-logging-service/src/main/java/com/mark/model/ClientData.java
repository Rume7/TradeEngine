package com.mark.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {
    private int Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Integer accountId;
}
