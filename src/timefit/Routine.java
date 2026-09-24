package timefit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Routine
{
    
    private static List<Exercise> exerciseLibrary = new ArrayList<Exercise>();

    private int timeStart;
    private int timeEnd;
    private int length;
    private List<Exercise> exercises;

  
    public Routine(int timeStart, int timeEnd, String goal)
    {
        if (timeStart < 0 || timeStart > 23 || timeEnd < 0 || timeEnd > 23 || timeStart > timeEnd)
        {
            throw new IllegalArgumentException();
        }

        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.length = (timeEnd - timeStart) * 60;
        this.exercises = new ArrayList<Exercise>();

        int remaining = this.length;

        for (int i = 0; i < exerciseLibrary.size(); i++)
        {
            Exercise ex = exerciseLibrary.get(i);

            if (ex.hasGoal(goal) && ex.getTime() <= remaining)
            {
                exercises.add(ex);
                remaining -= ex.getTime();
            }
        }
    }


  
    public static void setExerciseLibrary(List<Exercise> library)
    {
        exerciseLibrary = library;
    }




    public int getTimeStart()
    {
        return timeStart;
    }



  
    public int getTimeEnd()
    {
        return timeEnd;
    }



    public int getLength()
    {
        return length;
    }



    public List<Exercise> getExercises()
    {
        return exercises;
    }


eparated String
     */
    public String toString()
    {
        if (exercises.size() == 0)
        {
            return "";
        }

        String result = "";

        for (int i = 0; i < exercises.size(); i++)
        {
            result += exercises.get(i).toString();

            if (i < exercises.size() - 1)
            {
                result += ", ";
            }
        }

        return result;
    }



    public static void main(String[] args)
    {
        String[] dummyGoals = { "Strength", "Cardio", "Flexibility" };

        List<Exercise> dummyExercises = new ArrayList<Exercise>();
        dummyExercises.add(new Exercise("Push-ups", Arrays.asList(dummyGoals[0]), 20));
        dummyExercises.add(new Exercise("Plank", Arrays.asList(dummyGoals[0]), 15));
        dummyExercises.add(new Exercise("Jog", Arrays.asList(dummyGoals[1]), 30));
        dummyExercises.add(new Exercise("Jump Rope", Arrays.asList(dummyGoals[1]), 10));
        dummyExercises.add(new Exercise("Stretch", Arrays.asList(dummyGoals[2]), 10));

        setExerciseLibrary(dummyExercises);

        Routine dummyRoutine = new Routine(12, 14, dummyGoals[0]);
        System.out.println("Goal: " + dummyGoals[0]);
        System.out.println("Length: " + dummyRoutine.getLength() + "m");
        System.out.println("Routine: " + dummyRoutine.toString());
    }
}
