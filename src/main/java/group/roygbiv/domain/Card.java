package group.roygbiv.domain;

public class Card {

    private int value;

    public Card(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "#" + value;
    }

}
