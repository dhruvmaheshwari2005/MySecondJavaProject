package com.example.notepad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class Notepad extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, viewMenu;

    // File menu items
    JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem, printMenuItem;

    // Edit menu items
    JMenuItem cutMenuItem, copyMenuItem, pasteMenuItem, findMenuItem, replaceMenuItem, gotoMenuItem;

    // View menu items
    JMenuItem zoomMenuItem, wordWrapMenuItem, statusBarMenuItem;

    JTextArea mainTextArea;

    private void init() {
        // get the window width and height
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rectangle = env.getMaximumWindowBounds();
        int width = rectangle.width;
        int height = rectangle.height;

        // set the UI skin
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception exc) {
            System.out.println(exc);
        }

        menuBar = new JMenuBar();

        // menu
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");

        // add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // file menu items
        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save As");
        printMenuItem = new JMenuItem("Print");
        exitMenuItem = new JMenuItem("Exit");

        // add them in the file menu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);

        // edit menu items
        cutMenuItem = new JMenuItem("Cut");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        findMenuItem = new JMenuItem("Find");
        replaceMenuItem = new JMenuItem("Replace");
        gotoMenuItem = new JMenuItem("Go to");

        // add them in the edit menu
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(findMenuItem);
        editMenu.add(replaceMenuItem);
        editMenu.add(gotoMenuItem);

        // edit menu items
        zoomMenuItem = new JMenuItem("Zoom");
        wordWrapMenuItem = new JMenuItem("Word Wrap");
        statusBarMenuItem = new JMenuItem("Status Bar");

        // add them in the view menu item
        viewMenu.add(zoomMenuItem);
        viewMenu.add(wordWrapMenuItem);
        viewMenu.add(statusBarMenuItem);

        // text area
        mainTextArea = new JTextArea();



        // frame properties
        setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add components on the frame
        add(menuBar);
        add(mainTextArea);
        menuBar.setBounds(0, 0, width, 30);
        mainTextArea.setBounds(0, 30, width, height - 30);

        bindListeners();
    }

    private void bindListeners() {
        FileMenuAction fileMenuAction = new FileMenuAction(this, mainTextArea);
        newMenuItem.addActionListener(fileMenuAction);
        saveMenuItem.addActionListener(fileMenuAction);
        saveAsMenuItem.addActionListener(fileMenuAction);
        openMenuItem.addActionListener(fileMenuAction);
        printMenuItem.addActionListener(fileMenuAction);
        exitMenuItem.addActionListener(fileMenuAction);

        mainTextArea.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int wheelRotation = e.getWheelRotation();
                Font font = mainTextArea.getFont();
                if (wheelRotation == -1) {
                    mainTextArea.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() + 2));
                } else if (wheelRotation == 1) {
                    mainTextArea.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() - 2));
                }
            }
        });
    }

    public void openApplication() {
        init();
    }



    public static void main(String[] args) {
        new Notepad().openApplication();
    }
}
