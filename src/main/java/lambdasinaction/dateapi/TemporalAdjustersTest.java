package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.*;

public class TemporalAdjustersTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        //now = LocalDate.of(2022,3,28);

        usingTemporalAdjuster(now);
        System.out.println("----------------------------");
        calculateMartOffDay(now);
    }

    private static void calculateMartOffDay(LocalDate date) {
        //TemporalAdjuster 인터페이스의 Temporal adjustInto(Temporal temporal) 메서드를 람다식으로 구현
        LocalDate martOffDay = date.with(temporal -> {
            //1. 기준이 되는 날짜를 구한다
            LocalDate theDay = LocalDate.from(temporal);
            System.out.println("기준날짜 = " + theDay);
            //2. 두번째, 네번째 일요일 날짜를 구한다
            LocalDate secondDay = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
            System.out.println("secondDay = " + secondDay);
            LocalDate fourthDay = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));
            System.out.println("fourthDay = " + fourthDay);
            //3. 기준날짜와 비교
            if(theDay.isBefore(secondDay)){
                return secondDay;
            }else if(theDay.isBefore(fourthDay)){
                return fourthDay;
            }else {
                return theDay.plusMonths(1).with(dayOfWeekInMonth(2,DayOfWeek.SUNDAY));
            }
        });
        System.out.println("다음 Mart 쉬는 날 = " + martOffDay);

    }

    private static void usingTemporalAdjuster(LocalDate now) {

        System.out.println("두번째 금요일 = " + now.with(dayOfWeekInMonth(2, DayOfWeek.FRIDAY)));
        System.out.println("다음 달의 첫날 = " + now.with(firstDayOfNextMonth()));
        System.out.println("첫번째 목요일 = " + now.with(firstInMonth(DayOfWeek.THURSDAY)));
        System.out.println("이번 달의 마지막날 = " + now.with(lastDayOfMonth()));
        System.out.println("이번 달의 마지막 월요일 = " + now.with(lastInMonth(DayOfWeek.MONDAY)));
        System.out.println("다음 수요일 = " + now.with(next(DayOfWeek.WEDNESDAY)));
        System.out.println("이전 화요일 = " + now.with(previous(DayOfWeek.TUESDAY)));
    }
}