package schedule;
import java.time.*;

public class Class {
    public final DayOfWeek day;
    public final LocalTime startTime;
    public final LocalTime endTime;
    public final Duration duration;
    public final ClassType type;

    public Class(LocalTime startTime, LocalTime endTime, DayOfWeek day, ClassType type) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.type = type;
        this.duration = Duration.between(startTime, endTime);
    }
}
