package group.roygbiv.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private boolean hasCompletedInitialCardSwitch;
    private final List<Card> cards;
    public static final int MAX_CARDS = 10;

    // Constructor
    public Hand() {
        this.cards = new ArrayList<>();
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Getters, setters, and toString

    // ----------------------------------------------------------------------------------------------------------------

    // This method checks if the Hand is already full
    public boolean isFull() {
        return cards.size() == MAX_CARDS;
    }

    public void addCard(Card c) {
        if (cards.size() < MAX_CARDS) {
            cards.add(c);
        }
    }

    // This method takes in two parameters, replacing cardToRemove
    // with cardToAdd in this Hand
    public void replaceCard(Card cardToRemove, Card cardToAdd) {
        int location = cards.indexOf(cardToRemove);
        cards.remove(cardToRemove);
        cards.add(location, cardToAdd);
    }

    // This method checks if all Cards in this Hand are sorted ascending
    public boolean isInConsecutiveOrder() {
        int previousCardValue = cards.getFirst().getValue();
        for (Card c : cards) {
            if (c.getValue() < previousCardValue) {
                return false;
            }
            previousCardValue = c.getValue();
        }
        return true;
    }

    public void switchCards(Card c1, Card c2) {
        if (!cards.contains(c1) || !cards.contains(c2)) {
            return;
        }
        int index1 = cards.indexOf(c1);
        int index2 = cards.indexOf(c2);
        cards.remove(index1);
        cards.add(index1, c2);
        cards.remove(index2);
        cards.add(index2, c1);
        hasCompletedInitialCardSwitch = true;
    }
}
