package group.roygbiv.domain;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }
    public Hand getHand() { return hand; }

    @Override
    public String toString() {
        return name;
    }

}
