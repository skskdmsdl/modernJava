package lambdasinaction._02stream.basic2;

import lambdasinaction._02stream.basic1.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class _05Reducing {

    public static void main(String... args) {

        //reduce - a + b 연산
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

        //reduce - 합계
        Integer sum = numbers.stream().reduce((a, b) -> a + b).get();
        System.out.println("sum = " + sum);

        //reduce - 최대값
        Integer max = numbers.stream().reduce(Integer::max).orElse(0);
        System.out.println("max = " + max);

        max = numbers.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.println("max = " + max);

        //reduce -  최소값
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        //Dish 의 총 칼로리 합계를 구하는 여러가지 방법
        // Stream의 map(), reduce() 사용
        Integer calorysum = Dish.menu.stream()  // Stream<Dish>
                .map(Dish::getCalories)  // Stream<Integer>
                .reduce((d1, d2) -> d1 + d2)  // Optional<Integer>
                .get();// Integer
        System.out.println("calorysum = " + calorysum);

        // IntStream의 sum() 사용
        Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("calorysum = " + calorysum);
    }
}
