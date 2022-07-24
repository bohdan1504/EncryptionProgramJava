import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class EncryptionJFrame extends JFrame implements ActionListener {

    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;

    JButton newKeyButton;
    JButton getKeyButton;
    JButton encryptButton;
    JButton decryptButton;
    JButton quitButton;

    JTextArea textField;
    JLabel programTitle;
    String encryptedMessage = "";

    JTextField selectableLabel1 = new JTextField();
    JTextField selectableLabel2 = new JTextField();

    JPanel panel1;
    JPanel panel2;

    EncryptionJFrame(){

        this.setSize(900,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());


        random = new Random();
        list = new ArrayList();
        shuffledList = new ArrayList<>();
        character = ' ';

        newKeyButton = new JButton("New key");
        newKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newKey();
                selectableLabel1.setText("*New key has been generated*");
            }
        });

        getKeyButton = new JButton("Get key");
        getKeyButton.addActionListener((e -> this.getKey()));

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(e -> this.encrypt());

        decryptButton = new JButton("Decrypt");
//        decryptButton.setEnabled(false);
        decryptButton.addActionListener(e -> this.decrypt());

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> this.quit());

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setPreferredSize(new Dimension(100,200));
        panel1.add(newKeyButton);
        panel1.add(getKeyButton);
        panel1.add(encryptButton);
        panel1.add(decryptButton);
        panel1.add(quitButton);

        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setPreferredSize(new Dimension(700,500));


        programTitle = new JLabel("ENCRYPTION PROGRAM");
        programTitle.setFont(new Font("Comic Sans", Font.BOLD, 48));
        programTitle.setPreferredSize(new Dimension(600,50));
//        programTitle.setSize(600,50);
//        programTitle.setBounds(0,0,600,50);
        panel2.add(programTitle);



        textField = new JTextArea();
        textField.setPreferredSize(new Dimension(500,400));
        textField.getPreferredScrollableViewportSize();
        textField.setToolTipText("Enter your command...");
        textField.setText("Enter text to encrypt...");
        textField.setForeground(Color.gray);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);


        JScrollPane areaScrollPane = new JScrollPane(textField);
        areaScrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel2.add(areaScrollPane);


        // JField that mimics JLabel and can be selected
        selectableLabel1.setEditable(false);
        selectableLabel1.setPreferredSize(new Dimension(500,20));
        selectableLabel1.setBorder(null);
        selectableLabel1.setForeground(UIManager.getColor("Label.foreground"));
        selectableLabel1.setBackground(UIManager.getColor("Label.background"));
        selectableLabel1.setFont(UIManager.getFont("Label.font"));

        // JField that mimics JLabel and can be selected
        selectableLabel2.setEditable(false);
        selectableLabel2.setPreferredSize(new Dimension(500,20));
        selectableLabel2.setBorder(null);
        selectableLabel2.setForeground(UIManager.getColor("Label.foreground"));
        selectableLabel2.setBackground(UIManager.getColor("Label.background"));
        selectableLabel2.setFont(UIManager.getFont("Label.font"));
        
        panel2.add(selectableLabel1);
        panel2.add(selectableLabel2);


        selectableLabel1.setText("The default key is generated. Press \"Get key\" to reveal it.");

        newKey();


        this.setLocationRelativeTo(null);
        this.add(panel1);
        this.add(panel2);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // generate new key
    private void newKey(){
        encryptButton.setEnabled(true);
//        decryptButton.setEnabled(false);
        selectableLabel2.setText("");
        character = ' ';
        list.clear();
        shuffledList.clear();

        for (int i=32; i<127; i++){
            list.add(Character.valueOf(character));
            character++;
        }
        shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);
//        selectableLabel1.setText("*New key has been generated*");
    }

    // display the key
    private void getKey(){

//        ArrayList<Character> theKey1 = new ArrayList<Character>();
        String theKey1 = "";
        for (int i =0; i<list.size(); i++){
            theKey1+=list.get(i);
        }

        String theKey2 = "";
        for (int j =0; j<shuffledList.size(); j++){
            theKey2+=shuffledList.get(j);
        }
//        for (Character x : shuffledList){
//            theKey2.add(x);
//        }
        selectableLabel1.setText(theKey1);
        selectableLabel2.setText(theKey2);
//        label.setText(theKey2);
    }

    // a message from user to be encrypted
    private void encrypt() {
        encryptedMessage = "";
        String message = textField.getText();

        letters = message.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }

        for (int i = 0; i < letters.length; i++) {
            encryptedMessage += letters[i];
        }
//        selectableLabel1.setText("Encrypted: " + encryptedMessage);
        textField.setText(encryptedMessage);
        decryptButton.setEnabled(true);
        encryptButton.setEnabled(false);
        selectableLabel1.setText("Your message has been encrypted!");
        selectableLabel2.setText(null);

    }
    // a message from user to be decrypted
    private void decrypt(){
        encryptedMessage = "";
        letters = null;
        String message = textField.getText();

        letters = message.toCharArray();
        for (int i =0; i<letters.length; i++){
            for (int j =0; j<shuffledList.size(); j++){
                if (shuffledList.get(j)==letters[i]){
                    letters[i]= list.get(j);
                    break;
                }
            }
        }

        String decryptedMessage = "";

        for (int i = 0; i < letters.length; i++) {
            decryptedMessage += letters[i];
        }
        textField.setText(decryptedMessage);
        encryptButton.setEnabled(true);
        decryptButton.setEnabled(false);
        selectableLabel1.setText("Your message has been decrypted!");
        selectableLabel2.setText(null);
    }

    // quit the program
    private void quit(){
        selectableLabel1.setText("Thank you! Have a nice day!");
        selectableLabel2.setText(null);
        System.exit(0);
    }

}