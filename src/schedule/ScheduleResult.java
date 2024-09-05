package schedule;

public class ScheduleResult {
    public final Group[] bestSchedule;
    public final int incompatibilityScore;

    public ScheduleResult(Group[] bestSchedule, int incompatibilityScore) {
        this.bestSchedule = bestSchedule;
        this.incompatibilityScore = incompatibilityScore;
    }
}

