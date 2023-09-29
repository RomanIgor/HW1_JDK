import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatView extends JFrame {
    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 720;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Chat with Server";

    JTextArea textOutput = new JTextArea("");
    JLabel label = new JLabel("Enter your message to server: ");
    JTextField textInput = new JTextField();
    JButton buttonConnect = new JButton("Send");

    ChatView(String login) {
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        textOutput.setBackground(Color.WHITE);
        textOutput.setBorder(BorderFactory.createTitledBorder("Chat History"));
        textInput.setBackground(Color.LIGHT_GRAY);


        Font labelFont = new Font("Arial", Font.BOLD, 14);
        label.setFont(labelFont);
        textOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textInput.setFont(new Font("Arial", Font.PLAIN, 14));


        textOutput.setMargin(new Insets(10, 10, 10, 10));
        textInput.setMargin(new Insets(5, 5, 5, 5));


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(label, BorderLayout.WEST);
        topPanel.add(textInput, BorderLayout.CENTER);
        topPanel.add(buttonConnect, BorderLayout.EAST);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(textOutput), BorderLayout.CENTER);


        loadChatHistory();


        textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    private void sendMessage() {
        String message = textInput.getText();
        if (!message.isEmpty()) {
            String logMessage = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")) + " : " + message + "\n";
            textOutput.append(logMessage);
            textInput.setText("");


            writeMessageToFile(logMessage);
        }
    }

    private void loadChatHistory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("chat_log.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textOutput.append(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeMessageToFile(String message) {
        try {
            FileWriter fileWriter = new FileWriter("chat_log.txt", true);
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
