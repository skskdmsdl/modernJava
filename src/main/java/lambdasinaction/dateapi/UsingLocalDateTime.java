package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UsingLocalDateTime {
    public static void main(String[] args) {
        // Local Date 사용
        LocalDate today = LocalDate.now();
        LocalDate todayAgain = LocalDate.now();

        System.out.println(today.compareTo(todayAgain) == 0);
        System.out.println(today);
        System.out.println(todayAgain);

        System.out.println("년월일 = " + today.getYear() + " " + today.getMonth() + " " + today.getMonthValue() + " " + today.getDayOfMonth());
        System.out.println("요일 = " + today.getDayOfWeek() + " " + today.getDayOfWeek().getValue());

        // 특정 날짜를 지정해서 LocalDate 생성
        LocalDate endDay = LocalDate.of(2022, 12, 31);
        System.out.println("현재기준 몇일 남아 있는지? " + today.until(endDay, ChronoUnit.DAYS) + "일");

        System.out.println(today.until(endDay, ChronoUnit.MONTHS) + "달");

        System.out.println("현재기준 1개월 후 " + today.plusMonths(1));
        System.out.println(DayOfWeek.FRIDAY.plus(3));
    }
}
