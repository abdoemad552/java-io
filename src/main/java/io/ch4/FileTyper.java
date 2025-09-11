package io.ch4;

import io.ch3.StreamCopier;

import java.io.FileInputStream;
import java.io.IOException;

public class FileTyper {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java io.FileTyper filename");
      return;
    }
    try {
      type(args[0]);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
  
  public static void type(String filename) throws IOException {
    try (FileInputStream fin = new FileInputStream(filename)) {
      StreamCopier.copy(fin, System.out);
    }
  }
}
