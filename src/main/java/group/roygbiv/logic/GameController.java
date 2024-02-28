package group.roygbiv.logic;

import group.roygbiv.domain.*;
import group.roygbiv.ui.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Button;

public class GameController {

    private final RoygbivApplication application;
    private final Game game;
    private final Map<Button, Card> buttonCardMap;
    private Card desiredCard;

    public GameController(RoygbivApplication application) {
        this.application = application;
        this.game = new Game();
        this.buttonCardMap = new HashMap<>();
        desiredCard = null;
    }

    public void start() {
        game.initializeDeckAndPlayers();
        game.dealCards();
        game.initializeDiscardPile();
        application.buildGUI();
    }

    public void mapButtonToCard(Button b, Card c) {
        buttonCardMap.put(b, c);
    }

    public void handleDrawPileHover(Button b) {
        if (isGameOver()) {
            return;
        }
        application.highlightButton(b);
    }

    public void handleDrawPileMouseExit(Button b) {
        if (game.topDrawPileCardIsRevealed()) {
            return;
        }
        application.unhighlightButton(b);
    }

    public void handleDrawPileClick() {
        if (isGameOver()) {
            return;
        }
        if (game.topDrawPileCardIsRevealed()) {
            return;
        }
        application.unhighlightDiscardPile();
        application.highlightDrawPile();
        game.revealTopDrawPileCard();
        desiredCard = game.takeRandomDrawPileCard();
        application.updateDrawPileButton(desiredCard);
    }

    public void handleDiscardPileHover(Button b) {
        if (isGameOver()) {
            return;
        }
        if (game.topDrawPileCardIsRevealed()) {
            return;
        }
        application.highlightButton(b);
    }

    public void handleDiscardPileMouseExit(Button b) {
        if (desiredCard == null) {
            application.unhighlightButton(b);
        }
    }

    public void handleDiscardPileClick() {
        if (isGameOver()) {
            return;
        }
        if (game.topDrawPileCardIsRevealed()) {
            return;
        }
        application.highlightDiscardPile();
        desiredCard = getTopDiscardCard();
    }

    public void handleHandHover(Player p, Button b) {
        if (isGameOver()) {
            return;
        }
        if (!game.isCurrentTurn(p)) {
            return;
        }
        if (desiredCard == null) {
            return;
        }
        application.highlightButton(b);
    }

    public void handleMouseExit(Player p, Button b) {
        if (isGameOver()) {
            return;
        }
        application.unhighlightButton(b);
    }

    public void handleHandClick(Player p, Button b) {
        if (isGameOver()) {
            return;
        }
        if (!game.isCurrentTurn(p)) {
            return;
        }
        if (desiredCard == null) {
            return;
        }

        Card clickedCard = buttonCardMap.get(b);

        // If the player is drawing from the draw pile
        if (game.topDrawPileCardIsRevealed()) {
            buttonCardMap.replace(b, clickedCard, desiredCard);
            game.swapCards(p, clickedCard, desiredCard);
            game.playerTookTopDrawPileCard();
        } else {
            Card topDiscardCard = game.getTopDiscardCard();
            buttonCardMap.replace(b, clickedCard, topDiscardCard);
            game.swapForTopDiscardCard(p, clickedCard);
        }

        application.unhighlightDrawPile();
        application.unhighlightDiscardPile();
        application.unhighlightButton(b);
        application.updateDrawPileButton();
        application.updateDiscardPileButton();
        application.updateHandCardButtonText(b);
        application.updateCurrentTurnLabel();
        this.desiredCard = null;
    }

    public void handleVictoryButtonClick(Player p) {
        if (isGameOver()) {
            return;
        }
        game.checkForVictory(p);
        if (game.isGameOver()) {
            application.updateCurrentTurnLabel();
        }
    }

    public Card getMappedHandCard(Button b) { return buttonCardMap.get(b); }
    public List<Player> getPlayers() {
        return game.getPlayers();
    }
    public Player getPlayer(int playerNum) {
        return game.getPlayer(playerNum);
    }
    public Player getCurrentTurnPlayer() { return game.getCurrentTurnPlayer(); }
    public Card getTopDiscardCard() { return game.getTopDiscardCard(); }
    public int getPlayerNum(Player p) { return game.getPlayerNum(p); }

}