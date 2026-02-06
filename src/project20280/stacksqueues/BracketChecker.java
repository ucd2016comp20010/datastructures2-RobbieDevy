package project20280.stacksqueues;

import project20280.interfaces.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        // TODO
        Stack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < input.length(); i++) {
            char open = input.charAt(i);

            if (open == '{' || open == '[' || open == '(') {
                stack.push(open);
            }

            else if (open == '}' || open == ']' || open == ')') {

                if (stack.isEmpty()) {
                    System.out.println("Error - missing opening bracket");
                    return;
                }

                char close = stack.pop();

                if ((open == '}' && close != '{') ||
                        (open == ']' && close != '[') ||
                        (open == ')' && close != '(')) {
                    System.out.println("Error - mismatched brackets");
                    return;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("Error - missing closing bracket");
        } else {
            System.out.println("Correct");
        }
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}