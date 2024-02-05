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
    private boolean newMessage;

    public static void main(String[] args) {
        new ServerWindow();
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage = newMessage;
    }


    public boolean isNewMessage() {
        return newMessage;
    }

    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Server stopped " + isServerWorking + "\n");
                log.append("Server stopped \n");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                System.out.println("Server started " + isServerWorking + "\n");
                log.append("Server started \n");
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

    public StringBuilder readLog() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(
                "D:\\GB\\Java Core\\Client_Server_msg\\src\\main\\java\\server\\log.txt"));
        for (String line : lines) {
            sb.append(line);
            sb.append("\n");
        }
        return sb;
    }

    public void writeLog(String msg) throws IOException {
        FileWriter fw = new FileWriter("D:\\GB\\Java Core\\Client_Server_msg\\src\\main\\java\\server\\log.txt",
                true);
        fw.append(msg).append("\n");
        fw.flush();
    }

    public String readLastMsg() throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(
                "D:\\GB\\Java Core\\Client_Server_msg\\src\\main\\java\\server\\log.txt"));
        return lines.getLast() + "\n";
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }
}
