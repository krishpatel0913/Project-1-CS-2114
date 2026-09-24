package timefit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoutineTest
{
    @BeforeEach
    void setUp()
    {

        List<Exercise> library = Arrays.asList(
            new Exercise("Push-ups", Arrays.asList("Strength"), 20),
            new Exercise("Plank", Arrays.asList("Strength"), 15),
            new Exercise("Jog", Arrays.asList("Cardio"), 30),
            new Exercise("Stretch", Arrays.asList("Flexibility"), 10)
        );
        Routine.setExerciseLibrary(library);
    }


    @Test
    void constructor_normal_validTimesCreateRoutine()
    {
        Routine routine = new Routine(12, 14, "Strength");
        assertEquals(12, routine.getTimeStart());
        assertEquals(14, routine.getTimeEnd());
        assertEquals(120, routine.getLength());
    }

    @Test
    void constructor_bad_outOfOrderTimesThrows()
    {
        assertThrows(IllegalArgumentException.class, () -> new Routine(14, 12, "Strength"));
    }

    @Test
    void constructor_bad_timesOutsideRangeThrows()
    {
        assertThrows(IllegalArgumentException.class, () -> new Routine(-1, 5, "Strength"));
        assertThrows(IllegalArgumentException.class, () -> new Routine(5, 24, "Strength"));
    }

    @Test
    void constructor_onlyPicksExercisesMatchingGoal()
    {
        Routine routine = new Routine(0, 1, "Strength"); 
        for (Exercise ex : routine.getExercises())
        {
            assertTrue(ex.hasGoal("Strength"));
        }
    }

    @Test
    void constructor_exercisesFitWithinCalculatedLength()
    {
        Routine routine = new Routine(0, 1, "Strength"); 
        int total = 0;

        for (Exercise ex : routine.getExercises())
        {
            total += ex.getTime();
        }

        assertTrue(total <= routine.getLength());
    }

    @Test
    void constructor_noMatchingGoalYieldsEmptyExerciseList()
    {
        Routine routine = new Routine(0, 1, "Balance"); 
        assertTrue(routine.getExercises().isEmpty());
    }

    

    @Test
    void toString()
    {

        Routine routine = new Routine(0, 1, "Strength"); // fits Push-ups & Plank
        String result = routine.toString();
        assertTrue(result.contains("Exercise ("));

        if (routine.getExercises().size() > 1)
        {
            assertTrue(result.contains(", "));
        }
    }

    @Test
    void toStringEmptyList()
    {
        
        Routine routine = new Routine(0, 1, "Balance");
        assertEquals("", routine.toString());
    }
}
