package de.unistuttgart.iste.gits.common.resource_markdown;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Wrapper class for the ResourceMarkdownEmbeddable in case it is easier to use
 * as an entity, e.g., when multiple markdowns are referenced by the same entity.
 */
@Entity(name = "ResourceMarkdown")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceMarkdownEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    private ResourceMarkdownEmbeddable resourceMarkdown;

    public ResourceMarkdownEntity(String text) {
        this.resourceMarkdown = new ResourceMarkdownEmbeddable(text);
    }

    public String getText() {
        return this.resourceMarkdown.getText();
    }

    public List<UUID> getReferencedMediaRecordIds() {
        return this.resourceMarkdown.getReferencedMediaRecordIds();
    }
}
