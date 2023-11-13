package christmas.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtil {
    private DateUtil() {
        // 인스턴스 생성 방지용
    }

    public static DayOfWeek getDayOfWeek(int year, int month, int day) {
        LocalDate orderDate = LocalDate.of(year, month, day);
        return orderDate.getDayOfWeek();
    }
}
