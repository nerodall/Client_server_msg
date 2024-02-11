package server.log;

import server.log.ILog;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileLogMsg implements ILog {
    private String pathLog = "D:\\GB\\Java Core\\Client_Server_msg\\src\\main\\java\\server\\log.txt";



    @Override
    public void writeLog(String msg) throws IOException {
        FileWriter fw = new FileWriter(pathLog, true);
        fw.append(msg).append("\n");
        fw.flush();
    }

    @Override
    public StringBuilder readLog() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(pathLog));
        for (String line : lines) {
            sb.append(line);
            sb.append("\n");
        }
        return sb;
    }

    @Override
    public String readLastMsg() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathLog));
        return lines.getLast() + "\n";
    }
}
