package de.unistuttgart.iste.gits.common.user_handling;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * This class represents user data for a logged-in user as provided by keycloak.
 */
@Data
@Builder
public class LoggedInUser {
    private final UUID id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final List<CourseMembership> courseMemberships;
    private final List<String> realmRoles;

    public LoggedInUser(@JsonProperty("id") final UUID id,
                        @JsonProperty("userName") final String userName,
                        @JsonProperty("firstName") final String firstName,
                        @JsonProperty("lastName") final String lastName,
                        @JsonProperty("courseMemberships") final List<CourseMembership> courseMemberships,
                        @JsonProperty("realmRoles") final List<String> realmRoles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseMemberships = courseMemberships;
        this.realmRoles = realmRoles;
    }

    @Data
    @Builder
    public static class CourseMembership {
        private final UUID courseId;
        private final UserRoleInCourse role;
        private final boolean published;
        private final OffsetDateTime startDate;
        private final OffsetDateTime endDate;

        public CourseMembership(final UUID courseId,
                                final UserRoleInCourse role,
                                final boolean published,
                                final OffsetDateTime startDate,
                                final OffsetDateTime endDate) {
            this.courseId = courseId;
            this.role = role;
            this.published = published;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        /**
         * Constructor used for JSON deserialization.
         */
        public CourseMembership(@JsonProperty("courseId") final UUID courseId,
                                @JsonProperty("role") final UserRoleInCourse role,
                                @JsonProperty("published") final boolean published,
                                @JsonProperty("startDate") final String startDate,
                                @JsonProperty("endDate") final String endDate) {
            this.courseId = courseId;
            this.role = role;
            this.published = published;
            this.startDate = OffsetDateTime.parse(startDate);
            this.endDate = OffsetDateTime.parse(endDate);
        }
    }

    public enum UserRoleInCourse {
        STUDENT(1),
        TUTOR(2),
        ADMINISTRATOR(3);

        UserRoleInCourse(final int roleRanking) {
            this.roleRanking = roleRanking;
        }

        /**
         * Checks if the user has at least the permissions of the specified role.
         *
         * @param role The role to check against.
         * @return True if the user has at least the permissions of the specified role, false otherwise.
         */
        public boolean hasAtLeastPermissionsOf(final UserRoleInCourse role) {
            return this.roleRanking >= role.roleRanking;
        }

        /**
         * The ranking of the role in the course. The higher the ranking, the more permissions the role has.
         */
        private final int roleRanking;
    }
}
