package oop.project.cli;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String[] split = arguments.trim().split("\\s+");

        // Check number of arguments
        if (split.length < 2) {
            throw new IllegalArgumentException("add command requires exactly two arguments: augend and addend. Only one provided.");
        }
        if (split.length > 2) {
            throw new IllegalArgumentException("add command requires exactly two arguments: augend and addend. Too many provided.");
        }

        try {
            int left = Integer.parseInt(split[0]);
            int right = Integer.parseInt(split[1]);
            return Map.of("left", left, "right", right);

        } catch (NumberFormatException e) {
            try {
                Integer.parseInt(split[0]);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("The first argument ('" + split[0] + "') is not a valid integer.");
            }
            throw new IllegalArgumentException("The second argument ('" + split[1] + "') is not a valid integer.");
        }
    }


    /**
     * Method to subtract two numbers.
     *
     * @param arguments A string containing two numbers separated by a space.
     * @return A map containing the minuend and subtrahend.
     * @throws IllegalArgumentException if the number of arguments is not two.
     */
    static Map<String, Object> sub(String arguments) {
        Pattern pattern = Pattern.compile("^\\s*(?:--left\\s(\\d+(\\.\\d+)?)\\s*)?--right\\s(\\d+(\\.\\d+)?)\\s*$");

        Matcher matcher = pattern.matcher(arguments);

        if (!matcher.matches()) {
            if (!arguments.contains("--right")) {
                throw new IllegalArgumentException("Missing mandatory '--right' argument.");
            }
            if (arguments.matches(".*\\s--\\w+\\s.*")) {
                throw new IllegalArgumentException("Invalid or extraneous argument detected.");
            }
            throw new IllegalArgumentException("Invalid command format.");
        }

        Map<String, Object> resultMap = new HashMap<>();
        String leftGroup = matcher.group(1);
        String rightGroup = matcher.group(3);

        resultMap.put("left", leftGroup != null ? Double.parseDouble(leftGroup) : Optional.empty());
        resultMap.put("right", Double.parseDouble(rightGroup));

        return resultMap;
    }

    /**
     * Calculates the square root of a non-negative integer.
     *
     * @param arguments A string representing the number to calculate the square root of.
     * @return A map containing the number.
     * @throws IllegalArgumentException if the number is negative.
     */
    static Map<String, Object> sqrt(String arguments) {
        String[] numbers = arguments.trim().split("\\s+");

        if (numbers.length > 1) {
            throw new IllegalArgumentException("Sqrt command requires exactly one non-negative integer argument, but multiple were provided.");
        }

        int number;
        try {
            number = Integer.parseInt(numbers[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: '" + numbers[0] + "' is not a valid integer.");
        }

        if (number < 0) {
            throw new IllegalArgumentException("Sqrt command requires a non-negative integer argument, but received: " + number);
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
        String trimmedArgs = arguments.trim();
        if (trimmedArgs.isEmpty()) {
            throw new IllegalArgumentException("No subcommand provided. Valid subcommands are: add, sub, sqrt, mul, or div.");
        }

        if (!Arrays.asList("add", "sub", "sqrt", "mul", "div").contains(trimmedArgs)) {
            throw new IllegalArgumentException("Invalid subcommand '" + trimmedArgs + "'. Valid subcommands are: add, sub, sqrt, mul, or div.");
        }

        return Map.of("subcommand", trimmedArgs);
    }

    /**
     * Parses a date string in the format yyyy-MM-dd and returns a map containing the parsed date.
     *
     * @param arguments A string representing the date to parse.
     * @return A map containing the parsed date as the "date" key.
     * @throws IllegalArgumentException if the date is in an invalid format.
     */
    static Map<String, Object> date(String arguments) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(arguments, formatter);
            return Map.of("date", date);
        } catch (DateTimeParseException e) {
            if (!arguments.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Invalid date format. Date must strictly follow the yyyy-MM-dd pattern (e.g., 2021-03-15).");
            }
            throw new IllegalArgumentException("Invalid date value: " + e.getMessage());
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
        String[] split = arguments.trim().split("\\s+");

        if (split.length != 2) {
            throw new IllegalArgumentException("Divide command requires exactly two arguments: dividend and divisor.");
        }

        double left;
        double right;

        try {
            left = Double.parseDouble(split[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for dividend: '" + split[0] + "' is not a valid number.");
        }

        try {
            right = Double.parseDouble(split[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for divisor: '" + split[1] + "' is not a valid number.");
        }

        if (right == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero.");
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
        String[] split = arguments.trim().split("\\s+");

        if (split.length != 2) {
            throw new IllegalArgumentException("Multiply command requires exactly two arguments: multiplicand and multiplier.");
        }

        double left;
        double right;

        try {
            left = Double.parseDouble(split[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for multiplicand: '" + split[0] + "' is not a valid number.");
        }

        try {
            right = Double.parseDouble(split[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for multiplier: '" + split[1] + "' is not a valid number.");
        }

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
            throw new IllegalArgumentException("Invalid time format. Time must be in the format HH:mm:ss, like '23:59:59'.");
        }

        try {
            LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return Map.of("time", time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date value: " + e.getMessage());
        }
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
        String[] split = arguments.trim().split("\\s+");

        if (split.length != 2) {
            throw new IllegalArgumentException("Power command requires exactly two arguments: base and exponent.");
        }

        double base;
        int exponent;

        try {
            base = Double.parseDouble(split[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for base: '" + split[0] + "' is not a valid number.");
        }

        try {
            exponent = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for exponent: '" + split[1] + "' is not a valid integer.");
        }

        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative for power calculation.");
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
            number = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Factorial command requires a valid integer argument.");
        }

        if (number < 0) {
            throw new IllegalArgumentException("Factorial command requires a non-negative integer argument.");
        }

        long factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return Map.of("number", number, "factorial", factorial);
    }
}
