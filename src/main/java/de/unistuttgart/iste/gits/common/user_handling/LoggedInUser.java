package de.unistuttgart.iste.gits.common.user_handling;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

/**
 * This class represents user data for a logged-in user as provided by keycloak.
 */
@Getter
public class LoggedInUser {
    private final UUID id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final List<CourseMembership> courseMemberships;

    public LoggedInUser(@JsonProperty("id") UUID id,
                        @JsonProperty("userName") String userName,
                        @JsonProperty("firstName") String firstName,
                        @JsonProperty("lastName") String lastName,
                        @JsonProperty("courseMemberships") List<CourseMembership> courseMemberships) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseMemberships = courseMemberships;
    }

    @Getter
    public static class CourseMembership {
        private final UUID courseId;
        private final UserRoleInCourse role;

        public CourseMembership(@JsonProperty("courseId") UUID courseId, @JsonProperty("role") UserRoleInCourse role) {
            this.courseId = courseId;
            this.role = role;
        }
    }

    public enum UserRoleInCourse {
        STUDENT,
        TUTOR,
        ADMINISTRATOR
    }
}
