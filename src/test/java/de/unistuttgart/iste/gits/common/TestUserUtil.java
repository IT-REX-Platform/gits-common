package de.unistuttgart.iste.gits.common;

import de.unistuttgart.iste.gits.common.user_handling.LoggedInUser;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class TestUserUtil {

    public static LoggedInUser createUserWithMemberships(final LoggedInUser.CourseMembership... courseMemberships){

        return LoggedInUser.builder()
                .userName("userWithMemberships")
                .id(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .courseMemberships(List.of(courseMemberships))
                .realmRoles(List.of("course-creator"))
                .build();
    }

    public static LoggedInUser.CourseMembership.CourseMembershipBuilder courseMembershipBuilder() {
        return LoggedInUser.CourseMembership.builder()
                .published(true)
                .courseId(UUID.randomUUID())
                .startDate(OffsetDateTime.now().minusDays(1))
                .endDate(OffsetDateTime.now().plusDays(1));
    }
}
