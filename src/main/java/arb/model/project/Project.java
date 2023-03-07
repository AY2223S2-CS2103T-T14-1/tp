package arb.model.project;

import static arb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null; field values are validated & immutable.
 */
public class Project {

    // Details fields
    private final Title title;
    private final Deadline deadline;
    private final Status status;

    /**
     * Constructs a {@code Project}.
     * Every field must be present and not null.
     */
    public Project(Title title, Deadline deadline) {
        requireAllNonNull(title, deadline);
        this.title = title;
        this.deadline = deadline;
        status = new Status();
    }

    public Title getTitle() {
        return title;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getTitle() + ", due by: " + getDeadline();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getTitle().equals(getTitle())
                && otherProject.getDeadline().equals(getDeadline())
                && otherProject.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, deadline);
    }
}
