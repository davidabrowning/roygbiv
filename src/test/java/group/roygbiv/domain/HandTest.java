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

    @Test
    void replaceCardDoesNotChangeHandSize() {
        int startingHandSize = fullHand.getCards().size();
        Card cardToRemove = fullHand.getCards().get(3);
        Card cardToAdd = new Card(45);
        fullHand.replaceCard(cardToRemove, cardToAdd);
        int endingHandSize = fullHand.getCards().size();
        assertEquals(startingHandSize, endingHandSize);
    }

    @Test
    void replaceCardPutsNewCardAtIntendedLocation() {
        Card cardToRemove = fullHand.getCards().get(4);
        Card cardToAdd = new Card(36);
        fullHand.replaceCard(cardToRemove, cardToAdd);
        assertEquals(cardToAdd, fullHand.getCards().get(4));
    }

    @Test
    void isInConsecutiveOrderTrueWhenCardsSorted() {
        emptyHand.addCard(new Card(1));
        emptyHand.addCard(new Card(2));
        emptyHand.addCard(new Card(3));
        emptyHand.addCard(new Card(4));
        assertTrue(emptyHand.isInConsecutiveOrder());
    }

    @Test
    void isInConsecutiveOrderFalseWhenCardsNotSorted() {
        emptyHand.addCard(new Card(1));
        emptyHand.addCard(new Card(3));
        emptyHand.addCard(new Card(2));
        emptyHand.addCard(new Card(4));
        assertFalse(emptyHand.isInConsecutiveOrder());
    }
}