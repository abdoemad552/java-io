package io.ch5;

import io.ch3.StreamCopier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTyper {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: java URLTyper url");
      System.exit(1);
    }
    
    InputStream in = null;
    try {
      URL url = new URL(args[0]);
      in = url.openStream();
      for (int c = in.read(); c != -1; c = in.read()) {
        System.out.write(c);
      }
    } catch (MalformedURLException e) {
      System.err.println(args[0] + " is not a URL java understands");
    } finally {
      if (in != null) in.close();
    }
  }
}
