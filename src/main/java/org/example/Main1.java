package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Robot;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;


/**
 * UI => desktop applications
 * 1. Notepad
 * 2. Paint
 * 3. Calculator
 * 4. Browser
 * 5. Media Player
 * 6. Multimedia (images, videos, music)
 * 7. Netbeans
 * AWT => java.awt (Abstract Window Toolkit)
 * Swing => javax.swing
 */

/*
    Notepad
    1. Container => something on which components will be placed.
    Frame, Panel
    2. Component
    Event => Action (Code should run)
 */

public class Main1 extends JFrame  {
    JButton btn;
    JButton btn2;

    Main1() {
        setVisible(true);
        setLayout(null);
        setSize(500, 500);
        setLocation(10, 10);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        btn = new JButton("Jhola Chapph");
        btn2 = new JButton("Chitti");

        OtherClass o = new OtherClass(this, btn, btn2);
        btn.addActionListener(o);
        btn2.addActionListener(o);

        add(btn);
        add(btn2);
        btn.setBounds(100, 100, 100, 30);
        btn2.setBounds(100, 300, 100, 30);
    }





    public static void main(String[] args) {
        new Main1();
    }

}

class OtherClass implements  ActionListener {

    JButton btn, btn2;
    JFrame frame;

    OtherClass(JFrame frame, JButton btn, JButton btn2) {
        this.btn = btn;
        this.btn2 = btn2;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
//            JDialog dialog = new JDialog(this, "Dummy Dialog");
//            dialog.setVisible(true);
//            dialog.setSize(100,100);
            int op = JOptionPane.showConfirmDialog(frame, "Are you baawla?");
            System.out.println(op);

        } else if (e.getSource() == btn2  && Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                // desktop.open(new File("C:\\Windows\\notepad.exe"));
                // desktop.browse(new URI("https://mail.google.com/mail/u/0/#inbox"));
                desktop.mail(new URI("mailto:sushiljangid69@gmail.com"));
            } catch(IOException exception) {
                System.out.println(exception);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
