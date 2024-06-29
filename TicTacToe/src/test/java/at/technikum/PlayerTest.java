package at.technikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void markerIsX() {
        Player playerMarker = new Player('X');
        assertEquals('X', playerMarker.getMarker());
    }

    @Test
    void markerIsO() {
        Player playerMarker = new Player('O');
        assertEquals('O', playerMarker.getMarker());
    }
}