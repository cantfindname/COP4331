package oop.project.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ScenariosTests {

    @Nested
    class Add {

        @ParameterizedTest
        @MethodSource
        public void testAdd(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testAdd() {
            return Stream.of(
                Arguments.of("Add", "add 1 2", Map.of("left", 1, "right", 2)),
                Arguments.of("Missing Argument", "add 1", null),
                Arguments.of("Extraneous Argument", "add 1 2 3", null),
                Arguments.of("Not A Number", "add one two", null),
                Arguments.of("Not An Integer", "add 1.0 2.0", null)
            );
        }

    }

    @Nested
    class Sub {

        @ParameterizedTest
        @MethodSource
        public void testSub(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testSub() {
            return Stream.of(
                Arguments.of("Sub", "sub --left 1.0 --right 2.0", Map.of("left", 1.0, "right", 2.0)),
                Arguments.of("Left Only", "sub --left 1.0", null),
                Arguments.of("Right Only", "sub --right 2.0", Map.of("left", Optional.empty(), "right", 2.0)),
                Arguments.of("Missing Value", "sub --right", null),
                Arguments.of("Extraneous Argument", "sub --right 2.0 extraneous", null),
                Arguments.of("Misspelled Flag", "sub --write 2.0", null),
                Arguments.of("Not A Number", "sub --right two", null)
            );
        }

    }

    @Nested
    class Sqrt {

        @ParameterizedTest
        @MethodSource
        public void testSqrt(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testSqrt() {
            return Stream.of(
                Arguments.of("Valid", "sqrt 4", Map.of("number", 4)),
                Arguments.of("Imperfect Square", "sqrt 3", Map.of("number", 3)),
                Arguments.of("Zero", "sqrt 0", Map.of("number", 0)),
                Arguments.of("Negative", "sqrt -1", null)
            );
        }

    }

    @Nested
    class Calc {

        @ParameterizedTest
        @MethodSource
        public void testCalc(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testCalc() {
            return Stream.of(
                Arguments.of("Add", "calc add", Map.of("subcommand", "add")),
                Arguments.of("Sub", "calc sub", Map.of("subcommand", "sub")),
                Arguments.of("Sqrt", "calc sqrt", Map.of("subcommand", "sqrt")),
                Arguments.of("Missing", "calc", null),
                Arguments.of("Invalid", "calc unknown", null)
            );
        }

    }

    @Nested
    class Date {

        @ParameterizedTest
        @MethodSource
        public void testDate(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testDate() {
            return Stream.of(
                    Arguments.of("Valid date format", "date 2024-01-01", Map.of("date", LocalDate.of(2024, 1, 1))),
                    Arguments.of("Invalid date format", "date 20240401", null),
                    Arguments.of("Date with invalid year", "date 202x-01-01", null),
                    Arguments.of("Date with invalid month", "date 2024-13-01", null),
                    Arguments.of("Date with invalid day", "date 2024-02-30", null),
                    Arguments.of("Date with additional spaces", "date   2024-01-01   ", Map.of("date", LocalDate.of(2024, 1, 1))),
                    Arguments.of("Date with leading/trailing spaces", "   date 2024-01-01   ", Map.of("date", LocalDate.of(2024, 1, 1)))
            );
        }
    }

    @Nested
    class Div {

        @ParameterizedTest
        @MethodSource
        public void testDivide(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testDivide() {
            return Stream.of(
                    Arguments.of("Valid", "div 10 2", Map.of("dividend", 10.0, "divisor", 2.0)),
                    Arguments.of("Zero Divisor", "div 5 0", null),
                    Arguments.of("Negative Dividend", "div -8 2", Map.of("dividend", -8.0, "divisor", 2.0)),
                    Arguments.of("Negative Divisor", "div 10 -2", Map.of("dividend", 10.0, "divisor", -2.0)),
                    Arguments.of("Not A Number", "div two 3", null),
                    Arguments.of("Missing Argument", "div 5", null),
                    Arguments.of("Extraneous Argument", "div 5 3 2", null),
                    Arguments.of("Floating Point", "mul 2.5 3", Map.of("multiplicand", 2.5, "multiplier", 3.0))
            );
        };
    }


    @Nested
    class Mul {

        @ParameterizedTest
        @MethodSource
        public void testMultiply(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testMultiply() {
            return Stream.of(
                    Arguments.of("Valid", "mul 5 4", Map.of("multiplicand", 5.0, "multiplier", 4.0)),
                    Arguments.of("Zero", "mul 0 10", Map.of("multiplicand", 0.0, "multiplier", 10.0)),
                    Arguments.of("Negative", "mul -3 2", Map.of("multiplicand", -3.0, "multiplier", 2.0)),
                    Arguments.of("Not A Number", "mul two 3", null),
                    Arguments.of("Missing Argument", "mul 5", null),
                    Arguments.of("Extraneous Argument", "mul 5 3 2", null)
            );
        };
    }

    @Nested
    class Time {

        @ParameterizedTest
        @MethodSource
        public void testTime(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testTime() {
            return Stream.of(
                    Arguments.of("Valid time format", "time 12:00:00", Map.of("time", LocalTime.of(12, 0, 0))),
                    Arguments.of("Invalid time format", "time 12:00", null),
                    Arguments.of("Time with invalid hour", "time 25:00:00", null),
                    Arguments.of("Time with invalid minute", "time 12:60:00", null),
                    Arguments.of("Time with invalid second", "time 12:00:60", null),
                    Arguments.of("Time with additional spaces", "time   12:00:00   ", Map.of("time", LocalTime.of(12, 0, 0))),
                    Arguments.of("Time with leading/trailing spaces", "   time 12:00:00   ", Map.of("time", LocalTime.of(12, 0, 0)))
            );
        }
    }

    @Nested
    class Power {

        @ParameterizedTest
        @MethodSource
        public void testPower(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testPower() {
            return Stream.of(
                    Arguments.of("Valid", "pow 2 3", Map.of("base", 2.0, "exponent", 3)),
                    Arguments.of("Zero Exponent", "pow 5 0", Map.of("base", 5.0, "exponent", 0)),
                    Arguments.of("Negative Exponent", "pow 3 -2", null),
                    Arguments.of("Not A Number", "pow two 3", null)
            );
        };
    }

    @Nested
    class Factorial {

        @ParameterizedTest
        @MethodSource
        public void testFactorial(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testFactorial() {
            return Stream.of(
                    Arguments.of("Valid", "fact 5", Map.of("number", 5, "factorial", 120L)),
                    Arguments.of("Zero", "fact 0", Map.of("number", 0, "factorial", 1L)),
                    Arguments.of("Negative", "fact -1", null),
                    Arguments.of("Not An Integer", "fact one", null)
            );
        };
    }

    private static void test(String command, Object expected) {
        if (expected != null) {
            var result = Scenarios.parse(command);
            Assertions.assertEquals(expected, result);
        } else {
            //TODO: Update with your specific Exception class or whatever other
            //error handling model you use to check for specific library issues.
            Assertions.assertThrows(Exception.class, () -> {
                Scenarios.parse(command);
            });
        }
    }

}
