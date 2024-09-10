//package org.example;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.FlowLayout;
//import javax.swing.BoxLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class Main extends JFrame {
//    JMenuBar topMenuBar, bottomMenuBar;
//    JTextArea mainTextArea;
//    public Main() {
//
//        LayoutManager2 m = new
//
//        setSize(1000, 1000);
//        setLayout(new BorderLayout());
//        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        topMenuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        fileMenu.add(new JMenuItem("New"));
//        topMenuBar.add(fileMenu);
//        bottomMenuBar = new JMenuBar();
//        bottomMenuBar.add(new JLabel("I am bottom menu bar"));
//        mainTextArea = new JTextArea("Hello");
//
//        add(topMenuBar, BorderLayout.NORTH);
//        add(mainTextArea, BorderLayout.CENTER);
//        add(bottomMenuBar, BorderLayout.SOUTH);
//
//         Grid
//        setLayout(new GridLayout(3, 3, 10, 10));
//
//        JButton[] buttons = new JButton[9];
//        for (int i = 0; i < 9; i++) {
//            buttons[i] = new JButton("Button"+ (i + 1));
//            add(buttons[i]);
//        }
//
//        setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        // Label: Name
//        gbc.gridx = 0; // Column 0
//        gbc.gridy = 0; // Row 0
//        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
//        add(new Label("Name:"), gbc);
//
//        // TextField: Name
//        gbc.gridx = 1; // Column 1
//        gbc.gridy = 0; // Row 0
//        gbc.fill = GridBagConstraints.HORIZONTAL | GridBagConstraints.CENTER; // Fill horizontally
//        gbc.weightx = 1.0; // Expand to fill the remaining space in the row
//        add(new TextField(20), gbc);
//
//        // Label: Email
//        gbc.gridx = 0; // Column 0
//        gbc.gridy = 1; // Row 1
//        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
//        gbc.weightx = 0; // Reset weightx to prevent it from affecting other components
//        add(new Label("Email:"), gbc);
//
//        // TextField: Email
//        gbc.gridx = 1; // Column 1
//        gbc.gridy = 1; // Row 1
//        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
//        gbc.weightx = 1.0; // Expand to fill the remaining space in the row
//        add(new TextField(20), gbc);
//
//        // Label: Phone
//        gbc.gridx = 0; // Column 0
//        gbc.gridy = 2; // Row 2
//        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
//        gbc.weightx = 0; // Reset weightx
//        add(new Label("Phone:"), gbc);
//
//        // TextField: Phone
//        gbc.gridx = 1; // Column 1
//        gbc.gridy = 2; // Row 2
//        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
//        gbc.weightx = 1.0; // Expand to fill the remaining space in the row
//        add(new TextField(20), gbc);
//
//        // Submit Button
//        gbc.gridx = 1; // Column 1
//        gbc.gridy = 3; // Row 3
//        gbc.anchor = GridBagConstraints.WEST; // Left-align the button
//        gbc.fill = GridBagConstraints.NONE; // No fill
//        gbc.weightx = 0; // Reset weightx
//        add(new Button("Submit"), gbc);
//
//        // Show the frame
//        setVisible(true);
//
//        CardLayout cardLayout = new CardLayout();
//        Container f = getContentPane();
//        setLayout(cardLayout);
//        JButton[] buttons = new JButton[9];
//        for (int i = 0; i < 9; i++) {
//            buttons[i] = new JButton("Button"+ (i + 1));
//            add(buttons[i]);
//            buttons[i].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    cardLayout.next(f);
//                }
//            });
//        }
//
//        setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
//        JButton[] buttons = new JButton[9];
//        for (int i = 0; i < 9; i++) {
//            buttons[i] = new JButton("Button"+ (i + 1));
//            add(buttons[i]);
//        }
//    }
//
//
//    public static void main(String[] args) {
//        new Main();
//    }
//}
