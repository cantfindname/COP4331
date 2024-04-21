package oop.project.cli;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class Scenarios {

    /**
     * Parses and returns the arguments of a command (one of the scenarios
     * below) into a Map of names to values. This method is provided as a
     * starting point that works for most groups, but depending on your command
     * structure and requirements you may need to make changes to adapt it to
     * your needs - use whatever is convenient for your design.
     */
    public static Map<String, Object> parse(String command) {
        //This assumes commands follow a similar structure to unix commands,
        //e.g. `command [arguments...]`. If your project uses a different
        //structure, e.g. Lisp syntax like `(command [arguments...])`, you may
        //need to adjust this a bit to work as expected.
        var split = command.split(" ", 2);
        var base = split[0];
        var arguments = split.length == 2 ? split[1] : "";
        return switch (base) {
            case "add" -> add(arguments);
            case "sub" -> sub(arguments);
            case "sqrt" -> sqrt(arguments);
            case "calc" -> calc(arguments);
            case "date" -> date(arguments);
            default -> throw new IllegalArgumentException("Unknown command.");
        };
    }

    private static Map<String, Object> add(String arguments) {
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("add command requires two arguments: left and right");
        }
        int left = Integer.parseInt(split[0]);
        int right = Integer.parseInt(split[1]);
        return Map.of("left", left, "right", right);
    }

    static Map<String, Object> sub(String arguments) {
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("sub command requires two arguments: left and right");
        }
        double left = split[0].isEmpty() ? 0.0 : Double.parseDouble(split[0]);
        double right = Double.parseDouble(split[1]);
        return Map.of("left", left, "right", right);
    }

    static Map<String, Object> sqrt(String arguments) {
        int number = Integer.parseInt(arguments);
        if (number < 0) {
            throw new IllegalArgumentException("sqrt command requires a non-negative integer argument");
        }
        return Map.of("number", number);
    }

    static Map<String, Object> calc(String arguments) {
        if (!Arrays.asList("add", "sub", "sqrt").contains(arguments)) {
            throw new IllegalArgumentException("calc command requires one of the subcommands: add, sub, sqrt");
        }
        return Map.of("subcommand", arguments);
    }

    static Map<String, Object> date(String arguments) {
        // Parse date from arguments assuming it's in the format yyyy-MM-dd
        LocalDate date = LocalDate.parse(arguments);
        return Map.of("date", date);
    }


    //TODO: Add your own scenarios based on your software design writeup. You
    //should have a couple from pain points at least, and likely some others
    //for notable features. This doesn't need to be exhaustive, but this is a
    //good place to test/showcase your functionality in context.

}
