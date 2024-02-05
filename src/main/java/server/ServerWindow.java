package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 500;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();

    private boolean isServerWorking;


    public static void main(String[] args) {
        new ServerWindow();
    }

    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Server stopped " + isServerWorking + "\n");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                System.out.println("Server started " + isServerWorking + "\n");
                try {
                    readLog();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(3, 2));
        add(log, BorderLayout.NORTH);
        add(btnStart);
        add(btnStop);

        setVisible(true);


    }

   public void readLog() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(
                "D:\\GB\\Java Core\\Client_Server_msg\\src\\main\\java\\server\\log.txt"));
        for (String line : lines) {
            log.append(line);
        }
    }

   public void writeLog (String msg) throws IOException {
        FileWriter fw = new FileWriter("D:\\GB\\Java Core\\Client_Server_msg\\src\\main\\java\\server\\log.txt",
                false);
        fw.write(msg);
        fw.flush();
        readLog();
    }
}
