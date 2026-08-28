// --- Imports: what each line pulls in ---
import org.junit.jupiter.api.Test;               // the @Test annotation that marks a method as a test
import static org.junit.jupiter.api.Assertions.*; // static import: lets us write assertEquals(...) instead of Assertions.assertEquals(...)
import java.util.ArrayList;                        // the list type calculateAverage expects
import java.util.Arrays;                           // Arrays.asList(...) is a quick way to fill a list inline

// Tests for GradeAnalyzer.calculateAverage. The method is static, so we can call it
// straight off the class name (GradeAnalyzer.calculateAverage) without building a
// GradeAnalyzer object first. Each @Test method is one independent little experiment:
// set up some input, call the method, then assert we got what we expected.
public class GradeAnalyzerTest {

    @Test
    void calculateAverage_returnsZero_whenListIsEmpty() {
        // An empty list has nothing to divide by, so the method should hand back 0.0
        // instead of blowing up with a divide-by-zero. This is our "edge case" guard.
        ArrayList<Integer> scores = new ArrayList<>();
        // assertEquals(expected, actual): expected value goes FIRST, the real result second.
        assertEquals(0.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsCorrectAverage_forTypicalScores() {
        // The everyday case: three normal scores that add to 270 and average to 90.
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(80, 90, 100));
        assertEquals(90.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsSingleValue_whenListHasOneItem() {
        // One score in, that same score back out. Nothing to average against itself.
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(75));
        assertEquals(75.0, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_returnsDouble_notInteger() {
        // The sneaky one: 1 + 2 = 3, divided by 2 = 1.5, NOT 1. If the method did integer
        // division (int / int) it would chop off the .5 and return 1. We expect 1.5, so this
        // test would go red the moment that bug crept in. This is the whole point of testing:
        // pin down the behavior that's easy to break.
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(1, 2));
        assertEquals(1.5, GradeAnalyzer.calculateAverage(scores));
    }

    @Test
    void calculateAverage_handlesAllSameValues() {
        // If every score is identical, the average is just that value. No surprises,
        // but worth locking in so a future "clever" rewrite can't quietly break it.
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(88, 88, 88));
        assertEquals(88.0, GradeAnalyzer.calculateAverage(scores));
    }

    // --- My own extra test (not from the module) ---

    @Test
    void calculateAverage_returnsExactAverage_forTenScores() {
        // A bigger, more realistic batch. These ten scores add up to 775, so the average
        // should land exactly on 77.5. The module examples only used 1-3 numbers; this one
        // checks the math still holds over a longer list where an off-by-one in the loop
        // or a wrong divisor would show up.
        ArrayList<Integer> scores = new ArrayList<>(
                Arrays.asList(55, 65, 75, 85, 95, 60, 70, 80, 90, 100));
        assertEquals(77.5, GradeAnalyzer.calculateAverage(scores));
    }
}
