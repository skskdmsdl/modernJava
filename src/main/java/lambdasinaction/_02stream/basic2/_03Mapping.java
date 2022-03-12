package lambdasinaction._02stream.basic2;

import lambdasinaction._02stream.basic1.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class _03Mapping {

    public static void main(String...args){

        //1. map - Dish의 name 목록만
        Dish.menu.stream()  // Stream<Dish>
                .map(Dish::getName)  //Stream<String>
                .collect(toList())  // List<String>
                .forEach(System.out::println);

        // 2-1. IntStream의 sum() 사용한 칼로리 합계
        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)  // IntStream
                .sum();
        System.out.println("sum = " + sum);

        // 2-2. Stream의 reduce() 사용한 칼로리 합계
        sum = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce((prev, curr) -> prev + curr)
                .orElse(0);
        System.out.println("sum = " + sum);
        
        sum = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (prev, curr) -> prev + curr);
        System.out.println("sum = " + sum);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        //2. map - 중복된 문자 제거한 word 리스트


        //3.flatMap - 중복된 문자 제거가 word 리스트




        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
