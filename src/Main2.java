import schedule.*;
import schedule.Class;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;


public class Main2 {
    public static void main(String[] args) {
        System.out.println("INITIATING CALCULATIONS");

        // Example subjects
        Subject ME = new Subject(new ArrayList<>(Arrays.asList(ME41, ME42, ME43, ME44, ME45, ME46, ME47, ME48)));
        Subject PI = new Subject(new ArrayList<>(Arrays.asList(PI41, PI42, PI43_44, PI45_46, PI47, PI48)));
        //Subject AP = new Subject(new ArrayList<>(Arrays.asList(AP41_42, AP43, AP44, AP45_46, AP47, AP48)));
        //Subject RC = new Subject(new ArrayList<>(Arrays.asList(RC41, RC42, RC43, RC44)));
        Subject AP = new Subject(new ArrayList<>(Arrays.asList(AP43)));
        Subject RC = new Subject(new ArrayList<>(Arrays.asList(RC43)));

        // Find the best 5 schedules with dynamic subjects
        ScheduleResult[] topSchedules = findTopSchedules(5, ME, PI, AP, RC);

        // Output the top 5 schedules and their incompatibility scores
        System.out.println("Top 5 schedules found:");
        for (int i = 0; i < topSchedules.length; i++) {
            System.out.println("Schedule " + (i + 1) + " (Incompatibility: " + topSchedules[i].incompatibilityScore + "):");
            for (Group group : topSchedules[i].bestSchedule) {
                System.out.println(group);
            }
            System.out.println();
        }
    }

    // Updated method to find the top 'n' best schedules with dynamic subjects
    public static ScheduleResult[] findTopSchedules(int topN, Subject... subjects) {
        // Priority Queue to store the top N best schedules, sorted by incompatibility (descending)
        PriorityQueue<ScheduleResult> topSchedules = new PriorityQueue<>(topN, Comparator.comparingInt(result -> -result.incompatibilityScore));

        // Helper method to iterate dynamically over subjects' groups
        findSchedulesRecursive(topSchedules, new Group[subjects.length], subjects, 0, topN);

        // Convert the priority queue to an array and return the top schedules
        ScheduleResult[] topScheduleArray = topSchedules.toArray(new ScheduleResult[0]);
        Arrays.sort(topScheduleArray, Comparator.comparingInt(result -> result.incompatibilityScore)); // Sort in ascending order
        return topScheduleArray;
    }

    // Recursive helper method to iterate over all group combinations
    private static void findSchedulesRecursive(PriorityQueue<ScheduleResult> topSchedules, Group[] currentSchedule, Subject[] subjects, int index, int topN) {
        if (index == subjects.length) {
            // Calculate total incompatibility once all groups in the schedule are selected
            int totalIncompatibility = 0;
            for (int i = 0; i < currentSchedule.length; i++) {
                for (int j = i + 1; j < currentSchedule.length; j++) {
                    totalIncompatibility += Group.compatible(currentSchedule[i], currentSchedule[j]);
                }
            }

            // Create a new schedule result
            ScheduleResult result = new ScheduleResult(Arrays.copyOf(currentSchedule, currentSchedule.length), totalIncompatibility);

            // If the priority queue has fewer than topN schedules, add the current one
            if (topSchedules.size() < topN) {
                topSchedules.add(result);
            } else if (result.incompatibilityScore < topSchedules.peek().incompatibilityScore) {
                // If the current schedule is better than the worst in the queue, replace the worst
                topSchedules.poll();  // Remove the worst schedule
                topSchedules.add(result);  // Add the new better schedule
            }
            return;
        }

        // Recursively iterate over the groups of the current subject
        for (Group group : subjects[index].subject) {
            currentSchedule[index] = group;
            findSchedulesRecursive(topSchedules, currentSchedule, subjects, index + 1, topN);
        }
    }

    static Group ME41 = new Group("ME41",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group ME42 = new Group("ME42",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group ME43 = new Group("ME43",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group ME44 = new Group("ME44",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group ME45 = new Group("ME45",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group ME46 = new Group("ME46",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group ME47 = new Group("ME47",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group ME48 = new Group("ME48",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group PI41 = new Group("PI41",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group PI42 = new Group("PI42",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group PI43_44 = new Group("PI43_44",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group PI45_46 = new Group("PI45_46",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group PI47 = new Group("PI47",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group PI48 = new Group("PI48",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group AP41_42 = new Group("AP41_42",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group AP43 = new Group("AP43",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group AP44 = new Group("AP44",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group AP45_46 = new Group("AP45_46",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group AP47 = new Group("AP47",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group AP48 = new Group("AP48",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group RC41 = new Group("RC41",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group RC42 = new Group("RC42",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group RC43 = new Group("RC43",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group RC44 = new Group("RC44",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );
}
