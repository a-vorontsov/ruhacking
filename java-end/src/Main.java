import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.concurrent.TimeUnit;

class Main {

    private static ServerSocket welcomeSocket;
    private static Socket connectionSocket;
    private static Robot robot;

    private static char[] inputKeys = new char[6];
    private static char[] prevKeys = new char[6];
    public static void main(String argv[]) throws Exception {
        getCursor();
        // String clientSentence;
        // welcomeSocket = new ServerSocket(11011);
        // robot = new Robot();

        // while (true) {
        //     connectionSocket = welcomeSocket.accept();
        //     connectionSocket.setSoTimeout(2000);
        //     try {
        //         BufferedReader inFromClient =
        //         new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        //         DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        //         clientSentence = inFromClient.readLine();
        //         System.out.println("Received: " + clientSentence);
        //         if (clientSentence != null && !clientSentence.equals(" ")) {
        //             inputKeys = clientSentence.toCharArray();
        //             for (char input : inputKeys) {
        //                 readInput(input);
        //             }
        //             prevKeys = inputKeys.clone();
        //         }
        //         outToClient.writeBytes("OK");
        //     } catch (SocketException error) {
        //         reset();
        //     }
        // }
    }

    public static void readInput(char input) {
        if ((new String(inputKeys).indexOf(input) != -1) && (new String(prevKeys).indexOf(input) == -1)) {
            switch (input) {
                case 'L':
                    robot.mousePress(InputEvent.getMaskForButton(1));
                    break;
                case 'R':
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
                        robot.mouseRelease(InputEvent.getMaskForButton(1));
                        break;
                    case 'R':
                        robot.mouseRelease(InputEvent.getMaskForButton(3));
                        break;
                    case 'W':
                        robot.keyRelease(KeyEvent.VK_W);
                        break;
                    case 'A':
                        robot.keyRelease(KeyEvent.VK_A);
                        break;
                    case 'S':
                        robot.keyRelease(KeyEvent.VK_S);
                        break;
                    case 'D':
                        robot.keyRelease(KeyEvent.VK_D);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void reset() {
        System.out.println("reset keys");
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_W);
        robot.mouseRelease(InputEvent.getMaskForButton(3));
        robot.mouseRelease(InputEvent.getMaskForButton(1));
    }

    public static void getCursor() {
        PointerInfo cursor = MouseInfo.getPointerInfo();
        while (true) {
            try {
                Thread.sleep(500);
                int xCoord = (int) cursor.getLocation().x + 5;
                int yCoord = (int) cursor.getLocation().y;
                robot.mouseMove(xCoord , yCoord);
                System.out.println(MouseInfo.getPointerInfo().getLocation().getX() + ", " + MouseInfo.getPointerInfo().getLocation().getY());
            } catch (Exception error) {
                System.err.println(error.fillInStackTrace());
            }
        }
    }
}
