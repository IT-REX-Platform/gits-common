package de.unistuttgart.iste.gits.common.user_handling;

import de.unistuttgart.iste.gits.common.exception.NoAccessToCourseException;

import java.time.OffsetDateTime;
import java.util.UUID;

public class UserCourseAccessValidator {
    public static void validateUserHasAccessToCourse(LoggedInUser user,
                                                     LoggedInUser.UserRoleInCourse requiredRole,
                                                     UUID courseId) {
        LoggedInUser.CourseMembership courseMembership = user.getCourseMemberships().stream()
                .filter(membership -> membership.getCourseId().equals(courseId))
                .findFirst()
                .orElseThrow(() -> new NoAccessToCourseException(courseId, "User is not a member of the course."));

        if(!courseMembership.isPublished()) {
            throw new NoAccessToCourseException(courseId, "Course is not published.");
        }

        if(courseMembership.getRole() != requiredRole) {
            throw new NoAccessToCourseException(
                    courseId,
                    "User does not have the required role to access this data of the course."
            );
        }

        if(courseMembership.getStartDate().isAfter(OffsetDateTime.now())) {
            throw new NoAccessToCourseException(
                    courseId,
                    "User does not have access to the course because it has not started yet."
            );
        }

        if(courseMembership.getEndDate().isBefore(OffsetDateTime.now())) {
            throw new NoAccessToCourseException(
                    courseId,
                    "User does not have access to the course because it has ended."
            );
        }
    }
}
