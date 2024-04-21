package oop.project.cli;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
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
            case "time" -> time(arguments);
            case "pow" -> power(arguments);
            case "fact" -> factorial(arguments);

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
     * Parses a date string in the format yyyy-MM-dd and returns a map containing the parsed date.
     *
     * @param arguments A string representing the date to parse.
     * @return A map containing the parsed date as the "date" key.
     * @throws IllegalArgumentException if the date is in an invalid format.
     */
    static Map<String, Object> date(String arguments) {
        try {
            LocalDate date = LocalDate.parse(arguments);
            return Map.of("date", date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Date must be in the format yyyy-MM-dd", e);
        }
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

    /**
     * Parses a time string in the format HH:mm:ss and returns a map containing the parsed time.
     *
     * @param timeString A string representing the time to parse.
     * @return A map containing the parsed time as the "time" key.
     * @throws IllegalArgumentException if the time is in an invalid format.
     */
    static Map<String, Object> time(String timeString) {
        if (!isValidTimeFormat(timeString)) {
            throw new IllegalArgumentException("Invalid time format. Time must be in the format HH:mm:ss");
        }
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return Map.of("time", time);
    }

    /**
     * Checks if the given time string is in a valid format.
     *
     * @param timeString A string representing the time in the format HH:mm:ss.
     * @return true if the time string is in the valid format, false otherwise.
     */
    static boolean isValidTimeFormat(String timeString) {
        String expectedFormat = "\\d{2}:\\d{2}:\\d{2}";

        return timeString.matches(expectedFormat);
    }

    /**
     * Method to calculate the power of a number.
     *
     * @param arguments A string containing two numbers separated by a space.
     * @return A map containing the base and exponent.
     * @throws IllegalArgumentException if the number of arguments is not two or if the exponent is negative.
     */
    static Map<String, Object> power(String arguments) {
        var split = arguments.split(" ");
        if (split.length != 2) {
            throw new IllegalArgumentException("power command requires two arguments: base and exponent");
        }
        double base = Double.parseDouble(split[0]);
        int exponent = Integer.parseInt(split[1]);
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent cannot be negative for power calculation");
        }
        return Map.of("base", base, "exponent", exponent);
    }

    /**
     * Method to calculate the factorial of a number.
     *
     * @param arguments A string containing a number.
     * @return A map containing the number and its factorial.
     * @throws IllegalArgumentException if the number is negative.
     */
    static Map<String, Object> factorial(String arguments) {
        int number;
        try {
            number = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("factorial command requires a valid integer argument");
        }
        if (number < 0) {
            throw new IllegalArgumentException("factorial command requires a non-negative integer argument");
        }
        long factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return Map.of("number", number, "factorial", factorial);
    }
}
