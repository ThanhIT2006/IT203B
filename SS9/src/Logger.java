public class Logger {

    private static final long startTime = System.currentTimeMillis();

    public static void log(String msg) {
        long t = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("[Time: " + t + "s] " + msg);
    }
}