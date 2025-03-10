import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Server {

    private static final int DEFAULT_PORT = 5555;

    public static void main(String[] args) {
        int port = getPortFromUser();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            System.out.println("Server IP address: " + getServerIPAddress());
            System.out.println("Waiting for clients...");

            // Server keeps running indefinitely
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // Handle client in a new thread
                    Thread clientThread = new Thread(new ClientHandler(clientSocket));
                    clientThread.start();
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port " + port);
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static int getPortFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("String Operations Server Setup");
        System.out.println("-----------------------------");
        System.out.print("Use default port (5555)? (Y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("n") || response.equals("no")) {
            while (true) {
                System.out.print("Enter server port (1024-65535): ");
                try {
                    int port = Integer.parseInt(scanner.nextLine().trim());
                    if (port >= 1024 && port <= 65535) {
                        return port;
                    } else {
                        System.out.println("Error: Port must be between 1024 and 65535.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid port number.");
                }
            }
        }

        return DEFAULT_PORT;
    }

    private static String getServerIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Could not determine IP address";
        }
    }

    // Inner class to handle client connections in separate threads
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                // Read the function choice (1 for palindrome check, 2 for string reversal)
                String choiceStr = in.readLine();
                try {
                    int choice = Integer.parseInt(choiceStr);

                    // Read the input string
                    String inputString = in.readLine();

                    if (inputString == null || inputString.isEmpty()) {
                        out.println("Error: Empty string provided");
                        return;
                    }

                    // Process based on the user's choice
                    switch (choice) {
                        case 1:
                            out.println(isPalindrome(inputString) ? "1" : "0");
                            break;
                        case 2:
                            out.println(reverseString(inputString));
                            break;
                        default:
                            out.println("Error: Invalid choice. Use 1 for palindrome check or 2 for string reversal");
                    }
                } catch (NumberFormatException e) {
                    out.println("Error: Invalid choice format. Please send 1 or 2");
                }
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                    System.out.println("Client disconnected");
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private boolean isPalindrome(String str) {
            // Remove spaces and convert to lowercase
            String processed = str.replaceAll("\\s+", "").toLowerCase();
            int length = processed.length();

            for (int i = 0; i < length / 2; i++) {
                if (processed.charAt(i) != processed.charAt(length - i - 1)) {
                    return false;
                }
            }
            return true;
        }

        private String reverseString(String str) {
            return new StringBuilder(str).reverse().toString();
        }
    }
}
