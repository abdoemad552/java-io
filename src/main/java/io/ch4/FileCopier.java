package io.ch4;

import io.ch3.StreamCopier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopier {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: java FileCopier infile outfile");
    }
    try {
      copy(args[0], args[1]);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
  
  public static void copy(String infile,
                          String outfile) throws IOException {
    try(
      FileInputStream in = new FileInputStream(infile);
      FileOutputStream out = new FileOutputStream(outfile)
    ) {
      StreamCopier.copy(in, out);
    }
  }
}
