package de.unistuttgart.iste.gits.common.event;

import lombok.*;

import java.util.UUID;

/**
 * This class represents a user progress log event.
 */
@Data
@Builder
@AllArgsConstructor
public class UserProgressUpdatedEvent {

    private UUID userId;
    private UUID contentId;
    private UUID chapterId;
    private UUID courseId;
    private boolean success;
    private double correctness;
    private int hintsUsed;
    private Integer timeToComplete;

}
