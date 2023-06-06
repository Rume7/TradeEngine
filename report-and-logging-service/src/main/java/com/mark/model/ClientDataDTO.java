package com.mark.model;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@ToString
@RedisHash
public class ClientDataDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private int clientId;
    private LocalDateTime dateTime = LocalDateTime.now() ;
}
