import java.io.*;

class DataManager {
    private static final String DIARY_FILE = "diary.ser";
    private static final String TASK_FILE = "tasks.ser";

    public static void saveDiary(DiaryManager m) {
        save(m, DIARY_FILE);
    }

    public static void saveTasks(TaskManager m) {
        save(m, TASK_FILE);
    }

    public static DiaryManager loadDiary() {
        return (DiaryManager) load(DIARY_FILE, new DiaryManager());
    }

    public static TaskManager loadTasks() {
        return (TaskManager) load(TASK_FILE, new TaskManager());
    }

    private static void save(Object o, String f) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))) {
            out.writeObject(o);
        } catch (Exception ignored) {}
    }

    private static Object load(String f, Object def) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return in.readObject();
        } catch (Exception e) {
            return def;
        }
    }
}
