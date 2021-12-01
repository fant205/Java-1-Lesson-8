package ru.gb.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public static final String[] buttonsNames = {"7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "C", "0", "/", "="};
    StringBuilder a = new StringBuilder();
    StringBuilder b = new StringBuilder();
    String action = "";
    String result = "";

    Label label;

    public Main() {


        setBounds(500, 500, 400, 300);
        setTitle("GridLayoutDemo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout mainGridLayout = new GridLayout(2, 1);
        setLayout(mainGridLayout);

        label = new Label();
        label.setSize(new Dimension(100, 50));
        add(label);

        JPanel panel = new JPanel();
        GridLayout buttonsGridLayout = new GridLayout(4, 4);
        panel.setLayout(buttonsGridLayout);
        add(panel);


        for (int i = 0; i < 16; i++) {
            JButton button = new JButton(buttonsNames[i]);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!result.equals("")){
                        reset();
                    }

                    String text = button.getText();
                    int ch = text.charAt(0);

                    if (ch >= 48 && ch <= 57) {
                        if (action.equals("")) {
                            a.append(text);
                        } else {
                            b.append(text);
                        }
                    } else if (text.equals("C")) {
                        reset();
                    } else if (text.equals("=")) {
                        int aa = Integer.parseInt(a.toString());
                        int bb = Integer.parseInt(b.toString());
                        if (action.equals("+")) {
                            result = "=" + (aa + bb);
                        } else if (action.equals("-")) {
                            result = "=" + (aa - bb);
                        } else if (action.equals("x")) {
                            result = "=" + (aa * bb);
                        } else if (action.equals("/")) {
                            result = "=" + ((double) aa / (double) bb);
                        }
                    } else {
                        if(!a.toString().isEmpty()){
                            action = text;
                        }
                    }
                    label.setText(a.toString() + action + b.toString() + result);


                }
            });
            panel.add(button);
        }
        setVisible(true);
    }

    private void reset() {
        a.delete(0, a.length());
        b.delete(0, b.length());
        action = "";
        label.setText("");
        result = "";
    }

    public static void main(String[] args) {
        new Main();
    }
}
