package de.unistuttgart.iste.gits.common.dapr;

import de.unistuttgart.iste.gits.common.event.ContentChangeEvent;
import de.unistuttgart.iste.gits.common.event.CrudOperation;
import de.unistuttgart.iste.gits.common.event.UserProgressLogEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class MockTopicPublisher extends GitsTopicPublisher{

    public MockTopicPublisher() {
        super(null);
    }

    @Override
    public void notifyCourseChanges(final UUID courseId, final CrudOperation operation){
        log.info("notifyCourseChanges called with {} and {}", courseId, operation);
    }

    @Override
    public void notifyChapterChanges(final List<UUID> chapterIds, final CrudOperation operation) {
        log.info("notifyChapterChanges called with {} and {}", chapterIds, operation);
    }

    @Override
    public void notifyContentChanges(final List<UUID> contentEntityIds, final CrudOperation operation) {
        log.info("notifyContentChanges called with {} and {}", contentEntityIds, operation);
    }

    @Override
    public void notifyUserWorkedOnContent(final UserProgressLogEvent userProgressLogEvent) {
        log.info("notifyUserWorkedOnContent called with {}", userProgressLogEvent);
    }

    @Override
    public void notifyUserProgressProcessed(final UserProgressLogEvent userProgressLogEvent) {
        log.info("notifyUserProgressProcessed called with {}", userProgressLogEvent);
    }
}
