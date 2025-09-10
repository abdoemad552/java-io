package io.ch3;

import java.io.IOException;

public class StreamPrinter {
  public static void main(String[] args) {
    try {
      int b;
      while ((b = System.in.read()) != -1) {
        System.out.println(b);
      }
    } catch (IOException e) {
      System.err.println("Couldn't read from System.in!");
    }
  }
}
