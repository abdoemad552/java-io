package io.ch5;

import io.ch2.JStreamedTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class URLViewer extends JFrame implements ActionListener {
  private JTextField theURL = new JTextField();
  private JButton loadButton = new JButton("Load");
  private JStreamedTextArea theDisplay = new JStreamedTextArea(60, 72);
  
  public URLViewer() {
    super("URL Viewer");
    getContentPane().add(BorderLayout.NORTH, theURL);
    JScrollPane pane = new JScrollPane(theDisplay);
    getContentPane().add(BorderLayout.CENTER, pane);
    JPanel south = new JPanel();
    south.add(loadButton);
    getContentPane().add(BorderLayout.SOUTH, south);
    theURL.addActionListener(this);
    loadButton.addActionListener(this);
    setLocation(50, 50);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      URL url = new URL(theURL.getText());
      InputStream in = url.openStream();
      OutputStream out = theDisplay.getOutputStream();
      theDisplay.setText("");
      for (int c = in.read(); c != -1; c = in.read()) {
        out.write(c);
      }
      in.close();
    } catch (IOException ex) {
      theDisplay.setText("Invalid URL: " + ex.getMessage( ));
    }
  }
  
  public static void main(String[] args) {
    URLViewer me = new URLViewer();
    SwingUtilities.invokeLater(() -> me.setVisible(true));
  }
}
