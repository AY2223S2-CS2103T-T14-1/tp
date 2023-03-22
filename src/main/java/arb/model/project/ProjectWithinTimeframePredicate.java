package arb.model.project;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Project}'s deadline falls within the given timeframe.
 */
public class ProjectWithinTimeframePredicate implements Predicate<Project> {
    private final Optional<Deadline> start;
    private final Optional<Deadline> end;

    /**
    * Builds a {@code ProjectWithinTimeframePredicate} tjat tests if a
    * {@code Project}'s deadline falls within the timeframe {@code start} to
    * {@code end}.
    */
    public ProjectWithinTimeframePredicate(Deadline start, Deadline end) {
        this.start = Optional.ofNullable(start);
        this.end = Optional.ofNullable(end);
    }

    @Override
    public boolean test(Project project) {
        return project.isDeadlinePresent()
            && start.map(d -> project.getDeadline().compareTo(d) >= 0).orElse(true)
            && end.map(d -> d.compareTo(project.getDeadline()) >= 0).orElse(true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectWithinTimeframePredicate // instanceof handles nulls
                && start.equals(((ProjectWithinTimeframePredicate) other).start) // state check
                && end.equals(((ProjectWithinTimeframePredicate) other).end));
    }
}
