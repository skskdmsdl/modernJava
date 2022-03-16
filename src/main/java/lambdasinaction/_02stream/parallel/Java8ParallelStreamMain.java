package lambdasinaction._02stream.parallel;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Java8ParallelStreamMain {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Using Sequential Stream");
        System.out.println("=================================");
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //순차 스트림 생성
        IntStream intArrStream = Arrays.stream(array);
        intArrStream.forEach(s ->
                {
                    System.out.println(s + " " + Thread.currentThread().getName());
                }
        );

        System.out.println("=================================");
        System.out.println("Using Parallel Stream");
        System.out.println("=================================");
        //병렬 스트림 생성
        IntStream intParallelStream = Arrays.stream(array).parallel();
        intParallelStream.forEach(s ->
                {
                    System.out.println(s + " " + Thread.currentThread().getName());
                }
        );
    }
}