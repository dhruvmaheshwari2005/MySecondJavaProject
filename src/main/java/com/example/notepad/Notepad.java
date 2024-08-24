package com.example.notepad;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.security.Key;


public class Notepad extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, viewMenu;



    // File menu items
    JMenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem, printMenuItem;

    // Edit menu items
    JMenuItem cutMenuItem, copyMenuItem, pasteMenuItem, findMenuItem, replaceMenuItem, gotoMenuItem;

    // View menu items
    JCheckBoxMenuItem zoomMenuItem, wordWrapMenuItem, statusBarMenuItem;

    JTextArea mainTextArea;

    boolean fileChanged;

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
        newMenuItem = new JMenuItem("New", new ImageIcon("new.png"));
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

        // view menu items
        zoomMenuItem = new JCheckBoxMenuItem("Zoom", false);
        wordWrapMenuItem = new JCheckBoxMenuItem("Word Wrap", false);
        statusBarMenuItem = new JCheckBoxMenuItem("Status Bar", false);

        // add them in the view menu item
        viewMenu.add(zoomMenuItem);
        viewMenu.addSeparator();
        viewMenu.add(wordWrapMenuItem);
        viewMenu.add(statusBarMenuItem);

        // text area
        mainTextArea = new JTextArea();



        // frame properties
        setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Untitled");

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

        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);
        viewMenu.setMnemonic(KeyEvent.VK_V);

        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        mainTextArea.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    Font font = mainTextArea.getFont();
                    System.out.println(e.getWheelRotation());
                    if (e.getPreciseWheelRotation() < 0) {
                        mainTextArea.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() + 2));
                    } else {
                        mainTextArea.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() - 2));
                    }
                }
            }
        });

        mainTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trackFileChanges(e);
            }
        });

        wordWrapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem checkBoxMenuItem = (JCheckBoxMenuItem) e.getSource();
                mainTextArea.setLineWrap(checkBoxMenuItem.getState());
            }
        });
    }

    public void openApplication() {
        init();
    }

    private void trackFileChanges(KeyEvent e) {
        if (getTitle().contains("Untitled")) {
            // check for some text
            if (!mainTextArea.getText().isBlank()) {
                setTitle("Untitled" + "*");
                fileChanged = true;
            } else {
                setTitle("Untitled");
                fileChanged = false;
            }
        } else {

        }
    }

    public static void main(String[] args) {
        new Notepad().openApplication();
    }
}
