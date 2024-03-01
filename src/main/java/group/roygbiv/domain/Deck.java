package group.roygbiv.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<Card> drawPile;
    private Card drawnCard;
    private final List<Card> discardPile;
    private final int DECK_SIZE = 60;

    public Deck() {
        this.topDrawPileCardIsRevealed = false;
        this.drawPile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.reset();
    }

    public void reset() {
        drawPile.clear();
        discardPile.clear();
        for (int i = 0; i < DECK_SIZE; i++) {
            drawPile.add(new Card(i));
        }
    }

    public void initializeDiscardPile() {
        Card c = takeRandomCard();
        discardPile.add(c);
    }

    public int drawPileSize() {
        return drawPile.size();
    }

    public int discardPileSize() {
        return discardPile.size();
    }

    public Card takeRandomCard() {
        Random rand = new Random();
        int randomCardNum = rand.nextInt(0, drawPile.size());
        Card randomCard = drawPile.get(randomCardNum);
        drawPile.remove(randomCard);
        return randomCard;
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

    public void addCardToDiscardPile(Card c) { discardPile.add(c); }

    public void replaceTopDiscardCard(Card c) {
        discardPile.removeLast();
        discardPile.add(c);
    }

    public void revealTopDrawPileCard() { topDrawPileCardIsRevealed = true; }
    public void unrevealTopDrawPileCard() { topDrawPileCardIsRevealed = false; }

    public boolean topDrawPileCardIsRevealed() { return topDrawPileCardIsRevealed; }

}
