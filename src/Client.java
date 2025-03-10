import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    private static final String DEFAULT_SERVER_ADDRESS = "localhost";
    private static final int DEFAULT_SERVER_PORT = 5555;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("String Operations Client");
        System.out.println("------------------------");

        // Get server connection details from user
        String serverAddress = getServerAddress(scanner);
        int serverPort = getServerPort(scanner);

        try {
            System.out.println("Connecting to server at " + serverAddress + ":" + serverPort + "...");
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server.");

            // Create input and output streams
            try (
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                // Get operation choice from user
                int choice = getChoice(scanner);

                // Get the string from user
                System.out.print("Enter a string: ");
                String inputString = scanner.nextLine();

                // Send data to server
                out.println(choice);
                out.println(inputString);

                // Receive and display response
                String response = in.readLine();

                if (response != null) {
                    if (response.startsWith("Error:")) {
                        System.out.println(response);
                    } else if (choice == 1) {
                        if (response.equals("1")) {
                            System.out.println("Result: \"" + inputString + "\" is a palindrome.");
                        } else if (response.equals("0")) {
                            System.out.println("Result: \"" + inputString + "\" is not a palindrome.");
                        } else {
                            System.out.println("Unexpected response from server: " + response);
                        }
                    } else {
                        System.out.println("Result: " + response);
                    }
                } else {
                    System.out.println("No response received from server.");
                }
            }

            // Close the connection
            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("Error: Could not find the server at " + serverAddress);
            System.err.println("Make sure the server address is correct.");
        } catch (ConnectException e) {
            System.err.println("Error: Could not connect to the server at " + serverAddress + ":" + serverPort);
            System.err.println("Make sure the server is running and the port is correct.");
        } catch (IOException e) {
            System.err.println("Error communicating with the server: " + e.getMessage());
        }
    }

    private static String getServerAddress(Scanner scanner) {
        System.out.println("Server connection setup:");
        System.out.print("Use localhost (default)? (Y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("n") || response.equals("no")) {
            System.out.print("Enter server IP address: ");
            String address = scanner.nextLine().trim();
            return address.isEmpty() ? DEFAULT_SERVER_ADDRESS : address;
        }

        return DEFAULT_SERVER_ADDRESS;
    }

    private static int getServerPort(Scanner scanner) {
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

        return DEFAULT_SERVER_PORT;
    }

    private static int getChoice(Scanner scanner) {
        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Choose an operation:");
            System.out.println("1. Check if a string is a palindrome");
            System.out.println("2. Reverse a string");
            System.out.print("Enter your choice (1 or 2): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1 || choice == 2) {
                    validChoice = true;
                } else {
                    System.out.println("Error: Please enter either 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number (1 or 2).");
            }
        }

        return choice;
    }
}