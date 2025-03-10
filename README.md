<h2 align="center">Distributed Systems Security</h2>
<h3 align="center">Ahmed Walid</h3>

<p align="center">
    Cross Platform Java server and Client using sockets
    <br />
    <a href="https://github.com/ahmeddwalid/DSSsockets/blob/main/README.md"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/ahmeddwalid/DSSsockets/issues">Report Bug</a>
    ·
    <a href="https://github.com/ahmeddwalid/DSSsockets/pulls">Request Feature</a>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project

This Project consists of two parts:
1. **Server**: Having two functions:
   - Checking if a string is a palindrome
   - Reversing a string

2. **Client**: Connects to the server, sends requests, and displays results.

<!-- FEATURES -->
## Features

- Configurable Network Settings
- Multithreaded server
- Input Validation
- Error Handling
- Isolated client handling failures (one client error won't affect other clients)
- **Clean code**

<!-- Prerequisites  -->
## Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA IDE (recommended)

<!-- Server Implementation-->
## Server Implementation

The server listens for client connections and processes string manipulation requests.

Featuring:

1. **Configurable Port**:
   - The user can choose to use the default port (8888) or specify a custom port (1024-65535).
   - Validates user input to ensure the port is within valid range.

2. **Multithreaded Architecture**:
   - Each client connection is handled in a separate thread by the `ClientHandler` class.
   - Allows the server to handle multiple clients simultaneously without blocking.

3. **Network Information Display**:
   - Shows the server's IP address when started, making it easier for clients whom are on different machine to connect.
     **Note:** If the client and the server are on the same machine, use localhost or any loopback IP address actually (127.0.0.0/8)

4. **String Manipulation Functions**:
   - **Palindrome Check**: Checks if a string reads the same backward as forward.
      - Returns "1" for palindromes, "0" for non-palindromes.
      - Ignores spaces and isn't case-sensitive for easier use.
   - **String Reversal**: Reverses the input string.
      - Uses Java's StringBuilder for efficient string manipulation.

5. **Error Handling**:
   - Socket binding errors
   - Connection acceptance errors (ex: if the port is already in use or if there are firewall restrictions)
   - Client communication errors
   - Invalid input handling

## The Server's sequence
1. The server starts and prompts for port configuration.
2. After binding to the port, it displays its IP address and waits for client connections.
3. When a client connects, a new thread is created to handle that client.
4. The handler thread:
   - Reads the function choice (1 or 2)
- Reads the input string
- Processes the request based on the choice
- Sends the result back to the client
- Closes the connection
5. The main server thread continues listening for new connections.

<!-- Client Implementation-->
## Client Implementation

The client connects to the server, sends requests and displays results.

Featuring:

1. **Configurable Connection Settings**:
   - **Server Address**: Use localhost (default) or specify a custom IP address.
   - **Server Port**: Use default port (5555) or specify a custom port.

2. **User Interaction**:
   - Clear and formatted output of results.

3. **Operation Modes**:
   - **Palindrome Check**: Displays whether the string is a palindrome.
   - **String Reversal**: Shows the reversed string.

4. **Error Handling**:
   - Connection failures
   - Invalid server address
   - Invalid port numbers
   - Server communication errors
   - Input validation

## Runtime

#### Server Side:
```  
String Operations Server Setup  
-----------------------------  
Use default port (5555)? (Y/n): y  
Server started on port 8888  
Server IP address: 192.168.57.1
Waiting for clients...  
Client connected: 127.0.0.1  
Client disconnected  
```  

#### Client Side:
```  
String Operations Client  
------------------------  
Server connection setup:  
Use localhost (default)? (Y/n): n  
Enter server IP address: 127.0.0.1  
Use default port (5555)? (Y/n): y  
Connecting to server at 127.0.0.1:5555...  
Connected to server.  
Choose an operation:  
1. Check if a string is a palindrome  
2. Reverse a string  
Enter your choice (1 or 2): 1  
Enter a string: Ahmed  
Result: "Ahmed" is not a palindrome.  
```

<!-- CONTRIBUTING -->
# Contributing

Project's Link: [https://github.com/ahmeddwalid/DSSsockets](https://github.com/ahmeddwalid/DSSsockets)

Any contributions you make are **greatly appreciated**.

Features to be implemented:
- **Security:**
   - Adding authentication for client connections
   - Implementing encryption for data transmission
   - Adding server system logs

- **User Interface:** A long stretch but a GUI possibly?

### How to Contribute:

If you'd like to contribute, please follow these steps:

-  **Fork the repository:** Create your own copy of the project.
-  **Create a branch:** `git checkout -b feature/your-feature-name`
-  **Make your changes:** Implement your contribution.
-  **Commit your changes:** `git commit -m "Add your descriptive commit message"`
-  **Push to the branch:** `git push origin feature/your-feature-name`
-  **Create a pull request:** Submit your changes for review.

### Contribution Guidelines

- Write clear and concise commit messages.
- Provide detailed explanations in your pull requests.
- Be respectful and considerate of other contributors.


Thank you for your contributions!


<!-- LICENSE -->
# License

This project is distributed under the [Apache 2.0 license](https://choosealicense.com/licenses/apache-2.0/). See
[```LICENSE.txt```](/LICENSE) for more information.

<p align="right">(<a href="#top">back to top</a>)</p>