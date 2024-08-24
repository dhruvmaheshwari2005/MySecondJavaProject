package com.example.notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileMenuAction implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    String fileName;

    public FileMenuAction(JFrame frame, JTextArea textArea) {
        this.frame = frame;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem clickedMenuItem = (JMenuItem) event.getSource();

        System.out.println("Hello");

        if (clickedMenuItem.getText().equals("Open")) {
            // code for open
            openFileChooser();
        } else if (clickedMenuItem.getText().equals("Save")) {
            System.out.println("Saving the file");
            // save the file
            saveFile();
        }
    }

    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(frame);
        File openedFile = chooser.getSelectedFile();

        if (openedFile != null) {
            fileName = openedFile.getAbsolutePath();
            frame.setTitle(fileName);

            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(openedFile))){
                String contentOfFile = new String(bis.readAllBytes());
                textArea.setText(contentOfFile);
            } catch(IOException exception) {
                System.out.println(exception);
            }
        }
    }

    private void saveFile() {
        if (fileName == null) {
            // this is new file need to open save as
        } else {
            System.out.println("Saving the existing file");
            // there is existing file need to save that
            String updatedContent = textArea.getText();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
                bos.write(updatedContent.getBytes());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
