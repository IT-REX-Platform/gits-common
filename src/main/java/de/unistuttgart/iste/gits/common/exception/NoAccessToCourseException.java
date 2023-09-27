package de.unistuttgart.iste.gits.common.exception;

import java.util.UUID;

public class NoAccessToCourseException extends RuntimeException {
    public NoAccessToCourseException(UUID courseId) {
        super("User has no access to course with id " + courseId + ".");
    }

    public NoAccessToCourseException(UUID courseId, String noAccessReason) {
        super("User has no access to course with id " + courseId + ". Reason: " + noAccessReason);
    }
}
