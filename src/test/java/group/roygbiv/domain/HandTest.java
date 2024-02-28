package group.roygbiv.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Hand emptyHand;
    Hand fullHand;


    @BeforeEach
    void setup() {
        emptyHand = new Hand();
        fullHand = new Hand();
        for (int i = 0; i < Hand.MAX_CARDS; i++) {
            fullHand.addCard(new Card(i));
        }
    }

    @Test
    void fullHandIsFull() {
        assertTrue(fullHand.isFull());
    }

    @Test
    void emptyHandIsFull() {
        assertFalse(emptyHand.isFull());
    }

    @Test
    void addCardIncreasesHandSize() {
        int initialHandSize = emptyHand.getCards().size();
        emptyHand.addCard(new Card(1));
        int currentHandSize = emptyHand.getCards().size();
        assertEquals(1, currentHandSize - initialHandSize);
    }

    @Test
    void maxHandSizeIsMAX_CARDS() {
        for (int i = 0; i < 30; i++) {
            emptyHand.addCard(new Card(i));
        }
        assertEquals(Hand.MAX_CARDS, emptyHand.getCards().size());
    }
}