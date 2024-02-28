package group.roygbiv.domain;

import group.roygbiv.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int turnCounter;
    private final List<Player> players;
    private final Deck deck;

    public Game() {
        turnCounter = 0;
        deck = new Deck();
        players = new ArrayList<>();
    }

    public void advanceTurn() {
        turnCounter++;
        if (turnCounter >= players.size()) {
            turnCounter = 0;
        }
    }

    public void revealTopDrawPileCard() { deck.revealTopDrawPileCard(); }

    public Player getCurrentTurnPlayer() {
        return players.get(turnCounter);
    }

    public boolean isCurrentTurn(Player p) {
        return p == this.getCurrentTurnPlayer();
    }

    public void initializeDeckAndPlayers() {
        deck.reset();
        players.clear();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
    }

    public int numPlayers() {
        return players.size();
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

    public Player getPlayer(int playerNum) {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(playerNum);
    }

    public void dealCards() {
        for (Player p : players) {
            Hand h = p.getHand();
            while (!h.isFull()) {
                h.addCard(deck.takeRandomCard());
            }
        }
    }

    public void playerTookTopDrawPileCard() {
        deck.unrevealTopDrawPileCard();
        advanceTurn();
    }

    public void swapForTopDiscardCard(Player p, Card c) {
        Card currentDiscardCard = deck.getTopDiscardCard();
        p.getHand().replaceCard(c, currentDiscardCard);
        deck.replaceTopDiscardCard(c);
        advanceTurn();
    }

    public void initializeDiscardPile() {
        deck.initializeDiscardPile();
    }
    public Deck getDeck() { return deck; }
    public Card takeRandomDrawPileCard() { return deck.takeRandomCard(); }
    public Card getTopDiscardCard() { return deck.getTopDiscardCard(); }
    public boolean topDrawPileCardIsRevealed() { return deck.topDrawPileCardIsRevealed(); }



}