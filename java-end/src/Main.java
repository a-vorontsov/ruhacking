import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class Main {

    private static ServerSocket welcomeSocket;
    private static Socket connectionSocket;
    private static Robot robot;

    private static char[] inputKeys = new char[4];
    private static char[] prevKeys = new char[4];
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        welcomeSocket = new ServerSocket(11011);
        robot = new Robot();

        while (true) {
            connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            if (clientSentence != null && !clientSentence.equals(" ")) {
                inputKeys = clientSentence.toCharArray();
                System.out.println(inputKeys);
                System.out.println(prevKeys);
                for (char input : inputKeys) {
                    readInput(input);
                }
                prevKeys = inputKeys.clone();
            } else {
                reset();
            }
            outToClient.writeBytes("OK");
        }
    }

    public static void readInput(char input) {
        System.out.println("press key " + ((new String(inputKeys).indexOf(input) != -1) && (new String(prevKeys).indexOf(input) == -1)));
        System.out.println("release key " + ((new String(inputKeys).indexOf(input) == -1) && (new String(prevKeys).indexOf(input) != -1)));
        if ((new String(inputKeys).indexOf(input) != -1) && (new String(prevKeys).indexOf(input) == -1)) {
            switch (input) {
                case 'L':
                    System.out.println("L mouse press");
                    robot.mousePress(InputEvent.getMaskForButton(1));
                    break;
                case 'R':
                    System.out.println("R mouse press");
                    robot.mousePress(InputEvent.getMaskForButton(3));
                    break;
                case 'W':
                    robot.keyPress(KeyEvent.VK_W);
                    break;
                case 'A':
                    robot.keyPress(KeyEvent.VK_A);
                    break;
                case 'S':
                    robot.keyPress(KeyEvent.VK_S);
                    break;
                case 'D':
                    robot.keyPress(KeyEvent.VK_D);
                    break;
                default:
                    break;
            }
        }
        for (char key: prevKeys) {
            if (new String(inputKeys).indexOf(key) == -1) {
                switch (key) {
                    case 'L':
                        System.out.println("L mouse release");
                        robot.mouseRelease(InputEvent.getMaskForButton(1));
                        break;
                    case 'R':
                        System.out.println("R mouse release");
                        robot.mouseRelease(InputEvent.getMaskForButton(3));
                        break;
                    case 'W':
                        System.out.println("W mouse release");
                        robot.keyRelease(KeyEvent.VK_W);
                        break;
                    case 'A':
                        System.out.println("A mouse release");
                        robot.keyRelease(KeyEvent.VK_A);
                        break;
                    case 'S':
                        System.out.println("S mouse release");
                        robot.keyRelease(KeyEvent.VK_S);
                        break;
                    case 'D':
                        System.out.println("D mouse release");
                        robot.keyRelease(KeyEvent.VK_D);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private static void reset() {
        System.out.println("reset called");
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_W);
        robot.mouseRelease(InputEvent.getMaskForButton(3));
        robot.mouseRelease(InputEvent.getMaskForButton(1));
    }
}
