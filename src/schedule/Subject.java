package schedule;

import java.util.List;

public class Subject {
    public final List<Group> subject;

    public Subject(List<Group> subject) {
        this.subject = subject;
    }

    public static Group[] mostCompatibleGroups(Subject subject1, Subject subject2) {
        Group[] mostCompatiblePair = new Group[2];
        int minIncompatibility = Integer.MAX_VALUE; // Start with a very high number

        // Iterate over each group in both subjects
        for (Group group1 : subject1.subject) {
            for (Group group2 : subject2.subject) {
                // Calculate the incompatibility between the two groups
                int incompatibility = Group.compatible(group1, group2);

                // If the current incompatibility is the lowest, update the most compatible pair
                if (incompatibility < minIncompatibility) {
                    minIncompatibility = incompatibility;
                    mostCompatiblePair[0] = group1;
                    mostCompatiblePair[1] = group2;
                }
            }
        }

        // Return the pair of groups with the lowest incompatibility
        return mostCompatiblePair;
    }
}
