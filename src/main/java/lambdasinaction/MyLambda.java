package lambdasinaction;

import java.util.List;
import java.util.function.Consumer;

public class MyLambda {
    public static void main(String[] args) {
        // Thread 생성
        // 1. 익명 내부 클래스(Anonymous Inner class)
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 내부 클래스");
            }
        });
        t1.start();

        // 2. 람다식
        Runnable r = () -> System.out.println("람다식");
        Thread t2 = new Thread(r);
        // 👆와 코드
        // Thread t2 = new Thread(() -> System.out.println("람다식"));
        t2.start();
        
        // ctrl + shift + f10 실행 단축키
        // 이클립스는 ctrl + f11

        List<String> myList = List.of("Java", "Python", "Scalar");
        for (String value : myList){
            System.out.println("value = " + value);
        }

        // forEach의 argument 타입은 Consumer
        // void accept(T t)
        // 1. 익명 내부 클래스(Anonymous Inner Class)
        myList.forEach((new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        }));

        // 2. Lambda Expression
        myList.forEach(val -> System.out.println(val));

        //3. Method Reference
        myList.forEach(System.out::println);

    }
}
