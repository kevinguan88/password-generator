import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generator_UI extends JFrame implements ActionListener {
    private static final JCheckBox lowerCheck = new JCheckBox("Lowercase Letters");
    private static final JCheckBox upperCheck = new JCheckBox("Uppercase Letters");
    private static final JCheckBox numberCheck = new JCheckBox("Numerical Characters");
    private static final JCheckBox specialCheck = new JCheckBox("Special Characters");
    private static final JLabel passwordLength = new JLabel("Password Length:");
    private static JTextField passLengthField = new JTextField("14");
    private static final JButton button = new JButton("Generate Password");

    //Generated password elements
    private static String password = "";
    private static JTextField generatedPassword = new JTextField();
    private static final JLabel passwordLabel = new JLabel("Password: ");
    private static final JButton copyButton = new JButton("Copy to Clipboard");

    public Generator_UI() {
        //setting up frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Password Generator");
        setLayout(new BorderLayout());
        pack();
        setSize(425,210);
        setLocationRelativeTo(null);
        setResizable(false);

        //Lock icon by Icons8 https://icons8.com/icons/set/lock
        ImageIcon icon = new ImageIcon("password_icon.png");
        setIconImage(icon.getImage());

        //main panel containing sub-panels for user-input to generate password
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel passLengthPanel = new JPanel(new FlowLayout());
        JPanel checkBoxPanel = new JPanel(new FlowLayout());
        mainPanel.add(passLengthPanel, BorderLayout.NORTH);
        mainPanel.add(checkBoxPanel, BorderLayout.CENTER);
        lowerCheck.setSelected(true);
        upperCheck.setSelected(true);
        numberCheck.setSelected(true);
        passLengthField.setPreferredSize(new Dimension(20,20));
        passLengthField.setHorizontalAlignment(JTextField.CENTER);
        passLengthPanel.add(passwordLength);
        passLengthPanel.add(passLengthField);
        checkBoxPanel.add(lowerCheck);
        checkBoxPanel.add(upperCheck);
        checkBoxPanel.add(numberCheck);
        checkBoxPanel.add(specialCheck);
        checkBoxPanel.add(button);
        button.addActionListener(this);


        //generated password panel containing the generated password and a button to copy the text to user's clipboard
        generatedPassword.setPreferredSize(new Dimension(150,20));
        generatedPassword.setEditable(false);
        JPanel generatedPanel = new JPanel(new FlowLayout());
        generatedPanel.add(passwordLabel);
        generatedPanel.add(generatedPassword);
        generatedPanel.add(copyButton);
        mainPanel.add(generatedPanel, BorderLayout.SOUTH);
        copyButton.addActionListener(this);


        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void displayPassword(String password) {
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
            } catch (NumberFormatException f) {
                lengthIsInt = false;
                JOptionPane.showMessageDialog(this, "Error: please enter an integer", "Error", JOptionPane.WARNING_MESSAGE);
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
                        password = generator.generatePassword();
                        this.displayPassword(password);
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
