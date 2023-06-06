package io.turntabl.models.orderbook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
class Execution {
    private LocalDateTime timestamp;
    private double price;
    private int quantity;

}
