package io.ch4;

import io.ch3.StreamCopier;

import java.io.FileInputStream;
import java.io.IOException;

public class FileDumper {
  public enum Mode {
    ASC, DEC, HEX
  }
  
  private static final String SEPARATOR_LINE
    = "\r\n------------------------------------\r\n";
  
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Usage: java FileDumper [-ahd] file1 file2...");
      System.exit(1);
    }
    
    int i = 0;
    Mode mode = Mode.ASC;
    if (args[0].startsWith("-")) {
      if (args[0].equals("-h")) {
        mode = Mode.HEX;
      } else if (args[0].equals("-d")) {
        mode = Mode.DEC;
      }
      i++;
    }
    
    while (i < args.length) {
      try {
        dump(args[i], mode);
        if (i < args.length - 1) {
          System.out.print(SEPARATOR_LINE);
        }
      } catch (IOException e) {
        System.err.println("Error: reading from " + args[i] + ": "
          + e.getMessage());
      }
      i++;
    }
  }
  
  public static void dump(String filename, Mode mode) throws IOException {
    switch (mode) {
      case ASC -> dumpAscii(filename);
      case DEC -> dumpDecimal(filename);
      case HEX -> dumpHex(filename);
      default -> throw new RuntimeException();
    }
  }
  
  public static void dumpAscii(String filename) throws IOException {
    try (FileInputStream fin = new FileInputStream(filename)) {
      StreamCopier.copy(fin, System.out);
    }
  }
  
  public static void dumpDecimal(String filename) throws IOException {
    boolean end = false;
    byte[] buf = new byte[16];
    try (FileInputStream fin = new FileInputStream(filename)) {
      while (!end) {
        int bytesRead = 0;
        while (bytesRead < buf.length) {
          int count = fin.read(buf, bytesRead, buf.length - bytesRead);
          if (count == -1) {
            end = true;
            break;
          }
          bytesRead += count;
        }
        for (int i = 0; i < bytesRead; i++) {
          int dec = buf[i];
          if (dec < 0) dec += 256;
          if (dec < 10) System.out.print("00" + dec);
          else if (dec < 100) System.out.print("0" + dec);
          else System.out.print(dec);
          System.out.print(" ");
        }
        if (!end) {
          System.out.println();
        }
      }
    }
  }
  
  public static void dumpHex(String filename) throws IOException {
    boolean end = false;
    byte[] buf = new byte[16];
    try (FileInputStream fin = new FileInputStream(filename)) {
      while (!end) {
        int bytesRead = 0;
        while (bytesRead < buf.length) {
          int count = fin.read(buf, bytesRead, buf.length - bytesRead);
          if (count == -1) {
            end = true;
            break;
          }
          bytesRead += count;
        }
        for (int i = 0; i < bytesRead; i++) {
          int hex = buf[i];
          if (hex < 0) hex += 256;
          if (hex >= 16) System.out.print(Integer.toHexString(hex));
          else System.out.print("0" + Integer.toHexString(hex));
          System.out.print(" ");
        }
        if (!end) {
          System.out.println();
        }
      }
    }
  }
}
