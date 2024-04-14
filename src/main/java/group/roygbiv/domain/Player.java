package group.roygbiv.domain;

public class Player {

    private final String name;
    private Hand hand;
    private int wins;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.wins = 0;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Getters, setters, and toString

    public Hand getHand() { return hand; }
    public int getWins() { return wins; }

    @Override
    public String toString() {
        if (wins == 0) {
            return name;
        }
        return name + " (" + wins + ")";
    }

    // ----------------------------------------------------------------------------------------------------------------
    public void resetHand() {
        this.hand = new Hand();
    }

    public void recordAWin() {
        wins++;
    }

}
