package io.ch5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class SocketTyper {
  private static final String CRLF = "\r\n";
  
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: java SocketTyper url");
      System.exit(1);
    }
    
    URL url = new URL(args[0]);
    if (!url.getProtocol().equalsIgnoreCase("http")) {
      System.err.println("Sorry, " + url.getProtocol() + " is not supported");
      System.exit(1);
    }
    
    String host = url.getHost();
    int port = url.getDefaultPort();
    String file = url.getFile();
    if (file == null) {
      file = "/";
    }
    
    System.out.println(host);
    System.out.println(port);
    System.out.println(file);
    
    Socket socket = null;
    try {
      socket = new Socket(host, port);
      String request = "GET " + file + " HTTP/1.1" + CRLF +
        "User-Agent: SocketTyper" + CRLF +
        "Accept: text/*" + CRLF +
        "Host: " + host + CRLF +
        CRLF;
      
      System.out.println(request);
      
      byte[] data = request.getBytes(US_ASCII);
      
      OutputStream out = socket.getOutputStream();
      InputStream in = socket.getInputStream();
      
      out.write(data);
      out.flush();
      
      for (int c = in.read(); c != -1; c = in.read()) {
        System.out.write(c);
      }
    } finally {
      if (socket != null && socket.isConnected()) {
        socket.close();
      }
    }
  }
}
