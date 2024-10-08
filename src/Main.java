import schedule.*;
import schedule.Class;
import java.time.*;
import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("INITIATING CALCULATIONS");

        // Create subjects with groups
        Subject math = new Subject(new ArrayList<>(Arrays.asList(math41_42, math43_44, math45_46, math47_48, math49_50, math51_52)));
        Subject physics = new Subject(new ArrayList<>(Arrays.asList(physics41_42, physics43_44, physics45_46, physics47_48, physics49_50, physics51_52)));
        Subject MN = new Subject(new ArrayList<>(Arrays.asList(MN41, MN42, MN43_44, MN45, MN46, MN47)));
        Subject AC = new Subject(new ArrayList<>(Arrays.asList(AC41, AC42, AC43, AC44, AC45, AC46, AC47)));
        Subject BD = new Subject(new ArrayList<>(Arrays.asList(BD41, BD42, BD43_44, BD45, BD46, BD47)));

        // Find the best 5 schedules
        ScheduleResult[] topSchedules = findTopSchedules(math, physics, MN, AC, BD, 5);

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

    // Method to find the top 'n' best schedules (minimum incompatibility)
    public static ScheduleResult[] findTopSchedules(Subject math, Subject physics, Subject MN, Subject AC, Subject BD, int topN) {
        // Priority Queue to store the top N best schedules, sorted by incompatibility (descending)
        PriorityQueue<ScheduleResult> topSchedules = new PriorityQueue<>(topN, Comparator.comparingInt(result -> -result.incompatibilityScore));

        // Iterate through all group combinations
        for (Group mathGroup : math.subject) {
            for (Group physicsGroup : physics.subject) {
                for (Group MNGroup : MN.subject) {
                    for (Group ACGroup : AC.subject) {
                        for (Group BDGroup : BD.subject) {

                            // Calculate total incompatibility between all pairs of groups
                            int totalIncompatibility = 0;
                            totalIncompatibility += Group.compatible(mathGroup, physicsGroup);
                            totalIncompatibility += Group.compatible(mathGroup, MNGroup);
                            totalIncompatibility += Group.compatible(mathGroup, ACGroup);
                            totalIncompatibility += Group.compatible(mathGroup, BDGroup);
                            totalIncompatibility += Group.compatible(physicsGroup, MNGroup);
                            totalIncompatibility += Group.compatible(physicsGroup, ACGroup);
                            totalIncompatibility += Group.compatible(physicsGroup, BDGroup);
                            totalIncompatibility += Group.compatible(MNGroup, ACGroup);
                            totalIncompatibility += Group.compatible(MNGroup, BDGroup);
                            totalIncompatibility += Group.compatible(ACGroup, BDGroup);

                            // Create a new schedule result
                            Group[] currentSchedule = new Group[] { mathGroup, physicsGroup, MNGroup, ACGroup, BDGroup };
                            ScheduleResult result = new ScheduleResult(currentSchedule, totalIncompatibility);

                            // If the priority queue has fewer than topN schedules, add the current one
                            if (topSchedules.size() < topN) {
                                topSchedules.add(result);
                            } else if (result.incompatibilityScore < topSchedules.peek().incompatibilityScore) {
                                // If the current schedule is better than the worst in the queue, replace the worst
                                topSchedules.poll();  // Remove the worst schedule
                                topSchedules.add(result);  // Add the new better schedule
                            }
                        }
                    }
                }
            }
        }

        // Convert the priority queue to an array and return the top schedules
        ScheduleResult[] topScheduleArray = topSchedules.toArray(new ScheduleResult[0]);
        Arrays.sort(topScheduleArray, Comparator.comparingInt(result -> result.incompatibilityScore)); // Sort in ascending order
        return topScheduleArray;
    }

    // we set up data here to make main algorithm clean
    static Group math41_42 = new Group("math41_42",
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
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
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group math43_44 = new Group("math43_44",
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
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
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group math45_46 = new Group("math45_46",
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group math47_48 = new Group("math47_48",
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group math49_50 = new Group("math49_50",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group math51_52 = new Group("math51_52",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group physics41_42 = new Group("physics41_42",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
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
                    LocalTime.of(16, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group physics43_44 = new Group("physics43_44",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
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
                    LocalTime.of(16, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group physics45_46 = new Group("physics45_46",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group physics47_48 = new Group("physics47_48",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group physics49_50 = new Group("physics49_50",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.TUESDAY,
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
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group physics51_52 = new Group("physics51_52",
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.TUESDAY,
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
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group MN41 = new Group("MN41",
            new Class(
                    LocalTime.of(20, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
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

    static Group MN42 = new Group("MN42",
            new Class(
                    LocalTime.of(20, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
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
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group MN43_44 = new Group("MN43_44",
            new Class(
                    LocalTime.of(20, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
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
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group MN45 = new Group("MN45",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group MN46 = new Group("MN46",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group MN47 = new Group("MN47",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(16, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(18, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group AC41 = new Group("AC41",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
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
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group AC42 = new Group("AC42",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
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

    static Group AC43 = new Group("AC43",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
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

    static Group AC44 = new Group("AC44",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
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
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group AC45 = new Group("AC45",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );

    static Group AC46 = new Group("AC46",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group AC47 = new Group("AC47",
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(18, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group BD41 = new Group("BD41",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group BD42 = new Group("BD42",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.LAB
            )
    );

    static Group BD43_44 = new Group("BD43_44",
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.MONDAY,
                    ClassType.LAB
            )
    );

    static Group BD45 = new Group("BD45",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group BD46 = new Group("BD46",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.TUESDAY,
                    ClassType.LAB
            )
    );

    static Group BD47 = new Group("BD47",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.THURSDAY,
                    ClassType.THEORY
            ),
            new Class(
                    LocalTime.of(17, 0),
                    LocalTime.of(19, 0),
                    DayOfWeek.WEDNESDAY,
                    ClassType.LAB
            )
    );
}