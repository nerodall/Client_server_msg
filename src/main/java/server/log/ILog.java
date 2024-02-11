package server.log;

import java.io.IOException;

public interface ILog {
    void writeLog(String msg) throws IOException;
    StringBuilder readLog () throws IOException;
    String readLastMsg () throws IOException;
}
