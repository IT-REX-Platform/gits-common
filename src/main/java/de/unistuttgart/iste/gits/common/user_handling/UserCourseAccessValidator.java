package de.unistuttgart.iste.gits.common.user_handling;

import de.unistuttgart.iste.gits.common.exception.NoAccessToCourseException;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Static helper class which provides methods to validate that a user has access to a course.
 */
public class UserCourseAccessValidator {
    /**
     * Validates that a user has access to a course at the specified level (role). Also takes into account start and
     * end dates of the course and its published status.
     * Throws a NoAccessToCourseException if the user does not have access to the course.
     * @param user The user to validate.
     * @param requiredMinimumRole The role which the user must at least have in this course for validation to succeed.
     * @param courseId The id of the course to validate access to.
     * @throws NoAccessToCourseException If the user does not have access to the course.
     */
    public static void validateUserHasAccessToCourse(LoggedInUser user,
                                                     LoggedInUser.UserRoleInCourse requiredMinimumRole,
                                                     UUID courseId) {
        LoggedInUser.CourseMembership courseMembership = user.getCourseMemberships().stream()
                .filter(membership -> membership.getCourseId().equals(courseId))
                .findFirst()
                .orElseThrow(() -> new NoAccessToCourseException(courseId, "User is not a member of the course."));

        if (!courseMembership.isPublished()) {
            throw new NoAccessToCourseException(courseId, "Course is not published.");
        }

        if (!courseMembership.getRole().hasAtLeastPermissionsOf(requiredMinimumRole)) {
            throw new NoAccessToCourseException(
                    courseId,
                    "User does not have the required role to access this data of the course."
            );
        }

        if (courseMembership.getStartDate().isAfter(OffsetDateTime.now())) {
            throw new NoAccessToCourseException(
                    courseId,
                    "User does not have access to the course because it has not started yet."
            );
        }

        if (courseMembership.getEndDate().isBefore(OffsetDateTime.now())) {
            throw new NoAccessToCourseException(
                    courseId,
                    "User does not have access to the course because it has ended."
            );
        }
    }
}
