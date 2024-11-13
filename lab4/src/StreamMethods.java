import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

public class StreamMethods {

    public static OptionalDouble getAverageNum(List<Integer> sourceList) {
        return sourceList.stream()
                .mapToInt(Integer::intValue)
                .average();
    }

    public static void strToUpperCase(List<String> strList) {
        strList.stream()
                .map(str -> "new " + str.toUpperCase())
                .forEach(System.out::println);
    }

    public static List<Double> getUniqueSquaredValues(List<Double> nums) {
        return nums.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(set -> set.getValue() == 1)
                .map(x -> pow(x.getKey(), 2))
                .collect(Collectors.toList());
    }


    public static <T> T getLast(List<T> list) {
        return list.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("The list is empty"));
    }

    public static int sumOfEvenNums(int[] nums) {
        OptionalInt sum = Arrays.stream(nums)
                .filter(x -> x % 2 == 0)
                .reduce(Integer::sum);
        return sum.orElse(0);
    }

    public static Map<Character, String> buildAMap(List<String> sourceList) {
        return sourceList.stream().collect(Collectors.toMap(x -> x.charAt(0), x -> x, (oldValue, newValue) -> oldValue + " | " + newValue));
    }

    public static void main(String[] args) {
        System.out.println("METHOD FOR GETTING AN AVERAGE NUM");
        List<Integer> nums = List.of(5, -8, 0, 2);
        System.out.println(getAverageNum(nums).orElse(Double.NaN));

        System.out.println("\nMETHOD FOR FORMATTING STRING");
        strToUpperCase(List.of("string", "to", "uppercase"));

        System.out.println("\nMETHOD FOR GETTING UNIQUE SQUARED VALUES");
        System.out.println(getUniqueSquaredValues(List.of(2.2, 1.2, 5.0, 2.2, 2.4)));

        System.out.println("\nMETHOD FOR GETTING THE LAST ELEMENT");
        try {
            System.out.println((String) getLast(List.of()));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(getLast(nums));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nMETHOD FOR GETTING THE SUM OF EVEN ELEMENTS");
        int[] arr1 = {1, 3, 5, 2, 3};
        int[] arr0 = {};
        System.out.println(sumOfEvenNums(arr1));
        System.out.println(sumOfEvenNums(arr0));


        System.out.println("\nMETHOD FOR GETTING A MAP OUT OF STRINGS");
        System.out.println(buildAMap(List.of("apricot", "banana", "apple", "cucumber", "tomato", "beef")));
        System.out.println(buildAMap(List.of()));
    }
}