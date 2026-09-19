import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("      STUDENT GRADE TRACKER");
        System.out.println("=================================");

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        // Enter student details
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for Student " + (i + 1));

            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();

            scanner.nextLine();

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            // Grade validation
            double grade;

            while (true) {

                System.out.print("Enter Grade (0-100): ");
                grade = scanner.nextDouble();

                if (grade >= 0 && grade <= 100) {
                    break;
                }

                System.out.println(
                    "Invalid grade! Please enter a grade between 0 and 100."
                );
            }

            Student student = new Student(id, name, grade);

            students.add(student);
        }

        // Display student details
        System.out.println("\n=================================");
        System.out.println("       STUDENT DETAILS");
        System.out.println("=================================");

        for (Student student : students) {

            System.out.println("ID     : " + student.getId());
            System.out.println("Name   : " + student.getName());
            System.out.println("Grade  : " + student.getGrade());
            System.out.println("Status : " + student.getStatus());
            System.out.println("---------------------------------");
        }

        // Calculate total, highest and lowest
        double total = 0;

        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();

        String highestStudent = students.get(0).getName();
        String lowestStudent = students.get(0).getName();

        int passed = 0;
        int failed = 0;

        for (Student student : students) {

            double grade = student.getGrade();

            total = total + grade;

            if (grade > highest) {
                highest = grade;
                highestStudent = student.getName();
            }

            if (grade < lowest) {
                lowest = grade;
                lowestStudent = student.getName();
            }

            if (grade >= 40) {
                passed++;
            } else {
                failed++;
            }
        }

        double average = total / students.size();

        // Summary report
        System.out.println("\n=================================");
        System.out.println("          SUMMARY REPORT");
        System.out.println("=================================");

        System.out.println("Total Students : " + students.size());

        System.out.printf("Average Grade  : %.2f%n", average);

        System.out.printf(
            "Highest Grade  : %.2f (%s)%n",
            highest,
            highestStudent
        );

        System.out.printf(
            "Lowest Grade   : %.2f (%s)%n",
            lowest,
            lowestStudent
        );

        System.out.println("Passed Students: " + passed);
        System.out.println("Failed Students: " + failed);

        scanner.close();
    }
}
