package timefit;

import java.util.List;

// -------------------------------------------------------------------------
/**
 * This class is an exercise that can be used in a routine made by TimeFit.
 * The exercise has a name, a list of fitness goals, and a length in minutes.
 * 
 * @author helam
 * @version Sep 24, 2026
 */
public class Exercise
{
    // ~ Fields ................................................................
    private String name;
    private List<String> goals;
    private int time;

    // ----------------------------------------------------------
    /**
     * Create a new Exercise object.
     * 
     * @param name
     *            the names of the exercise
     * @param goals
     *            the fitness goals the exercise helps with
     * @param time
     *            the length of the exercise in minutes
     */

    // ~Public Methods ........................................................
    public Exercise(String name, List<String> goals, int time)
    {
        if (time <= 0)
        {
            throw new IllegalArgumentException(
                "Exercise time must be greater than 0.");

        }
        this.name = name;
        this.goals = goals;
        this.time = time;
    }


    // ----------------------------------------------------------
    /**
     * Checks that the exercise matches the given fitness goal.
     * 
     * @param input
     *            the fitness goal to check
     * @return true if the exercise has the goal in it and false if not
     */
    public boolean hasGoal(String input)
    {
        if (input == null)
        {
            return false;
        }
        return goals.contains(input);
    }


    // ----------------------------------------------------------
    /**
     * Gets the length in minutes of the exercise
     * 
     * @return the exercise length in minutes 
     */
    public int getTime()
    {
        return time;
    }

    // ----------------------------------------------------------
    /**
     * Returns the exercise's name and length as strings.
     * 
     * @return the exercise in the designed format 
     */
    public String toString()
    {
        return name + " (" + time + "m)";
    }
}
