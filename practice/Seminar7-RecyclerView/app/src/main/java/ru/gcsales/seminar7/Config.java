package ru.gcsales.seminar7;

public class Config {
    /**
     * Broadcast receiver intent filter name
     *
     */
    public static final String ACTION = "ru.gcsales.action.SEND_INFO";

    /**
     * Enum for choosing an extra key to put into intent
     *
     */
    public enum Extras {
        FIRST(1), SECOND(2), THIRD(3);

        private int type;

        Extras(int type){
           this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    private Config() {
    }

}
