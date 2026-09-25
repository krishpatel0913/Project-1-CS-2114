package timefit;

import java.util.List;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Tests the methods of the TimeFit class.
 * 
 *  @author Naomika
 *  @version 09.24.26
 */
public class TimeFitTest
    extends TestCase
{
    //~ Fields ................................................................
    private TimeFit app;
    
    //~ Constructors ..........................................................
    public void setUp() {
        app = new TimeFit();
    }
    
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * tests createExerciseLibrary().
     */
    public void testCreateExerciseLibrary() {
        List<Exercise> library = app.createExerciseLibrary();
        assertNotNull(library);
        assertEquals(25, library.size());
    }
    
    // ----------------------------------------------------------
    /**
     * tests getGoal().
     */
    public void testGetGoal() {
        Exception e = null;
        try {
            app.setGoal(-1);
        } 
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        
        e = null;
        try {
            app.setGoal(5);
        } 
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        
        app.setGoal(0);
        assertEquals("Weight Loss", app.getGoal());
        
        app.setGoal(4);
        assertEquals("General Fitness", app.getGoal());
    }
    
    // ----------------------------------------------------------
    /**
     * test initializeSchedule()
     */
    public void testInitializeSchedule() {
        setSystemIn("bad_text\n9\n1\n-5\n25\nabc\n9\n5\n24\nxyz\n11\n" 
            + "NEXT\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\n");
        app.initializeSchedule();
        assertEquals("Muscle Gain", app.getGoal()); 
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the randomizeSchedule method across all condition paths,
     * including a null goal, empty day schedules, and an active goal.
     */
    public void testRandomizeSchedule() {
        assertNull(app.getGoal());
        app.randomizeSchedule();
        
        app.setGoal(0); 
        app.randomizeSchedule();
        assertEquals("Weight Loss", app.getGoal());

        setSystemIn("1\n9\n11\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\n");
        app.initializeSchedule();
        app.randomizeSchedule();
        assertEquals("Muscle Gain", app.getGoal());
    }

    // ----------------------------------------------------------
    /**
     * Tests the printSchedule method by verifying console output text.
     */
    public void testPrintSchedule() {
        app.setGoal(4);
        
        systemOut().clearHistory();
        app.printSchedule();
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("=== Your Weekly Workout Schedule ==="));
    }

    // ----------------------------------------------------------
    /**
     * Tests the static main method entry point with full workflow execution.
     */
    public void testMain() {
        setSystemIn("0\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\nNEXT\n");
        
        systemOut().clearHistory();
        
        String[] args = new String[0];
        TimeFit.main(args);
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("=== Welcome to TimeFit ==="));
    }
}