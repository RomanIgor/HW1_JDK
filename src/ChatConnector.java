
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatConnector extends JFrame {

    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 720;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Connection to the server";

    JTextField loginField = new JTextField("Enter the login: ");
    JTextField passwordField = new JTextField("Enter your password: ");
    JTextField serverField = new JTextField("Enter the address of server and port for connection: ");
    JButton buttonConnect = new JButton("Connect");

    JPanel grid = new JPanel(new GridLayout(4,1));

    ChatConnector(){

        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        grid.add(loginField);
        grid.add(passwordField);
        grid.add(serverField);
        grid.add(buttonConnect);

        add(grid);

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String login = loginField.getText().replace("Enter your login: ", "");
                new ChatView(login);
            }
        });

        setVisible(true);
    }
}