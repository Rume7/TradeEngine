package io.turntabl.models.orderbook;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonSerialize
public class FullOrderBook implements Serializable {

    private List<OrderDTO> fullOrderBook;

    public FullOrderBook() {
        this.fullOrderBook = new ArrayList<>();
    }

    public FullOrderBook(ArrayList<OrderDTO> fullOrderBook) {
        this.fullOrderBook = fullOrderBook;
    }
}
