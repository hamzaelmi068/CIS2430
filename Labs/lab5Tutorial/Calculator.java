package CIS2430.Labs.lab5Tutorial;

// import javax.swing.JFrame; dont need this, swing.* has this
// import javax.swing.JPanel; dont need this, swing.* has this
// import javax.swing.JButton; dont need this, swing.* has this
import javax.swing.*; // this includes JFrame, JPanel, JButton, JTextArea
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextArea equationDisplay;
    private float x;
    private char operation;

    // calculator constrcutor
    public Calculator() {
        x = 0.0f;
        operation = '\0';

        setSize(450, 600);
        setTitle("CIS*2430 Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // clean up my program, return back to wahtever terminal i
                                                        // started with, when pressing x
        setLocationRelativeTo(null); // place it in the center of the screen, so now it'll pop up in the top left

        // mow, we start creating the digits the user buttons, addition and subtraction,
        // and a button
        // to clear, in a function

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // sets the layout sequentially

        this.equationDisplay = new JTextArea(5, 10);
        equationDisplay.setMaximumSize(new Dimension(450, 100)); // setting the size of the equation to be abit larger
        equationDisplay.setEditable(false);

        add(equationDisplay);

        add(initBtnPanel());

        setVisible(true);
    }

    private JPanel initBtnPanel() {
        // creating our buttons for the panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4, 4)); // this sets our buttons in a 4 by 4 format in the panel

        // to make things less repetitive with the buttons, declaring an array for our
        // buttons to alleviate repetitive code
        String[] btnLabels = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "=", "C" }; // 16
                                                                                                                 // total
                                                                                                                 // elements
        for (String label : btnLabels) {
            JButton btn = new JButton(label);
            btn.addActionListener(this);
            btnPanel.add(btn);
        }
        return btnPanel;
    }

    public static void main(String[] args) {
        new Calculator();
    }

    private void clear() {
        x = 0.0f;
        operation = '\0';

    }

    private void clearDisplay() {
        equationDisplay.setText("C");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnLabel = e.getActionCommand();

        // If a number is pressed
        if (btnLabel.charAt(0) >= '0' && btnLabel.charAt(0) <= '9') {
            equationDisplay.setText(equationDisplay.getText() + btnLabel);
        }
        // If equals is pressed
        else if (btnLabel.equals("=")) {
            float currentValue = Float.parseFloat(equationDisplay.getText());
            switch (operation) {
                case '+':
                    x += currentValue;
                    break;
                case '-':
                    x -= currentValue;
                    break;
                case '*':
                    x *= currentValue;
                    break;
                case '/':
                    x /= currentValue;
                    break;
                default:
                    x = currentValue;
                    break;
            }
            equationDisplay.setText(Float.toString(x));
            operation = '\0';
        }
        // If clear is pressed
        else if (btnLabel.equals("C")) {
            clear();
            equationDisplay.setText("");
        }
        // If an operation button is pressed
        else {
            // If there's a previous value, perform the previous operation first
            if (operation != '\0') {
                float currentValue = Float.parseFloat(equationDisplay.getText());
                switch (operation) {
                    case '+':
                        x += currentValue;
                        break;
                    case '-':
                        x -= currentValue;
                        break;
                    case '*':
                        x *= currentValue;
                        break;
                    case '/':
                        x /= currentValue;
                        break;
                }
            } else {
                // If no previous operation, store the current value
                x = Float.parseFloat(equationDisplay.getText());
            }

            // Set new operation and clear display
            operation = btnLabel.charAt(0);
            equationDisplay.setText("");
        }
    }
}