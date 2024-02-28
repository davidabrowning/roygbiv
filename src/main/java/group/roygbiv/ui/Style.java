package group.roygbiv.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Style {

    public static int CARD_HEIGHT = 120;
    public static int CARD_WIDTH = 100;

    public static int BORDER_RADIUS = 10;
    public static int BACKGROUND_RADIUS = 10;

    public static Border unhighlightedBorder = new Border(new BorderStroke(Color.WHITESMOKE, BorderStrokeStyle.SOLID, new CornerRadii(BORDER_RADIUS), new BorderWidths(5)));
    public static Border highlightedBorder = new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, new CornerRadii(BORDER_RADIUS), new BorderWidths(5)));

    public static Background getBackground(Color c) {
        BackgroundFill bf = new BackgroundFill(c, new CornerRadii(BORDER_RADIUS + BACKGROUND_RADIUS), new Insets(0, 0, 0, 0));
        return new Background(bf);
    }

}
