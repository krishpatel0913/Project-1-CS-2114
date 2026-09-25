package timefit;

import student.TestCase;
import java.util.List;

// -------------------------------------------------------------------------
/**
 *  tests day
 *  @author krish
 *  @version Sep 24, 2026
 */
public class DayTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Day day;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * Creates a day
     */
    public void setUp()
    {
        day = new Day();
    }


    /**
     * tests setting available hours
     */
    public void testSetAvailability()
    {
        day.setAvailability(12, 14);

        assertEquals(3, day.getTotalAvailability());
    }


    /**
     * tests invalid availability
     */
    public void testSetAvailabilityInvalid()
    {
        Exception exception = null;
        try
        {
            day.setAvailability(14, 12);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * tests total availability
     */
    public void testGetTotalAvailability()
    {
        day.setAvailability(12, 14);
        assertEquals(3, day.getTotalAvailability());
    }


    /**
     * tests total availability when none is available
     */
    public void testGetTotalAvailabilityEmpty()
    {
        assertEquals(0, day.getTotalAvailability());
    }


    /**
     * tests getting a time block
     */
    public void testGetAvailableTime()
    {
        day.setAvailability(12, 14);
        List<Integer> times = day.getAvailableTime();
        assertEquals(2, times.size());
        assertEquals(12, times.get(0).intValue());
        assertEquals(14, times.get(1).intValue());
    }


    /**
     * tests getting available time when there is none
     */
    public void testGetAvailableTimeEmpty()
    {
        List<Integer> times = day.getAvailableTime();

        assertEquals(0, times.size());
    }


    /**
     * tests booking available hours
     */
    public void testBookTime()
    {
        day.setAvailability(12, 14);

        day.bookTime(13, 14);

        assertEquals(1, day.getTotalAvailability());
    }


    /**
     * Tests invalid booking times
     */
    public void testBookTimeInvalid()
    {
        Exception exception = null;
        try
        {
            day.bookTime(14, 12);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests empty toString
     */
    public void testToStringEmpty()
    {
        assertEquals("No Routines", day.toString());
    }
    
    /**
     * tests adding a routine to the day
     */
    public void testAddRoutine()
    {
        Routine routine = new Routine(12, 14, "Strength");
        day.addRoutine(routine);
        assertEquals(routine.toString(), day.toString());
    }
    
    /**
     * Tests toString when there are multiple routines
     */
    public void testToStringRoutines()
    {
        Routine routine1 = new Routine(12, 14, "Strength");
        Routine routine2 = new Routine(18, 19, "Cardio");

        day.addRoutine(routine1);
        day.addRoutine(routine2);

        String expected =
            routine1.toString() + ", " + routine2.toString();

        assertEquals(expected, day.toString());
    }
    
}
