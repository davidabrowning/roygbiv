package group.roygbiv.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck();
    }

    @Test
    void drawPileIsInitiallyFiftyNine() {
        assertEquals(59, deck.drawPileSize());
    }

    @Test
    void discardPileIsInitiallyOne() {
        assertEquals(1, deck.discardPileSize());
    }

}