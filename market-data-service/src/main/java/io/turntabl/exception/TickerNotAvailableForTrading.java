package io.turntabl.exception;

public class TickerNotAvailableForTrading extends RuntimeException {

    public TickerNotAvailableForTrading(String message) {
        super(message);
    }
}
