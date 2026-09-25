package timefit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Main class for TimeFit. 
 * 
 * @author Naomika
 * @version 2026.09.24
 */
public class TimeFit {
    private String[] goalOptions;
    private String goal;
    private List<Exercise> exerciseLibrary;
    private Day[] week;
    private int streak;
    private boolean running;

    // ----------------------------------------------------------
    /**
     * Constructs a TimeFit object.
     */
    public TimeFit() {
        this.goalOptions = new String[]{
            "Weight Loss", 
            "Muscle Gain", 
            "Endurance & Cardio", 
            "Flexibility & Mobility", 
            "General Fitness"
        };
        this.goal = null;
        this.week = new Day[7];
        for (int i = 0; i < 7; i++) {
            this.week[i] = new Day();
        }
        this.exerciseLibrary = createExerciseLibrary();
        Routine.setExerciseLibrary(this.exerciseLibrary);
    }

    // ----------------------------------------------------------
    /**
     * Creates and returns library of 25 Exercise objects.
     * 
     * @return list of available exercise objects
     */
    public List<Exercise> createExerciseLibrary() {
        List<Exercise> library = new ArrayList<>();

        library.add(new Exercise("Barbell Back Squat", Arrays.asList("Muscle Gain", "General Fitness"), 15));
        library.add(new Exercise("Push-Up", Arrays.asList("Muscle Gain", "General Fitness"), 10));
        library.add(new Exercise("Treadmill Running", Arrays.asList("Weight Loss", "Endurance & Cardio"), 30));
        library.add(new Exercise("Stationary Cycling", Arrays.asList("Weight Loss", "Endurance & Cardio"), 45));
        library.add(new Exercise("Dynamic Hamstring Stretch", Arrays.asList("Flexibility & Mobility"), 10));
        library.add(new Exercise("Deadlift", Arrays.asList("Muscle Gain", "General Fitness"), 20));
        library.add(new Exercise("Jump Rope", Arrays.asList("Weight Loss", "Endurance & Cardio"), 15));
        library.add(new Exercise("Dumbbell Bicep Curl", Arrays.asList("Muscle Gain"), 12));
        library.add(new Exercise("Foam Rolling", Arrays.asList("Flexibility & Mobility"), 15));
        library.add(new Exercise("Plank Hold", Arrays.asList("General Fitness", "Muscle Gain"), 8));
        library.add(new Exercise("Burpees", Arrays.asList("Weight Loss", "Endurance & Cardio", "General Fitness"), 12));
        library.add(new Exercise("Yoga Sun Salutation", Arrays.asList("Flexibility & Mobility", "General Fitness"), 20));
        library.add(new Exercise("Overhead Shoulder Press", Arrays.asList("Muscle Gain"), 15));
        library.add(new Exercise("Rowing Machine", Arrays.asList("Weight Loss", "Endurance & Cardio", "Muscle Gain"), 25));
        library.add(new Exercise("Pull-Ups", Arrays.asList("Muscle Gain", "General Fitness"), 12));
        library.add(new Exercise("Cat-Cow Stretch", Arrays.asList("Flexibility & Mobility"), 8));
        library.add(new Exercise("Kettlebell Swings", Arrays.asList("Weight Loss", "Endurance & Cardio", "General Fitness"), 15));
        library.add(new Exercise("Walking Lunges", Arrays.asList("Muscle Gain", "General Fitness"), 15));
        library.add(new Exercise("Stair Climber", Arrays.asList("Weight Loss", "Endurance & Cardio"), 30));
        library.add(new Exercise("Chest Dip", Arrays.asList("Muscle Gain"), 12));
        library.add(new Exercise("Pigeon Pose Stretch", Arrays.asList("Flexibility & Mobility"), 10));
        library.add(new Exercise("High-Intensity Interval Training Sprints", Arrays.asList("Weight Loss", "Endurance & Cardio"), 20));
        library.add(new Exercise("Lat Pulldown", Arrays.asList("Muscle Gain"), 15));
        library.add(new Exercise("Box Jumps", Arrays.asList("Endurance & Cardio", "General Fitness"), 10));
        library.add(new Exercise("Bodyweight Side Lunge", Arrays.asList("Flexibility & Mobility", "General Fitness"), 10));

        return library;
    }

    // ----------------------------------------------------------
    /**
     * Sets active goal by option index.
     * 
     * @param option
     *            selected goal index
     */
    public void setGoal(int option) {
        if (option < 0 || option >= goalOptions.length) {
            throw new IllegalArgumentException("Invalid goal selection index.");
        }
        this.goal = goalOptions[option];
    }

    // ----------------------------------------------------------
    /**
     * Gets currently active goal string.
     * 
     * @return goal string
     */
    public String getGoal() {
        return this.goal;
    }

