package objects;

/**
 * Created by Max on 18.03.2015.
 */
public class Cell {
    private State state;
    public Cell() {
        this.state = State.BLANK;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive(){
        return state == State.ALIVE;
    }

    public boolean isDead(){
        return state == State.DEAD;
    }

    public boolean isBlank(){
        return state == State.BLANK;
    }

    public enum State {
        ALIVE, DEAD, BLANK
    }
}
