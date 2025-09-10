package io.ch3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class RandomInputStream extends InputStream {
  
  private final Random generator = new Random();
  private boolean closed = false;

  public int read() throws IOException {
    checkOpen();
    int result = generator.nextInt() % 256;
    if (result < 0) result = -result;
    return result;
  }
  
  public int read(byte[] data, int offset, int len) throws IOException {
    checkOpen();
    byte[] temp = new byte[len];
    generator.nextBytes(temp);
    System.arraycopy(temp, 0, data, offset, len);
    return len;
  }
  
  public int read(byte[] data) throws IOException {
    checkOpen();
    generator.nextBytes(data);
    return data.length;
  }
  
  public long skip(long n) throws IOException {
    checkOpen();
    return n;
  }
  
  public void close() {
    this.closed = true;
  }
  
  private void checkOpen( ) throws IOException {
    if (closed) throw new IOException("Input stream closed");
  }
  
  public int available() {
    return Integer.MAX_VALUE;
  }
}
