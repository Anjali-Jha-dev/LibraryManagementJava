package LibraryManagement;
import java.util.Scanner;
import java.io.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private int availableCopies;

    Book(int bookId, String title, String author, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return bookId + "," + title + "," + author + "," + availableCopies;
    }
}

class Student {
    private int studentId;
    private String name;
    private String course;

    Student(int studentId, String name, String course) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return studentId + "," + name + "," + course;
    }
}

public class LibraryManagement {

    // ------------------- Book Methods -------------------
    public static void addBook() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bookId: ");
        int Bid = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter title: ");
        String Bt = sc.nextLine();

        System.out.print("Enter author: ");
        String auth = sc.nextLine();

        System.out.print("Enter available copies: ");
        int ac = sc.nextInt();

        Book b = new Book(Bid, Bt, auth, ac);
        FileWriter fw = new FileWriter("books.csv", true); // append mode
        fw.write(b.toString() + "\n");
        fw.close();

        System.out.println("Book added successfully!");
    }

    public static void viewBook(Scanner sc) throws Exception {
        System.out.print("Enter book name: ");
        sc.nextLine(); // consume leftover newline
        String B_name = sc.nextLine();
        searchBook(B_name, "books.csv");
    }

    public static void searchBook(String B_name, String a) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(a));
        String str;
        int found = 0;

        while ((str = br.readLine()) != null) {
            String[] detail = str.split(",");
            if (detail[1].equalsIgnoreCase(B_name)) {
                printBookDetail(detail);
                found = 1;
            }
        }
        br.close();

        if (found == 0) {
            System.out.println("Book is Unavailable.");
        }
    }

    public static void printBookDetail(String[] details) {
        System.out.println("BookId : " + details[0]);
        System.out.println("Book Name : " + details[1]);
        System.out.println("Book Author : " + details[2]);
        System.out.println("Available Copies : " + details[3]);
    }

    // ------------------- Student Methods -------------------
    public static void addStudents() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter StudentId: ");
        int Sid = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Student Name: ");
        String Sname = sc.nextLine();

        System.out.print("Enter Student Course: ");
        String Scourse = sc.nextLine();

        Student s = new Student(Sid, Sname, Scourse);
        FileWriter fw = new FileWriter("students.csv", true); // append mode
        fw.write(s.toString() + "\n");
        fw.close();

        System.out.println("Student added successfully!");
    }

    public static void viewStudent(Scanner sc) throws Exception {
        System.out.print("Enter Student Id: ");
        int Sid = sc.nextInt();
        searchStudent(Sid, "students.csv");
    }

    public static void searchStudent(int Sid, String f) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        int found = 0;

        while ((line = br.readLine()) != null) {
            String[] details = line.split(",");
            if (Integer.parseInt(details[0]) == Sid) {
                printStudentDetail(details);
                found = 1;
            }
        }
        br.close();

        if (found == 0) {
            System.out.println("Student not found.");
        }
    }

    public static void printStudentDetail(String[] details) {
        System.out.println("Student Id : " + details[0]);
        System.out.println("Student Name : " + details[1]);
        System.out.println("Student Course : " + details[2]);
    }

    // ------------------- Main Menu -------------------
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int ch;

        do {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Book");
            System.out.println("3. Add Student");
            System.out.println("4. View Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBook(sc);
                    break;
                case 3:
                    addStudents();
                    break;
                case 4:
                    viewStudent(sc);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Enter a valid choice please.");
                    break;
            }
        } while (ch != 5);
    }
}
