package io.ch3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamCopier {
  private static final int BUFFER_SIZE = 1024;
  
  public static void main(String[] args) {
    try {
      copy(System.in, System.out);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
  
  public static void copy(InputStream in,
                          OutputStream out) throws IOException {
    int count;
    byte[] buf = new byte[BUFFER_SIZE];
    while ((count = in.read(buf)) != -1) {
      out.write(buf, 0, count);
    }
  }
}
