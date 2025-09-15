package io.ch5;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class HelloServer {
  private static final int PORT = 2345;
  private static final String CRLF = "\r\n";
  
  public static void main(String[] args) throws IOException {
    ServerSocket server = new ServerSocket(PORT);
    while (true) {
      try {
        Socket client = server.accept();
        String response = "Hello " + client.getInetAddress()
          + " on port " + client.getPort() + CRLF
          + "This is " + client.getLocalAddress()
          + " on port " + client.getLocalPort() + CRLF;
        
        OutputStream out = client.getOutputStream();
        out.write(response.getBytes(US_ASCII));
        out.flush();
        
        client.close();
      } catch (IOException e) {
      }
    }
  }
}