    // ----------------------------------------------------------
    /**
     * Prompts user for goal and daily availability schedule.
     */
    public void initializeSchedule() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a Goal:");
        for (int i = 0; i < goalOptions.length; i++) {
            System.out.println(i + ": " + goalOptions[i]);
        }

        while (true) {
            try {
                System.out.print("Enter goal number: ");
                int option = Integer.parseInt(scanner.nextLine().trim());
                setGoal(option);
                System.out.println("Goal set to: " + this.goal + "\n");
                break;
            } catch (Exception e) {
                System.out.println("Invalid selection. Please re-enter a valid index.");
            }
        }

        String[] daysOfWeek = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        };
        
        for (int i = 0; i < 7; i++) {
            System.out.println("--- " + daysOfWeek[i] + " ---");
            boolean addingBlocks = true;

            while (addingBlocks) {
                int start = -1;
                int end = -1;

                while (true) {
                    try {
                        System.out.print("Enter time block start hour (0-23) "
                            + "or 'NEXT' to move to next day: ");
                        String input = scanner.nextLine().trim();

                        if (input.equalsIgnoreCase("NEXT")) {
                            addingBlocks = false;
                            break;
                        }

                        start = Integer.parseInt(input);
                        if (start < 0 || start > 23) {
                            throw new IllegalArgumentException(
                                "Start hour must be between 0 and 23.");
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                }

                if (!addingBlocks) {
                    break;
                }

                while (true) {
                    try {
                        System.out.print("Enter time block end hour (0-23): ");
                        end = Integer.parseInt(scanner.nextLine().trim());
                        if (end < start || end > 23) {
                            throw new IllegalArgumentException(
                                "End hour must be >= start hour and <= 23.");
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                }

                try {
                    week[i].setAvailability(start, end);
                    System.out.printf("Added available block (%d:00 - %d:00) to %s.\n", 
                        start, end, daysOfWeek[i]);
                } catch (Exception e) {
                    System.out.println("Failed to set availability: " + e.getMessage());
                }

                while (true) {
                    System.out.print("Type 'new' to add another block for " 
                        + daysOfWeek[i] + " or 'NEXT' to move to next day: ");
                    String choice = scanner.nextLine().trim();

                    if (choice.equalsIgnoreCase("NEXT")) {
                        addingBlocks = false;
                        break;
                    } else if (choice.equalsIgnoreCase("new")) {
                        break;
                    } else {
                        System.out.println("Invalid command. Type 'new' or 'NEXT'.");
                    }
                }
            }
            System.out.println();
        }
    }

    // ----------------------------------------------------------
    /**
     * Generates routines across contiguous available daily blocks.
     */
    public void randomizeSchedule() {
        if (goal == null) {
            return;
        }

        Routine.setExerciseLibrary(this.exerciseLibrary);

        for (int d = 0; d < week.length; d++) {
            Day day = week[d];
            List<Integer> availableHours = day.getAvailableTime();
            if (availableHours.isEmpty()) {
                continue;
            }

            for (int i = 0; i < availableHours.size(); i += 2) {
                int blockStart = availableHours.get(i);
                int blockEnd = availableHours.get(i + 1);

                Routine routine = new Routine(blockStart, blockEnd, this.goal);
                day.addRoutine(routine);
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Displays weekly generated schedule.
     */
    public void printSchedule() {
        String[] daysOfWeek = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        };
        
        System.out.println("=== Your Weekly Workout Schedule ===");
        for (int i = 0; i < week.length; i++) {
            System.out.println(daysOfWeek[i] + ":");
            System.out.println(week[i].toString());
            System.out.println();
        }
    }
    
    // ----------------------------------------------------------
    /**
     * counts streak.
     */
    public void countStreak() {
        String[] daysOfWeek = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        };
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < daysOfWeek.length; i++) {
            System.out.println("Did you complete your routine for " + daysOfWeek[i] + "?");
            String response = null;
            while (response == null) {
                response = scanner.nextLine();
                if (response.trim().equals("yes")) {
                    streak++;
                }
                else if (response.trim().equals("no")) {
                    streak = 0;
                }
                else {
                    response = null;
                    System.out.println("Please enter 'yes' or 'no'.");
                }
            }
        }
        System.out.println("Current Streak: " + streak);
        System.out.println("Would you like to complete another week? Yes or No");
        String response = null;
        while (response == null) {
            response = scanner.nextLine();
            if (response.trim().equals("yes")) {
                running = true;
            }
            else if (response.trim().equals("no")) {
                running = false;
            }
            else {
                response = null;
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Program execution entry point.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        TimeFit app = new TimeFit();
        System.out.println("=== Welcome to TimeFit ===");
        app.running = true;
        app.streak = 0;
        while (app.running) {
            app.initializeSchedule();
            app.randomizeSchedule();
            app.printSchedule();
            app.countStreak();
        }
    }
}