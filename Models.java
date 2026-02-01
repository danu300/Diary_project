import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

abstract class Record implements Serializable {
    protected String id = UUID.randomUUID().toString().substring(0, 5);
    protected String title;
    protected LocalDateTime created = LocalDateTime.now();

    public Record(String title) {
        this.title = title;
    }

    public String getId() { return id; }

    public String toString() {
        return "ID: " + id + " | " + title + " | " +
                created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

class DiaryEntry extends Record {
    private String content;

    public DiaryEntry(String t, String c) {
        super(t);
        content = c;
    }

    public String getContent() { return content; }
    public void setContent(String c) { content = c; }

    public String toString() {
        return super.toString() + "\nContent: " + content;
    }
}

class Task extends Record implements Comparable<Task> {
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    private boolean completed = false;

    public Task(String t, String d, LocalDateTime date, int p) {
        super(t);
        description = d;
        dueDate = date;
        priority = p;
    }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean c) { completed = c; }
    public boolean isOverdue() {
        return !completed && LocalDateTime.now().isAfter(dueDate);
    }

    public int compareTo(Task o) {
        int p = Integer.compare(o.priority, priority);
        return p != 0 ? p : dueDate.compareTo(o.dueDate);
    }

    public String toString() {
        return super.toString() +
                "\nStatus: " + (completed ? "COMPLETED" : isOverdue() ? "OVERDUE" : "PENDING") +
                "\nDue: " + dueDate + "\n" + description;
    }
}
