package oop.project.cli;

import java.time.LocalDate;
import java.util.*;

public class Scenarios {

    /**
     * Parses the given command and calls the corresponding method based on the command base.
     *
     * @param command the command to parse
     * @return a map containing the result of the executed command
     * @throws IllegalArgumentException if the command base is unknown
     */
    public static Map<String, Object> parse(String command) {
        var split = command.split(" ", 2);
        var base = split[0];
        var arguments = split.length == 2 ? split[1] : "";
        return switch (base) {
            case "add" -> add(arguments);
            case "sub" -> sub(arguments);
            case "sqrt" -> sqrt(arguments);
            case "calc" -> calc(arguments);
            case "date" -> date(arguments);
            case "mul" -> multiply(arguments);
            case "div" -> divide(arguments);

            default -> throw new IllegalArgumentException("Unknown command.");
        };
    }


    /**
     * Parses the "add" command arguments and returns a map containing the augend and addend.
     *
     * @param arguments A string containing two numbers separated by a space.
     * @return A map containing the augend and addend.
     * @throws IllegalArgumentException if the number of arguments is not two.
     */
    private static Map<String, Object> add(String arguments) {
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("add command requires two arguments: augend and addend");
        }
        int left = Integer.parseInt(split[0]);
        int right = Integer.parseInt(split[1]);
        return Map.of("augend", left, "addend", right);
    }
    /**
     * Method to subtract two numbers.
     *
     * @param arguments A string containing two numbers separated by a space.
     * @return A map containing the minuend and subtrahend.
     * @throws IllegalArgumentException if the number of arguments is not two.
     */
    static Map<String, Object> sub(String arguments) {
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("sub command requires two arguments: minuend and subtrahend");
        }
        double left = split[0].isEmpty() ? 0.0 : Double.parseDouble(split[0]);
        double right = Double.parseDouble(split[1]);
        return Map.of("minuend", left, "subtrahend", right);
    }

    /**
     * Calculates the square root of a non-negative integer.
     *
     * @param arguments A string representing the number to calculate the square root of.
     * @return A map containing the number.
     * @throws IllegalArgumentException if the number is negative.
     */
    static Map<String, Object> sqrt(String arguments) {
        int number = Integer.parseInt(arguments);
        if (number < 0) {
            throw new IllegalArgumentException("sqrt command requires a non-negative integer argument");
        }
        return Map.of("number", number);
    }

    /**
     * This method is used to perform various calculations based on the given subcommand.
     *
     * @param arguments A string representing the subcommand.
     * @return A map containing the subcommand as the "subcommand" key.
     * @throws IllegalArgumentException if the subcommand is not one of the valid options: add, sub, sqrt, mul, or div.
     */
    static Map<String, Object> calc(String arguments) {
        if (!Arrays.asList("add", "sub", "sqrt", "mul", "div").contains(arguments)) {
            throw new IllegalArgumentException("calc command requires one of the subcommands: add, sub, sqrt, mul, or div");
        }
        return Map.of("subcommand", arguments);
    }

    /**
     * Parses a string argument into a LocalDate object and returns it as a map.
     *
     * @param arguments A string representing a date.
     * @return A map containing the parsed date.
     */
    static Map<String, Object> date(String arguments) {
        LocalDate date = LocalDate.parse(arguments);
        return Map.of("date", date);
    }

    /**
     * Divides two numbers and returns a map containing the dividend and divisor.
     *
     * @param arguments A string containing two numbers separated by a space.
     * @return A map containing the dividend and divisor.
     * @throws IllegalArgumentException if the number of arguments is not two or if the divisor is zero.
     */
    static Map<String, Object> divide(String arguments){
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("div command requires two arguments: dividend and divisor");
        }
        double left = Double.parseDouble(split[0]);
        double right = Double.parseDouble(split[1]);
        if(right == 0){
           throw new IllegalArgumentException("divisor cannot be zero");
        }
        return Map.of("dividend", left, "divisor", right);
    }
    /**
     * Method to multiply two numbers.
     *
     * @param arguments A string containing two numbers separated by a space.
     * @return A map containing the multiplicand and multiplier.
     * @throws IllegalArgumentException if the number of arguments is not two.
     */
    static Map<String, Object> multiply(String arguments) {
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("mul command requires two arguments: multiplicand and multiplier");
        }
        double left = Double.parseDouble(split[0]);
        double right = Double.parseDouble(split[1]);
        return Map.of("multiplicand", left, "multiplier", right);
    }
}
