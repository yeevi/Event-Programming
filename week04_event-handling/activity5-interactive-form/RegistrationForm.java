import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck;
    private JCheckBox musicCheck;
    private JCheckBox readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JLabel statusLabel;
    private JButton submitButton;
    private JButton clearButton;
    private JButton exitButton;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel using GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Full Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        formPanel.add(new JLabel("Full Name:"), gbc);
        nameField = new JTextField(30);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(nameField, gbc);

        // 2. Email
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.2;
        formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(30);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(emailField, gbc);

        // 3. Password
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.2;
        formPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(30);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(passwordField, gbc);

        // 4. Gender (Radio Buttons)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.2;
        formPanel.add(new JLabel("Gender:"), gbc);
        JPanel genderPanel = new FlowLayout(FlowLayout.LEFT, 10, 0) == null ? new JPanel() : new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(genderPanel, gbc);

        // 5. Interests (Checkboxes)
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.2;
        formPanel.add(new JLabel("Interests:"), gbc);
        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(interestsPanel, gbc);

        // 6. Country (Dropdown)
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.2;
        formPanel.add(new JLabel("Country:"), gbc);
        String[] countries = {"Kenya", "Tanzania", "Uganda", "Rwanda"};
        countryBox = new JComboBox<>(countries);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(countryBox, gbc);

        // 7. Comments (TextArea inside JScrollPane)
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Comments:"), gbc);
        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane commentsScroll = new JScrollPane(commentsArea);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(commentsScroll, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Bottom section: Status + Buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(40, 167, 69));
        submitButton.setForeground(Color.WHITE);

        clearButton = new JButton("Clear");

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // EVENT HANDLERS
        submitButton.addActionListener(this::submitButtonActionPerformed);
        clearButton.addActionListener(this::clearButtonActionPerformed);
        exitButton.addActionListener(this::exitButtonActionPerformed);
    }

    private void submitButtonActionPerformed(ActionEvent evt) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter your name");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Please enter a valid email");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (password.length() < 6) {
            statusLabel.setText("Error: Password must be at least 6 characters");
            statusLabel.setForeground(Color.RED);
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        // Gather country & comments
        String country = (String) countryBox.getSelectedItem();
        String comments = commentsArea.getText().trim();

        // Show confirmation dialog
        String message = "Registration Successful!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n" +
                "Country: " + country + "\n" +
                "Comments: " + (comments.isEmpty() ? "None" : comments);

        JOptionPane.showMessageDialog(this, message, "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    private void exitButtonActionPerformed(ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
