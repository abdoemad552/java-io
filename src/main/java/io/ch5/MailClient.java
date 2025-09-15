package io.ch5;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MailClient {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java MailClient username@host.com");
      System.exit(1);
    }
    
    try {
      URL url = new URL("mailto:" + args[0]);
      URLConnection connection = url.openConnection();
      
      connection.setDoOutput(true);
      connection.connect();
      
      OutputStream out = connection.getOutputStream();
      for (int c = System.in.read(); c != -1; c = System.in.read()) {
        out.write(c);
      }
      
      out.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
