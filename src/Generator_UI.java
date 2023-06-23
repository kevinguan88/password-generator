import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generator_UI extends JFrame implements ActionListener {
    private static JCheckBox lowerCheck = new JCheckBox("Lowercase Letters");
    private static JCheckBox upperCheck = new JCheckBox("Uppercase Letters");
    private static JCheckBox numberCheck = new JCheckBox("Numerical Characters");
    private static JCheckBox specialCheck = new JCheckBox("Special Characters");
    private static JLabel passwordLength = new JLabel("Password Length:");
    private static JTextField passLengthField = new JTextField("8");
    private static JButton button = new JButton("Generate Password");
    private static JPanel panel = new JPanel();
    private static JLabel title = new JLabel("Password Generator");

    //Generated password elements
    private static String password = "";
    private static JTextField generatedPassword = new JTextField();
    private static JLabel passwordLabel = new JLabel("Password: ");
    private static JButton copyButton = new JButton("Copy to Clipboard");

    public Generator_UI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Password Generator");
        this.setLayout(new BorderLayout());
        this.pack();
        this.setSize(400,225);
        button.addActionListener(this);

        //TODO: Properly size and format all elements


        //main panel containing sub-panels for user-input to generate password
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel passLengthPanel = new JPanel(new FlowLayout());
        JPanel checkBoxPanel = new JPanel(new FlowLayout());
        mainPanel.add(passLengthPanel, BorderLayout.NORTH);
        mainPanel.add(checkBoxPanel, BorderLayout.CENTER);
        mainPanel.setBackground(Color.gray);
        passLengthPanel.add(passwordLength);
        passLengthPanel.add(passLengthField);
        checkBoxPanel.add(lowerCheck);
        checkBoxPanel.add(upperCheck);
        checkBoxPanel.add(numberCheck);
        checkBoxPanel.add(specialCheck);
        checkBoxPanel.add(button);

        //generated password panel containing the generated password and a button to copy the text to user's clipboard
        JPanel generatedPanel = new JPanel(new FlowLayout());
        generatedPanel.add(passwordLabel);
        generatedPanel.add(generatedPassword);
        generatedPanel.add(copyButton);
        mainPanel.add(generatedPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);


/*

        panel.add(passwordLength);
        panel.add(passLength);
        panel.add(lowerCheck);
        panel.add(upperCheck);
        panel.add(numberCheck);
        panel.add(specialCheck);
        panel.add(button);
*/

        this.setVisible(true);

       /* generatedPassword.setEditable(false);
        panel.add(passwordLabel);
        panel.add(generatedPassword);
        panel.add(copyButton); */
        passwordLabel.setVisible(false);
        generatedPassword.setVisible(false);
        copyButton.setVisible(false);
    }

    private void displayPassword(String password) {
        copyButton.addActionListener(this);
        generatedPassword.setText(password);
        generatedPassword.setVisible(true);
        copyButton.setVisible(true);
        passwordLabel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if the event is from the generate password button
        if (e.getSource() == button) {
            PasswordGenerator generator = new PasswordGenerator();
            boolean lengthIsInt = true;
            try {
                Integer.parseInt(passLengthField.getText());
                System.out.println("length is int");
            } catch (NumberFormatException f) {
                lengthIsInt = false;
                JOptionPane.showMessageDialog(this, "Error: please enter an integer", "Error", JOptionPane.WARNING_MESSAGE);
                System.out.println("length is not int");
            }
            if (lengthIsInt) {
                if (Integer.parseInt(passLengthField.getText()) < 8) { // if user enters a length less than 8
                    JOptionPane.showMessageDialog(this, "Error: please enter a length of at least 8", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    //if no options are selected
                    if (!lowerCheck.isSelected() && !upperCheck.isSelected() && !numberCheck.isSelected() && !specialCheck.isSelected()) {
                        JOptionPane.showMessageDialog(this, "Error: please select at least one character option.", "Error", JOptionPane.WARNING_MESSAGE);
                        System.out.println("no options selected");
                    } else {
                        //when the user-defined length is an int of at least 8 and at least one option is selected
                        generator.setPasswordLength(Integer.parseInt(passLengthField.getText()));
                        if (lowerCheck.isSelected()) {
                            generator.setHasLowerCase(true);
                        }
                        if (upperCheck.isSelected()) {
                            generator.setHasUpperCase(true);
                        }
                        if (numberCheck.isSelected()) {
                            generator.setHasNumbers(true);
                        }
                        if (specialCheck.isSelected()) {
                            generator.setHasSpecialChar(true);
                        }
                        System.out.println("password generating");
                        password = generator.generatePassword();
                        this.displayPassword(password);
                        System.out.println("password generated");
                    }
                }
            }
        }
        //if the event is from the copy to clipboard button
        if (e.getSource() == copyButton){
            StringSelection stringSelectionObj = new StringSelection(password);
            Clipboard clipboardObj = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboardObj.setContents(stringSelectionObj, null);
        }
    }
}
