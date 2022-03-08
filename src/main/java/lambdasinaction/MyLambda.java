package lambdasinaction;

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
    }
}
