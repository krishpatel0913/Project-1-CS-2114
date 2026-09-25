package timefit;
import java.util.ArrayList;
import java.util.List;
public class Day
{
    //~ Fields ................................................................
    private boolean[] available;
    private List<Routine> routines;
    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    
    
    
    /**
     * Creates a day with 24 hours.
     */
    public Day()
    {
        available = new boolean[24];
        routines = new ArrayList<Routine>();
    }
    
    
    /**
     * marks a set of hours as available
     * If user calls 12 14. Will mark hours 12 13 14
     * as available
     *
     * @param start the starting hour
     * @param end the ending hour
     */
    public void setAvailability(int start, int end)
    {
        //prevents bad input
        if (start < 0 || start > 23 || end < 0 || end > 23 || start > end)
        {
            throw new IllegalArgumentException();
        }
        
        for (int i = start; i <= end; i++)
        {
            available[i] = true;
        }
    }
    
    
    
    /**
     * returns all available hours
     *
     * @return the number of available hours
     */
    public int getTotalAvailability()
    {
        int total = 0;

        for (int i = 0; i < available.length; i++)
        {
            if (available[i])
            {
                total++;
            }
        }

        return total;
    }
    
    
    
    /**
     * returns list of time blocks
     * this one actually returns the blocks that are available
     * Using 12-14, getTotalAvailability would return 13
     * this would return the actual block of 12-14
     * As a list so its more flexible.
     *
     * @return a list of available time blocks
     */
    public List<Integer> getAvailableTime()
    {
        List<Integer> times = new ArrayList<Integer>();

        int i = 0;

        while (i < available.length)
        {
            if (available[i])
            {
                int start = i;

                while (i + 1 < available.length && available[i + 1])
                {
                    i++;
                }

                int end = i;

                times.add(start);
                times.add(end);
            }

            i++;
        }

        return times;
    }
    
    
    
    /**
     * marks a list of times as booked
     *
     * @param start the starting hour
     * @param end the ending hour
     */
    public void bookTime(int start, int end)
    {
        //prevents bad input
        if (start < 0 || start > 23 || end < 0 || end > 23 || start > end)
        {
            throw new IllegalArgumentException();
        }

        for (int i = start; i <= end; i++)
        {
            available[i] = false;
        }
    }
    
    
    
    /**
     * returns the routines scheduled for a day
     *
     * @return the routines as a String
     */
    public String toString()
    {
        if (routines.size() == 0)
        {
            return "No Routines";
        }

        String result = "";

        for (int i = 0; i < routines.size(); i++)
        {
            result += routines.get(i).toString();

            if (i < routines.size() - 1)
            {
                result += "\n";
            }
        }

        return result;
    }
    
    
    /**
     * adds a routine to the day
     *
     * @param routine the routine to add
     */
    public void addRoutine(Routine routine)
    {
        routines.add(routine);
    }
}
