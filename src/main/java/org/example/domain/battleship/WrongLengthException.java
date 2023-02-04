package org.example.domain.battleship;

import java.io.IOException;

public class WrongLengthException extends IOException {

    public WrongLengthException(String ship) {
        super("Error! Wrong length of the " + ship + "! Try again:");
    }
}
