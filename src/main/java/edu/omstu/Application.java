package edu.omstu;

public class Application {
    public static void main(String[] args) {
        IntelligentHandler exHandler = new IntelligentHandler("config.txt");

        try {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            exHandler.handle(e);
        }

        try {
            throw new NullPointerException();
        } catch (Exception e) {
            exHandler.handle(e);
        }

        try {
            throw new IndexOutOfBoundsException();
        } catch (Exception e) {
            exHandler.handle(e);
        }
    }
}
