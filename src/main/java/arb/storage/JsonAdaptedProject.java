package arb.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import arb.commons.core.LogsCenter;
import arb.commons.exceptions.IllegalValueException;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.Title;
import arb.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Project}.
 */
public class JsonAdaptedProject {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedProject.class);

    private final String title;
    private final String deadline;
    private final String status;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("title") String title,
            @JsonProperty("deadline") String deadline, @JsonProperty("status") String status,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.deadline = Optional.ofNullable(deadline).orElse(null);
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        logger.info("Logging project: " + source);
        this.title = source.getTitle().fullTitle;
        this.deadline = Optional.ofNullable(source.getDeadline()).map(d -> d.dueDate.toString()).orElse(null);
        this.status = Boolean.toString(source.getStatus().getStatus());
        this.tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Tag> projectTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            projectTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (deadline != null && !Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException((Deadline.MESSAGE_CONSTRAINTS));
        }
        final Deadline modelDeadline = Optional.ofNullable(deadline).map(d -> new Deadline(d)).orElse(null);

        final Set<Tag> modelTags = new HashSet<>(projectTags);
        Project project = new Project(modelTitle, modelDeadline, modelTags);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (Boolean.parseBoolean(status)) {
            project.markAsDone();
        }

        return project;
    }

}
