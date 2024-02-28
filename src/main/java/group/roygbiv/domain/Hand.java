package group.roygbiv.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;
    public static final int MAX_CARDS = 10;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public boolean isFull() {
        return cards.size() == MAX_CARDS;
    }

    public void addCard(Card c) {
        if (cards.size() < MAX_CARDS) {
            cards.add(c);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void replaceCard(Card cardToRemove, Card cardToAdd) {
        int location = cards.indexOf(cardToRemove);
        cards.remove(cardToRemove);
        cards.add(location, cardToAdd);
    }



}