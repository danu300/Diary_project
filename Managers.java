import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class DiaryManager implements Serializable {
    private final List<DiaryEntry> entries = new ArrayList<>();
    private final Map<String, DiaryEntry> map = new HashMap<>();

    public void addEntry(DiaryEntry e) {
        entries.add(e);
        map.put(e.getId(), e);
    }

    public List<DiaryEntry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    public boolean updateEntry(String id, String t, String c) {
        DiaryEntry e = map.get(id);
        if (e == null) return false;
        if (!t.isEmpty()) e.title = t;
        if (!c.isEmpty()) e.setContent(c);
        return true;
    }

    public boolean deleteEntry(String id) {
        DiaryEntry e = map.remove(id);
        return e != null && entries.remove(e);
    }

    public List<DiaryEntry> searchByKeyword(String k) {
        return entries.stream()
                .filter(e -> e.title.contains(k) || e.getContent().contains(k))
                .collect(Collectors.toList());
    }
}

class TaskManager implements Serializable {
    private final List<Task> tasks = new LinkedList<>();

    public void addTask(Task t) {
        tasks.add(t);
        Collections.sort(tasks);
    }

    public boolean markCompleted(String id) {
        for (Task t : tasks)
            if (t.getId().equals(id)) {
                t.setCompleted(true);
                return true;
            }
        return false;
    }

    public boolean deleteTask(String id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }

    public List<Task> getAllTasks() { return new ArrayList<>(tasks); }
    public List<Task> getIncompleteTasks() {
        return tasks.stream().filter(t -> !t.isCompleted()).toList();
    }
    public List<Task> getOverdueTasks() {
        return tasks.stream().filter(Task::isOverdue).toList();
    }
}
