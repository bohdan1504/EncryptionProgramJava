import javax.swing.*;
import java.util.*;

public class EncryptionProgram {

    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;

    JButton newKeyButton;
//    JButton getKeyButton;
//    JButton encryptButton;
//    JButton decryptButton;
//    JButton quitButton;
//
//    JTextField textField;
//    JLabel label;
//
//    JPanel panel1;
//    JPanel panel2;


    EncryptionProgram(){

//        this.setSize(500,500);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(null);
        
        scanner = new Scanner(System.in);
        random = new Random();
        list = new ArrayList();
        shuffledList = new ArrayList<>();
        character = ' ';

//        newKeyButton = new JButton("New key");
//        getKeyButton = new JButton("Get key");
//        encryptButton = new JButton("Encrypt");
//        decryptButton = new JButton("Decrypt");
//        quitButton = new JButton("Quit");
//
//        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//        panel1.add(newKeyButton);
//        panel1.add(getKeyButton);
//        panel1.add(encryptButton);
//        panel1.add(decryptButton);
//        panel1.add(quitButton);


        newKey();
        askQuestion();
        
//        this.add(panel1);
//        this.add(panel2);
//        this.setVisible(true);

    }

    private void askQuestion(){
        while(true){
            System.out.println("*************************************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response){
                case 'N': newKey();
                break;
                case 'G': getKey();
                    break;
                case 'E': encrypt();
                    break;
                case 'D': decrypt();
                    break;
                case 'Q': quit();
                default:
                    System.out.println("Not a valid answer :(");

            }

        }
    }

    private void newKey(){
        character = ' ';
        list.clear();
        shuffledList.clear();

        for (int i=32; i<127; i++){
            list.add(Character.valueOf(character));
            character++;
        }
        shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);
        System.out.println("*A new key has been generated*");
    }


    private void getKey(){
        System.out.println("Key: ");
        for (Character x : list){
            System.out.print(x);
        }
        System.out.println();
        for (Character x : shuffledList){
            System.out.print(x);
        }
        System.out.println();
    }

    private void encrypt(){
        System.out.println("Enter a message to be encrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();
        for (int i =0; i<letters.length; i++){
            for (int j =0; j<list.size(); j++){
                if (letters[i]==list.get(j)){
                    letters[i]=shuffledList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted: ");
        for (char x : letters){
            System.out.print(x);
        }
        System.out.println();
//        System.out.println(letters);
    }

    private void decrypt(){
        System.out.println("Enter a message to be decrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();
        for (int i =0; i<letters.length; i++){
            for (int j =0; j<shuffledList.size(); j++){
                if (shuffledList.get(j)==letters[i]){
                    letters[i]= list.get(j);
                    break;
                }
            }
        }
        System.out.println("Decrypted: ");
        for (char x : letters){
            System.out.print(x);
        }
        System.out.println();
//        System.out.println(letters);
    }

    private void quit(){
        System.out.println("Thank you! Have a nice day, bro");
        System.exit(0);
    }

}