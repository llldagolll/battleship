package org.example.domain.battleship;

import java.io.IOException;

public class PlacedShipTooCloseToAnotherShipException extends IOException {
    public PlacedShipTooCloseToAnotherShipException() {
        super("Error! You placed it too close to another one. Try again:");
    }
}
