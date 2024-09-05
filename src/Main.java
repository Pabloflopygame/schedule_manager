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
        Subject BD = new Subject(new ArrayList<>(Arrays.asList(BD41, BD42, BD43_44, BD45_46, BD47)));

        // Find the best schedule
        ScheduleResult result = findBestSchedule(math, physics, MN, AC, BD);

        // Output the best schedule and the number of incompatibilities
        System.out.println("Best schedule found:");
        for (Group group : result.bestSchedule) {
            System.out.println(group);
        }
        System.out.println("Total incompatibility score: " + result.incompatibilityScore);
    }

    // Method to find the best schedule (minimum incompatibility)
    public static ScheduleResult findBestSchedule(Subject math, Subject physics, Subject MN, Subject AC, Subject BD) {
        Group[] bestSchedule = new Group[5];
        int minIncompatibility = Integer.MAX_VALUE;

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

                            // Check if this combination has the least incompatibility
                            if (totalIncompatibility < minIncompatibility) {
                                minIncompatibility = totalIncompatibility;
                                bestSchedule[0] = mathGroup;
                                bestSchedule[1] = physicsGroup;
                                bestSchedule[2] = MNGroup;
                                bestSchedule[3] = ACGroup;
                                bestSchedule[4] = BDGroup;
                            }
                        }
                    }
                }
            }
        }

        // Return the best schedule and the minimum incompatibility score
        return new ScheduleResult(bestSchedule, minIncompatibility);
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
                    LocalTime.of(19, 0),
                    LocalTime.of(21, 0),
                    DayOfWeek.FRIDAY,
                    ClassType.LAB
            )
    );

    static Group MN41 = new Group("MN41",
            new Class(
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
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
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
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
                    LocalTime.of(19, 0),
                    LocalTime.of(20, 0),
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
                    LocalTime.of(15, 0),
                    LocalTime.of(17, 0),
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

    static Group BD45_46 = new Group("BD45_46",
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