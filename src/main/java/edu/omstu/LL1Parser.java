package edu.omstu;

import java.util.Set;
import java.util.Stack;

public class LL1Parser {
    private CSVTable parseTable;

    public LL1Parser(CSVTable parseTable) {
        this.parseTable = parseTable;
    }

    public void parse(String text) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        String[] tokens = text.split("\\s+");
        int index = 0;
        boolean accept = true;
        int k = 0;
        String token = tokens[0];
        Set<String> keywords = Set.of("true", "false", "and", "or", "not", "numeric", "id");
        while (k < tokens.length) {
            if (accept) {
                token = tokens[k];
                k++;
            }
            Set<String> terminals = Set.of(parseTable.getValue("terminals", index).split("\\s+"));
            if ((token.matches("-?\\d+(\\.\\d+)?") || token.matches("^[_a-z]\\w*$")) && !keywords.contains(token)) {
                token = "id";
            }
            if (terminals.contains(token)) {
                accept = Integer.parseInt(parseTable.getValue("accept", index)) != 0;
                if (Integer.parseInt(parseTable.getValue("return", index)) != 0) {
                    index = stack.pop();
                } else {
                    int next = Integer.parseInt(parseTable.getValue("next", index)) - 1;
                    if (Integer.parseInt(parseTable.getValue("stack", index)) != 0) {
                        stack.add(index + 1);
                    }
                    index = next;
                }
            } else if (Integer.parseInt(parseTable.getValue("error", index)) != 0) {
                System.err.println("Error");
                return;
            } else {
                index++;
                accept = false;
            }
        }
        System.out.println("Correct");
    }
}
