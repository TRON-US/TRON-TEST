package common.utils;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AppiumServerController {

    //private Process mProcess;
    private HashMap<String, Process> processHashMap = new HashMap<>();

    private static AppiumServerController appiumServerController = new AppiumServerController();

    private AppiumServerController() {
    }

    public void startServer(ReentrantLock lock, String port,
                            String bootstrapPort,String udid) throws Exception {
        Process process;
        String cmd = "appium -a 127.0.0.1 --session-override -p " + port + " -bp " + bootstrapPort + " -U " + udid;
        System.out.println(cmd);
        process = Runtime.getRuntime().exec(cmd);
        processHashMap.put(port, process);
        System.out.println(process);
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if(line.indexOf("[Appium]") != -1) {
                lock.unlock();
            }
        }
        process.waitFor();
        System.out.println("Stop appium server");
        inputStream.close();
        reader.close();
        process.destroy();
    }

    public void stopServer(Process process) {
        if (process != null) {
            System.out.println(process);
            process.destroy();
        }
    }

    public void stopServer(String port) {
        Process process = processHashMap.get(port);
        stopServer(process);
        processHashMap.remove(port);
    }

    public static AppiumServerController getInstance() {
        return appiumServerController;
    }
}

