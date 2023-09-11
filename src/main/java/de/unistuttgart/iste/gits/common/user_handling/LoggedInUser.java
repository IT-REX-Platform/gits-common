package de.unistuttgart.iste.gits.common.user_handling;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * This class represents user data for a logged-in user as provided by keycloak.
 */
@Getter
public record LoggedInUser(
        @JsonProperty("id") UUID id,
        @JsonProperty("userName") String userName,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName
) {

}

