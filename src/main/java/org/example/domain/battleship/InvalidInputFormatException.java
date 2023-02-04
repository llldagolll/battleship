package org.example.domain.battleship;

import java.io.IOException;

public class InvalidInputFormatException extends IOException {
    public InvalidInputFormatException() {
        super("Error! Wrong ship location! Try again:");
    }
}
