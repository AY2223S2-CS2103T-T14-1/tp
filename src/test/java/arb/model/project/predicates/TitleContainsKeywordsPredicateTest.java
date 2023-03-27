package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.testutil.ProjectBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate = new TitleContainsKeywordsPredicate(
                firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate = new TitleContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        TitleContainsKeywordsPredicate secondPredicateCopy =
                new TitleContainsKeywordsPredicate(Arrays.asList("second", "first"));
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.singletonList("Sky"));
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Sky", "Painting"));
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Sky", "Oil"));
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("sKy", "PaiNTing"));
        assertTrue(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Oil"));
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").build()));

        // Keywords match deadline but does not match title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("2000-01-01"));
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").withDeadline("2000-01-01")
                .build()));
    }
}
