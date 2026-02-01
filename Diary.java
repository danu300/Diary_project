import java.time.*;
import java.time.format.*;
import java.util.*;

public class Diary {

    private final DiaryManager diaryManager;
    private final TaskManager taskManager;
    private final Scanner scanner = new Scanner(System.in);

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Diary() {
        diaryManager = DataManager.loadDiary();
        taskManager = DataManager.loadTasks();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataManager.saveDiary(diaryManager);
            DataManager.saveTasks(taskManager);
        }));
    }

    public static void main(String[] args) {
        new Diary().start();
    }

    public void start() {
        boolean running = true;
        System.out.println("Diary Management System");

        while (running) {
            System.out.println("\n1. Diary\n2. Tasks\n3. Reports\n4. Exit");
            switch (scanner.nextLine()) {
                case "1": manageDiary(); break;
                case "2": manageTasks(); break;
                case "3": generateReports(); break;
                case "4": running = false; break;
            }
        }
    }

    // -------- Diary UI --------
    private void manageDiary() {
        System.out.println("1. Add 2. View 3. Search 4. Edit 5. Delete");
        switch (scanner.nextLine()) {
            case "1":
                diaryManager.addEntry(
                        new DiaryEntry(prompt("Title: "), prompt("Content: "))
                );
                break;
            case "2":
                diaryManager.getAllEntries().forEach(System.out::println);
                break;
            case "3":
                System.out.print("Keyword: ");
                diaryManager.searchByKeyword(scanner.nextLine())
                        .forEach(System.out::println);
                break;
            case "4":
                diaryManager.updateEntry(
                        prompt("ID: "),
                        prompt("New title: "),
                        prompt("New content: ")
                );
                break;
            case "5":
                diaryManager.deleteEntry(prompt("ID: "));
                break;
        }
    }

    // -------- Task UI --------
    private void manageTasks() {
        System.out.println("1. Add 2. View 3. Incomplete 4. Complete 5. Delete");
        switch (scanner.nextLine()) {
            case "1":
                taskManager.addTask(new Task(
                        prompt("Title: "),
                        prompt("Description: "),
                        parseDate(prompt("Due date (yyyy-MM-dd or yyyy-MM-dd HH:mm): ")),
                        Integer.parseInt(prompt("Priority (1-5): "))
                ));
                break;
            case "2":
                taskManager.getAllTasks().forEach(System.out::println);
                break;
            case "3":
                taskManager.getIncompleteTasks().forEach(System.out::println);
                break;
            case "4":
                taskManager.markCompleted(prompt("ID: "));
                break;
            case "5":
                taskManager.deleteTask(prompt("ID: "));
                break;
        }
    }

    private void generateReports() {
        System.out.println("Diary Entries: " + diaryManager.getAllEntries().size());
        System.out.println("Tasks: " + taskManager.getAllTasks().size());
        System.out.println("Overdue: " + taskManager.getOverdueTasks().size());
    }

    private String prompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private LocalDateTime parseDate(String s) {
        try {
            return LocalDateTime.parse(s, dateTimeFormatter);
        } catch (Exception e) {
            return LocalDate.parse(s, dateFormatter).atStartOfDay();
        }
    }
}
