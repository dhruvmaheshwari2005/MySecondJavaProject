package com.example.notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileMenuAction implements ActionListener {
    JFrame frame;
    JTextArea textArea;

    public FileMenuAction(JFrame frame, JTextArea textArea) {
        this.frame = frame;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Hello");
        // code for open
        openFileChooser();
    }

    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(frame);
        File openedFile = chooser.getSelectedFile();

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(openedFile))){
            String contentOfFile = new String(bis.readAllBytes());
            textArea.setText(contentOfFile);
        } catch(IOException exception) {
            System.out.println(exception);
        }
    }
}
