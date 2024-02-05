package client;

import server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();


    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAdress = new JTextField("localhost");
    private final JTextField tfPort = new JTextField("8080");
    private JTextField tfLogin = new JTextField("User");
    private JTextField tfPassword = new JTextField("Password");
    private JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public ClientGUI(ServerWindow sw) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        setResizable(false);
        log.setEditable(false);

        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sw.isServerWorking()) {
                    log.append("Вы успешно подключились!");
                    log.append("\n");
                    tfIPAdress.setEnabled(false);
                    tfLogin.setEnabled(false);
                    tfPort.setEnabled(false);
                    tfPassword.setEnabled(false);
                    try {
                        log.append(sw.readLog().toString());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    log.append("Сервер недоступен 404");
                    log.append("\n");
                }
            }
        });


        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sw.writeLog(tfLogin.getText() + ":" + tfMessage.getText());
                    log.append(sw.readLastMsg());
                    sw.setNewMessage(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                if (sw.isNewMessage()) {
                    sw.setNewMessage(false);
                    try {
                        log.append(sw.readLastMsg());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                checkServer(sw);

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    void checkServer(ServerWindow sw) {
        if (!sw.isServerWorking()) {
            log.append("Сервер недоступен 404\n");
            tfIPAdress.setEnabled(true);
            tfLogin.setEnabled(true);
            tfPort.setEnabled(true);
            tfPassword.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new ClientGUI(new ServerWindow());
    }

}
