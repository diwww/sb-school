package ru.gcsales.seminar4;

class StateManager {

    public enum State {
        A, B, C, D
    }

    private static final StateManager ourInstance = new StateManager();
    private State state;

    static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {
        this.state = State.A;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
