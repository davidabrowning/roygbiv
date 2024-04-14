package group.roygbiv.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<Card> drawPile;
    private Card drawnCard;
    private final List<Card> discardPile;
    @SuppressWarnings("FieldCanBeLocal")
    private final int DECK_SIZE = 60;

    public Deck() {
        // Initialize drawPile
        this.drawPile = new ArrayList<>();
        for (int i = 0; i < DECK_SIZE; i++) {
            drawPile.add(new Card(i));
        }

        // Initialize discardPile
        this.discardPile = new ArrayList<>();
        drawCard();
        discard(takeDrawnCard());
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Getters, setters, and toString
    public boolean hasCardBeenDrawn() { return drawnCard != null; }
    public int drawPileSize() { return drawPile.size(); }
    public int discardPileSize() { return discardPile.size(); }

    // ---------------------------------------------------------------------------------------------------------------

    // This method gets a Card from the drawPile without removing it
    // and sets the drawnCard variable to its value
    public Card drawCard() {
        Random rand = new Random();
        int randomCardNum = rand.nextInt(0, drawPile.size());
        drawnCard = drawPile.get(randomCardNum);
        return drawnCard;
    }

    // This method removes the drawnCard from the drawPile
    // and resets the drawPile if it is empty
    public Card takeDrawnCard() {
        Card c = drawnCard;
        drawnCard = null;
        drawPile.remove(c);

        if (drawPile.isEmpty()) {
            Card lastDiscardedCard = discardPile.getLast();
            drawPile.addAll(discardPile);
            discardPile.clear();
            discardPile.add(lastDiscardedCard);
            drawPile.remove(lastDiscardedCard);
        }

        return c;
    }

    // This method gets the Card at the top of the discardPile without removing it
    public Card getTopDiscardCard() {
        if (discardPile.isEmpty()) {
            return null;
        }
        return discardPile.getLast();
    }

    // This method removes the top discardPile card
    public Card takeDiscardCard() {
        Card c = discardPile.getLast();
        discardPile.remove(c);
        return c;
    }

    // This method adds a Card to the discardPile
    public void discard(Card c) { discardPile.add(c); }
}
