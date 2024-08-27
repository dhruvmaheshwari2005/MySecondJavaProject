package com.example.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;


public class Notepad extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, viewMenu;
    JPopupMenu popup;
    JMenuBar statusBar;
    int width, height;

    JLabel charCountLabel, wordCountLabel;


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
        width = rectangle.width;
        height = rectangle.height;

        // set the UI skin
        try {
            UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
        } catch (Exception exc) {
            System.out.println(exc);
        }

        menuBar = new JMenuBar();
        statusBar = new JMenuBar();

        // status bar label
        charCountLabel = new JLabel();
        wordCountLabel = new JLabel();

        // menu
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");

        // popup menu
        popup = new JPopupMenu();

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

//        popup.add(cutMenuItem);
//        popup.add(copyMenuItem);
//        popup.add(pasteMenuItem);

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
        add(statusBar);
        statusBar.add(wordCountLabel);
        statusBar.add(charCountLabel);

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
                    if (e.getPreciseWheelRotation() < 0) {
                        mainTextArea.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() + 2));
                    } else {
                        mainTextArea.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() - 2));
                    }
                }
            }
        });

        System.out.println(Thread.currentThread().getName());
        // creating a thread for tracking changes on main textarea
        new Thread(new StatusBar(), "Status Bar Thread").start();

        wordWrapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem checkBoxMenuItem = (JCheckBoxMenuItem) e.getSource();
                mainTextArea.setLineWrap(checkBoxMenuItem.getState());
            }
        });

        mainTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    // right click
                    mainTextArea.add(popup);
                    popup.setVisible(true);
                    popup.setLocation(e.getX(), e.getY() + popup.getHeight());
                } else if (e.getButton() == 1) {
                    popup.setVisible(false);
                }
            }
        });

        cutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = mainTextArea.getSelectedText();
                mainTextArea.replaceSelection("");
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(content), null);
            }
        });

        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                if (transferable != null) {
                    try {
                        String content = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                        mainTextArea.replaceSelection(content);
                        System.out.println(content);
                    } catch (UnsupportedFlavorException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
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

    // nested class for showing data on the status bar
    class StatusBar implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            mainTextArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    statusBar.setBounds(0, getHeight() - 60, width, 30);
                    charCountLabel.setText("Char count: " + mainTextArea.getText().length());
                }
            });
        }

        public StatusBar() {

        }
    }
}
