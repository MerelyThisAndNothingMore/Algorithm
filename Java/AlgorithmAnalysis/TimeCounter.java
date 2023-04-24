package Java.AlgorithmAnalysis;

import java.text.SimpleDateFormat;

public class TimeCounter {

    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void text(String tag, Task task) {
        if (task == null) {
            return;
        }
        tag = tag == null ? "" : ("【" + tag + "】");
        long begin = System.currentTimeMillis();
        System.out.println(tag + "start:" + begin);
        task.execute();
        long end = System.currentTimeMillis();
        long delta = end - begin;
        System.out.println(tag + "end:" + end + " delta:" + delta);
    }
}
