package group.roygbiv.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int turnCounter;
    private final List<Player> players;
    private final Deck deck;
    private boolean isGameOver;

    public Game() {
        turnCounter = 0;
        deck = new Deck();
        players = new ArrayList<>();
        isGameOver = false;
    }

    public void advanceTurn() {
        turnCounter++;
        if (turnCounter >= players.size()) {
            turnCounter = 0;
        }
    }

    public Player getCurrentTurnPlayer() {
        return players.get(turnCounter);
    }

    public boolean isCurrentTurn(Player p) {
        return p == this.getCurrentTurnPlayer();
    }

    public boolean isGameOver() { return isGameOver; }

    public void initializePlayers() {
        players.clear();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayerNum(Player p) {
        if (players.contains(p)) {
            return players.indexOf(p);
        }
        return -1;
    }

    // This method deals Cards to the Players at the beginning of a game
    public void dealCards() {
        for (Player p : players) {
            Hand h = p.getHand();
            while (!h.isFull()) {
                deck.drawCard();
                h.addCard(deck.takeDrawnCard());
            }
        }
    }

    public void takeDrawnCard(Player p, Card cardToRemove) {
        Card drawnCard = deck.takeDrawnCard();
        p.getHand().replaceCard(cardToRemove, drawnCard);
        discard(cardToRemove);
        advanceTurn();
    }

    public void takeDiscardedCard(Player p, Card cardToRemove) {
        Card discardedCard = deck.takeDiscardCard();
        p.getHand().replaceCard(cardToRemove, discardedCard);
        discard(cardToRemove);
        advanceTurn();
    }

    public void swapCards(Player p, Card cardToRemove, Card cardToTake) {
        p.getHand().replaceCard(cardToRemove, cardToTake);
    }

    public void initializeDiscardPile() {
        deck.initializeDiscardPile();
    }
    public Card takeRandomDrawPileCard() { return deck.takeRandomCard(); }
    public Card getTopDiscardCard() { return deck.getTopDiscardCard(); }
    public boolean topDrawPileCardIsRevealed() { return deck.topDrawPileCardIsRevealed(); }
    public void addCardToDiscardPile(Card c) { deck.addCardToDiscardPile(c); }

    public void checkForVictory(Player p) {
        if (p.getHand().isInConsecutiveOrder()) {
            isGameOver = true;
        }
    }


}
