package group.roygbiv.ui;

import group.roygbiv.logic.GameController;
import group.roygbiv.domain.*;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class RoygbivApplication extends Application {

    private GameController gameController;

    private Stage stage;
    private Scene mainScene;
    private VBox mainLayout;

    private Label currentTurnLabel;

    private HBox communalCardLayout;
    private Button drawPileButton;
    private Button discardPileButton;

    private VBox handsLayout;
    private List<HBox> playerLayoutList;
    private List<Label> playerNameLabelList;
    private List<HBox> playerHandLayoutList;
    private List<Button> playerVictoryButtonList;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        gameController = new GameController(this);
        gameController.start();
    }

    public void buildGUI() {
        buildScenes();
        buildStage();
    }

    private void buildScenes() {
        buildMainScene();
    }

    private void buildMainScene() {
        buildCurrentTurnLabel();
        buildCommunalCardLayout();
        buildHandsLayout();
        buildMainLayout();
        mainScene = new Scene(mainLayout);
    }

    private void buildMainLayout() {
        mainLayout = new VBox();
        mainLayout.getChildren().addAll(currentTurnLabel, communalCardLayout, handsLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);
        mainLayout.setMinWidth(1600);
        mainLayout.setMinHeight(800);
    }

    private void buildCurrentTurnLabel() {
        currentTurnLabel = new Label("Current turn: " + gameController.getCurrentTurnPlayer());
    }

    private void buildCommunalCardLayout() {
        buildDrawPileButton();
        buildDiscardPileButton();

        communalCardLayout = new HBox();
        communalCardLayout.setAlignment(Pos.CENTER);
        communalCardLayout.setSpacing(20);
        communalCardLayout.getChildren().addAll(drawPileButton, discardPileButton);
    }

    private void buildDrawPileButton() {
        drawPileButton = new Button("Draw card");
        drawPileButton.setBackground(Background.fill(Color.LIGHTGRAY));
        drawPileButton.setBorder(Style.unhighlightedBorder);
        drawPileButton.setMinHeight(Style.CARD_HEIGHT);
        drawPileButton.setMinWidth(Style.CARD_WIDTH);
        drawPileButton.setOnAction(event -> gameController.handleDrawPileClick());
    }

    private void buildDiscardPileButton() {
        discardPileButton = new Button(gameController.getTopDiscardCard().toString());
        discardPileButton.setMinHeight(Style.CARD_HEIGHT);
        discardPileButton.setMinWidth(Style.CARD_WIDTH);
        discardPileButton.setBackground(Background.fill(Color.LIGHTGREEN));
        discardPileButton.setBorder(Style.unhighlightedBorder);
        discardPileButton.setOnAction(event -> gameController.handleDiscardPileClick());
    }

    private void buildHandsLayout() {
        handsLayout = new VBox();
        handsLayout.setSpacing(20);
        playerLayoutList = new ArrayList<>();
        playerNameLabelList = new ArrayList<>();
        playerHandLayoutList = new ArrayList<>();
        playerVictoryButtonList = new ArrayList<>();
        for (Player player : gameController.getPlayers()) {
            buildPlayerLayout(player);
        }
    }

    private void buildPlayerLayout(Player player) {
        buildPlayerLayoutComponents(player);
        HBox playerLayout = new HBox();
        playerLayout.setAlignment(Pos.CENTER);
        playerLayout.setSpacing(20);
        playerLayout.getChildren().add(playerNameLabelList.getLast());
        playerLayout.getChildren().add(playerHandLayoutList.getLast());
        playerLayout.getChildren().add(playerVictoryButtonList.getLast());

        handsLayout.getChildren().add(playerLayout);
        playerLayoutList.add(playerLayout);
    }

    private void buildPlayerLayoutComponents(Player player) {
        Label playerName = new Label(player.toString());
        playerNameLabelList.add(playerName);

        HBox handLayout = new HBox();
        handLayout.setSpacing(5);
        playerHandLayoutList.add(handLayout);

        buildHandCardButtons(player);

        Button roygbiv = new Button("ROY G. BIV!");
        playerVictoryButtonList.add(roygbiv);
    }

    private void buildHandCardButtons(Player p) {
        for (Card c : p.getHand().getCards()) {
            int playerNum = gameController.getPlayerNum(p);
            Button b = new Button(c.toString());
            b.setBorder(Style.unhighlightedBorder);
            b.setBackground(Background.fill(Color.LIGHTGREEN));
            b.setMinWidth(Style.CARD_WIDTH);
            b.setMinHeight(Style.CARD_HEIGHT);
            b.setOnAction(event -> gameController.handleHandClick(p, b));
            playerHandLayoutList.get(playerNum).getChildren().add(b);
            gameController.mapButtonToCard(b, c);
        }
    }

    private void buildStage() {
        stage.setTitle("Desktop ROY G. BIV Game");
        stage.setScene(mainScene);
        stage.show();
    }

    public void highlightDrawPile() { drawPileButton.setBorder(Style.highlightedBorder); }
    public void unhighlightDrawPile() { drawPileButton.setBorder(Style.unhighlightedBorder); }
    public void updateDrawPileButton(Card topCard) { drawPileButton.setText(topCard.toString()); }
    public void updateDrawPileButton() { drawPileButton.setText("Draw card"); }

    public void highlightDiscardPile() { discardPileButton.setBorder(Style.highlightedBorder); }
    public void unhighlightDiscardPile() { discardPileButton.setBorder(Style.unhighlightedBorder); }
    public void updateDiscardPileButton() { discardPileButton.setText(gameController.getTopDiscardCard().toString()); }

    public void updateHandCardButtonText(Button b) { b.setText(gameController.getMappedHandCard(b).toString()); }
    public void updateCurrentTurnLabel() { currentTurnLabel.setText("Current turn: " + gameController.getCurrentTurnPlayer()); }



}