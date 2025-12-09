import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();
        
        System.out.println("===== Student Grade Tracker =====");
        
        while (true) {
            System.out.print("Enter student grade (-1 to stop): ");
            int grade = sc.nextInt();
            
            if (grade == -1) break;      // stops input when -1 entered
            grades.add(grade);
        }
        
        if (grades.isEmpty()) {
            System.out.println("No grades entered!");
            return;
        }
        
        int sum = 0, highest = grades.get(0), lowest = grades.get(0);
        
        for (int g : grades) {
            sum += g;
            if (g > highest) highest = g;
            if (g < lowest) lowest = g;
        }
        
        double avg = (double) sum / grades.size();
        
        System.out.println("\n===== Grade Report =====");
        System.out.println("Total Students : " + grades.size());
        System.out.println("Average Grade  : " + avg);
        System.out.println("Highest Grade  : " + highest);
        System.out.println("Lowest Grade   : " + lowest);
    }
}
