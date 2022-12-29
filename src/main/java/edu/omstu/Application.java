package edu.omstu;



public class Application {
    public static void main(String[] args) {
        CSVTable parseTable = new CSVTable("grammar.csv");
        LL1Parser parser = new LL1Parser(parseTable);
        parser.parse("( x + y )");
        parser.parse("f * ( y - z )");
        parser.parse("( ) x y");
    }
}
