package group.roygbiv.domain;

public class Player {

    private final String name;
    private Hand hand;
    private int wins;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Getters, setters, and toString

    public Hand getHand() { return hand; }

    @Override
    public String toString() {
        return name;
    }

}
