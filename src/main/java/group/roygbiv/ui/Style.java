package group.roygbiv.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Style {

    public static int CARD_HEIGHT = 120;
    public static int CARD_WIDTH = 100;

    public static Border unhighlightedBorder = new Border(new BorderStroke(Color.WHITESMOKE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));
    public static Border highlightedBorder = new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));

}
