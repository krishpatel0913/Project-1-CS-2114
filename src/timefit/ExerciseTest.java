package timefit;

import java.util.ArrayList;
import java.util.List;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  This class tests the Exercise class.
 * 
 *  @author helam
 *  @version Sep 24, 2026
 */
public class ExerciseTest extends TestCase 
{
    //~ Fields ................................................................
    private Exercise exercise;
    private List<String> goals;
    
    //~ Constructors ..........................................................
    
    // ----------------------------------------------------------
    /**
     * Sets up objects used for the tests.
     */
    public void setUp() {
        goals = new ArrayList<String>();
        goals.add("Strength");
        goals.add("Muscle Gain");
        exercise = new Exercise("Squats", goals, 20);
    }
    
    //~Public  Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Tests the getTime() method.
     */
    public void testGetTime() {
        assertEquals(20, exercise.getTime());   
    }
    
    // ----------------------------------------------------------
    /**
     * Tests when the exercise contains the goal.
     */
    public void testHasGoal() {
        assertTrue(exercise.hasGoal("Strength"));
        assertTrue(exercise.hasGoal("Muscle Gain"));
    }
    
    // ----------------------------------------------------------
    /**
     * Tests when an exercise doesn't contain the goal.
     */
    public void testNotHaveGoal() {
        assertFalse(exercise.hasGoal("Core Strength"));
    }
    
    // ----------------------------------------------------------
    /**
     * Tests a goal thats null.
     */
    public void testHasNullGoal() {
        assertFalse(exercise.hasGoal(null));
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the toString to make sure it's formatted correctly 
     */
    public void testToString() {
        assertEquals("Squats (20m)", exercise.toString());
    }
    
    /**
     * Tests creating an exercise with a negative time.
     */
    @SuppressWarnings("unused")
    public void testNegativeTime()
    {
        Exception exception = null;

        try
        {
            new Exercise("Squats", goals, -5);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }
}
