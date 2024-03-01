package group.roygbiv.logic;

import group.roygbiv.domain.*;
import group.roygbiv.ui.*;

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
        game.initializePlayers();
        game.dealCards();
        application.buildGUI();
    }

    public void mapButtonToCard(Button b, Card c) {
        buttonCardMap.put(b, c);
    }

    public void handleDrawPileHover(Button b) {
        if (isGameOver()) {
            return;
        }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) {
            return;
        }
        application.highlightButton(b);
    }

    public void handleDrawPileMouseExit(Button b) {
        if (game.hasCardBeenDrawn()) {
            return;
        }
        application.unhighlightButton(b);
    }

    public void handleDrawPileClick() {
        if (isGameOver()) {
            return;
        }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) {
            return;
        }
        application.unhighlightDiscardPile();
        application.highlightDrawPile();
        desiredCard = game.drawCard();
        application.revealDrawPileButton(desiredCard);
    }

    public void handleDiscardPileHover(Button b) {
        if (isGameOver()) {
            return;
        }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) {
            return;
        }
        if (game.hasCardBeenDrawn()) {
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
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) {
            return;
        }
        if (game.hasCardBeenDrawn()) {
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
        if (desiredCard == null && game.allPlayersHaveCompletedInitialCardSwitch()) {
            return;
        }
        application.highlightButton(b);
    }

    public void handleMouseExit(Player p, Button b) {
        if (isGameOver()) {
            return;
        }
        if (buttonCardMap.get(b) == desiredCard) {
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

        Card clickedCard = buttonCardMap.get(b);

        // If not all players have completed their initial card switch
        // Then set this card as the first card for the switch
        // or complete the switch
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) {
            if (clickedCard == desiredCard) {
                desiredCard = null;
                application.unhighlightButton(b);
                return;
            }
            if (desiredCard == null) {
                desiredCard = clickedCard;
                application.highlightButton(b);
            } else {
                game.switchCards(p, desiredCard, clickedCard);

                Button previousButton = null;
                for (Button buttonKey : buttonCardMap.keySet()) {
                    if (buttonCardMap.get(buttonKey) == desiredCard) {
                        previousButton = buttonKey;
                    }
                }
                assert previousButton != null;

                buttonCardMap.replace(b, desiredCard);
                buttonCardMap.replace(previousButton, clickedCard);
                desiredCard = null;

                application.unhighlightButton(b);
                application.updateHandCardButtonText(b);
                application.unhighlightButton(previousButton);
                application.updateHandCardButtonText(previousButton);
                application.updateCurrentTurnLabel();
            }
            return;
        }

        if (desiredCard == null) {
            return;
        }

        buttonCardMap.replace(b, clickedCard, desiredCard);

        // If the player is drawing from the draw pile, then take the drawn card
        // Else, then take the discard card
        if (game.hasCardBeenDrawn()) {
            game.takeDrawnCard(p, clickedCard);
        } else {
            game.takeDiscardedCard(p, clickedCard);
        }



        application.unhighlightDrawPile();
        application.unhighlightDiscardPile();
        application.unhighlightButton(b);
        application.resetDrawPileButton();
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
    public Player getCurrentTurnPlayer() { return game.getCurrentTurnPlayer(); }
    public Card getTopDiscardCard() { return game.getTopDiscardCard(); }
    public int getPlayerNum(Player p) { return game.getPlayerNum(p); }
    public boolean allPlayersHaveCompletedInitialCardSwitch() { return game.allPlayersHaveCompletedInitialCardSwitch(); }
    public boolean isGameOver() { return game.isGameOver(); }

}