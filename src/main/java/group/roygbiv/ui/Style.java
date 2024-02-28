package group.roygbiv.ui;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Style {

    public static int CARD_HEIGHT = 120;
    public static int CARD_WIDTH = 100;

    public static Border unhighlightedBorder = new Border(new BorderStroke(Color.WHITESMOKE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));
    public static Border highlightedBorder = new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));

}
