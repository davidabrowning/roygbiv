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
    public Card getMappedHandCard(Button b) { return buttonCardMap.get(b); }
    public List<Player> getPlayers() {
        return game.getPlayers();
    }
    public Player getCurrentTurnPlayer() { return game.getCurrentTurnPlayer(); }
    public Card getTopDiscardCard() { return game.getTopDiscardCard(); }
    public int getPlayerNum(Player p) { return game.getPlayerNum(p); }
    public boolean allPlayersHaveCompletedInitialCardSwitch() { return game.allPlayersHaveCompletedInitialCardSwitch(); }
    public boolean isGameOver() { return game.isGameOver(); }

    // This method handles user hovering over a Button
    public void handleDrawPileHover(Button b) {

        // Exit criteria:
        // Game over
        // Some players still need to do initial two-card switch
        if (isGameOver()) { return; }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) { return; }

        // Highlight Button
        application.highlightButton(b);
    }

    // This method handles user ending hovering over a button
    public void handleDrawPileMouseExit(Button b) {

        // Exit criteria:
        // The drawPile card has already been drawn and therefore the Player is committed to taking it
        if (game.hasCardBeenDrawn()) { return; }

        // Unhighlight Button
        application.unhighlightButton(b);
    }

    // This method handles user clicking on the drawPile
    public void handleDrawPileClick() {

        // Exit criteria:
        // Game over
        // Some players still need to do initial two-card switch
        if (isGameOver()) { return; }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) { return; }

        // Transfer highlighting from discardPile to drawPile
        // Set the desiredCard to the drawn card
        // Show the drawnCard
        application.unhighlightDiscardPile();
        application.highlightDrawPile();
        desiredCard = game.drawCard();
        application.revealDrawPileButton(desiredCard);
    }

    // This method handles user hovering over the discardPile
    public void handleDiscardPileHover(Button b) {

        // Exit criteria:
        // Game over
        // Some players still need to do initial two-card switch
        // Player has already drawn a card from drawPile and therefore committed to taking a drawnCard
        if (isGameOver()) { return; }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) { return; }
        if (game.hasCardBeenDrawn()) { return; }

        // Highlight the discardPile Button
        application.highlightButton(b);
    }

    // This method handles the user ending hover over the discardPile
    public void handleDiscardPileMouseExit(Button b) {

        // As long as they haven't clicked on and set the discardPile as the desired card
        // then unhighlight the discardPile
        if (desiredCard == null) {
            application.unhighlightButton(b);
        }
    }

    // This method handles the user clicking on the discardPile
    public void handleDiscardPileClick() {

        // Exit criteria:
        // Game is over
        // Some players still need to do initial two-card switch
        // Player has already drawn a card from drawPile and therefore committed to taking a drawnCard
        if (isGameOver()) { return; }
        if (!game.allPlayersHaveCompletedInitialCardSwitch()) { return; }
        if (game.hasCardBeenDrawn()) { return; }

        // Highlight the discardPile and set its top card as the desiredCard
        application.highlightDiscardPile();
        desiredCard = getTopDiscardCard();
    }

    // This method handles the user hovering over a Card in their Hand
    public void handleHandHover(Player p, Button b) {

        // Exit criteria:
        // Game is over
        // Not this player's turn
        // User has not yet chosen a desired draw/discard pile card
        // (and game has exited the initial two-card switch phase)
        if (isGameOver()) { return; }
        if (!game.isCurrentTurn(p)) { return; }
        if (desiredCard == null && game.allPlayersHaveCompletedInitialCardSwitch()) { return; }

        // Highlight the Card's Button
        application.highlightButton(b);
    }

    // This method handles the user ending the hover over a Card in their Hand
    public void handleHandMouseExit(Player p, Button b) {

        // Exit criteria:
        // Game is over
        // This Card is the desired Card (for the game's initial two-card switch)
        if (isGameOver()) { return; }
        if (buttonCardMap.get(b) == desiredCard) { return; }

        // Unhighlight the Card in their hand
        application.unhighlightButton(b);
    }

    // This method handles the user clicking on a Card in their Hand
    public void handleHandClick(Player p, Button b) {

        // Exit criteria:
        // Game is over
        // Not this player's turn
        if (isGameOver()) { return; }
        if (!game.isCurrentTurn(p)) { return; }


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

    // This method handles the user clicking on a "Claim victory" Button
    public void handleVictoryButtonClick(Player p) {

        // Exit criteria
        if (isGameOver()) { return; }

        // Check for victory and update victory flag
        // Note: This is only done on user request; users need to "notice" that they have won
        game.checkForVictory(p);

        // If game is over, update the currentTurnLabel value to say "Game over!"
        if (game.isGameOver()) {
            application.updateCurrentTurnLabel();
        }
    }

}