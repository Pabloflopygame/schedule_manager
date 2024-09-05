package schedule;
import java.util.*;


public class Group {
    public final List<Class> classes;
    public final String ID;

    public Group(String id, Class... classes) {
        ID = id;
        this.classes = Arrays.asList(classes);
    }

    public static int compatible(Group group1, Group group2) {
        int incompatibilityCount = 0;

        // Iterate over classes in both groups to check for overlap
        for (Class class1 : group1.classes) {
            for (Class class2 : group2.classes) {

                // Check if classes are on the same day
                if (class1.day == class2.day) {
                    // Check if class times overlap
                    boolean isOverlapping = class1.startTime.isBefore(class2.endTime) && class2.startTime.isBefore(class1.endTime);

                    if (isOverlapping) {
                        // If the class is a lab, count as 9999
                        if (class1.type == ClassType.LAB || class2.type == ClassType.LAB) {
                            incompatibilityCount += 9999;
                        } else {
                            // Otherwise, count as 1
                            incompatibilityCount += 1;
                        }
                    }
                }
            }
        }

        return incompatibilityCount;
    }

    @Override
    public String toString() {
        return ID;
    }
}
