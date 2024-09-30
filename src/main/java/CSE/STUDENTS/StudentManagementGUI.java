//package com.students;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class StudentManagementGUI extends JFrame {
//    private JTextField nameField, ageField, deleteIdField, updateIdField, newNameField;
//    private JTextArea displayArea;
//    private StudentDAO studentDAO;
//
//    public StudentManagementGUI() {
//        studentDAO = new StudentDAO();
//        
//        setTitle("Student Management System");
//        setSize(400, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(5, 1));
//
//        // Insert Form
//        JPanel insertPanel = new JPanel(new GridLayout(3, 2));
//        insertPanel.setBorder(BorderFactory.createTitledBorder("Insert Student"));
//        nameField = new JTextField();
//        ageField = new JTextField();
//        JButton insertButton = new JButton("Insert");
//        insertButton.addActionListener(e -> insertStudent());
//        insertPanel.add(new JLabel("Name:"));
//        insertPanel.add(nameField);
//        insertPanel.add(new JLabel("Age:"));
//        insertPanel.add(ageField);
//        insertPanel.add(insertButton);
//        add(insertPanel);
//
//        // Delete Form
//        JPanel deletePanel = new JPanel(new GridLayout(2, 2));
//        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Student"));
//        deleteIdField = new JTextField();
//        JButton deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(e -> deleteStudent());
//        deletePanel.add(new JLabel("Student ID:"));
//        deletePanel.add(deleteIdField);
//        deletePanel.add(deleteButton);
//        add(deletePanel);
//
//        // Update Form
//        JPanel updatePanel = new JPanel(new GridLayout(3, 2));
//        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Student"));
//        updateIdField = new JTextField();
//        newNameField = new JTextField();
//        JButton updateButton = new JButton("Update");
//        updateButton.addActionListener(e -> updateStudent());
//        updatePanel.add(new JLabel("Student ID:"));
//        updatePanel.add(updateIdField);
//        updatePanel.add(new JLabel("New Name:"));
//        updatePanel.add(newNameField);
//        updatePanel.add(updateButton);
//        add(updatePanel);
//
//        // View Students
//        JPanel viewPanel = new JPanel(new BorderLayout());
//        viewPanel.setBorder(BorderFactory.createTitledBorder("View Students"));
//        displayArea = new JTextArea();
//        JButton viewButton = new JButton("View");
//        viewButton.addActionListener(e -> viewStudents());
//        viewPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
//        viewPanel.add(viewButton, BorderLayout.SOUTH);
//        add(viewPanel);
//
//        setVisible(true);
//    }
//
//    private void insertStudent() {
//        String name = nameField.getText();
//        int age = Integer.parseInt(ageField.getText());
//        studentDAO.insertStudent(new Student(name, age));
//    }
//
//    private void deleteStudent() {
//        int studentId = Integer.parseInt(deleteIdField.getText());
//        studentDAO.deleteStudent(studentId);
//    }
//
//    private void updateStudent() {
//        int studentId = Integer.parseInt(updateIdField.getText());
//        String newName = newNameField.getText();
//        studentDAO.updateStudent(studentId, newName);
//    }
//
//    private void viewStudents() {
//        displayArea.setText("");
//        studentDAO.viewStudents().forEach(student -> 
//            displayArea.append("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge() + "\n")
//        );
//    }
//
//    public static void main(String[] args) {
//        new StudentManagementGUI();
//    }
//}



package CSE.STUDENTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentManagementGUI extends JFrame {
    private JTextField nameField, ageField, deleteIdField, updateIdField, newNameField;
    private JTextArea displayArea;
    private StudentDAO studentDAO;

    public StudentManagementGUI() {
        studentDAO = new StudentDAO();
        
        // Set up the frame
        setTitle("Student Management System");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());  // Use GridBagLayout for flexible layout
        getContentPane().setBackground(Color.WHITE);  // Set background color
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Title Label
        JLabel lblTitle = new JLabel("Student Management", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(72, 61, 139));  // Dark blue title
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        // Insert Student Panel
        gbc.gridy++;
        gbc.gridwidth = 1;
        JPanel insertPanel = createInsertPanel();
        add(insertPanel, gbc);

        // Delete Student Panel
        gbc.gridy++;
        JPanel deletePanel = createDeletePanel();
        add(deletePanel, gbc);

        // Update Student Panel
        gbc.gridy++;
        JPanel updatePanel = createUpdatePanel();
        add(updatePanel, gbc);

        // View Students Panel
        gbc.gridy++;
        JPanel viewPanel = createViewPanel();
        add(viewPanel, gbc);
        
        setVisible(true);
    }

    private JPanel createInsertPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(105, 105, 105)), "Insert Student"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Name field
        panel.add(createStyledLabel("Name:"), gbc);
        gbc.gridx++;
        nameField = createStyledTextField();
        panel.add(nameField, gbc);

        // Age field
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createStyledLabel("Age:"), gbc);
        gbc.gridx++;
        ageField = createStyledTextField();
        panel.add(ageField, gbc);

        // Insert button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton insertButton = createStyledButton("Insert", e -> insertStudent());
        panel.add(insertButton, gbc);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(105, 105, 105)), "Delete Student"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Student ID field
        panel.add(createStyledLabel("Student ID:"), gbc);
        gbc.gridx++;
        deleteIdField = createStyledTextField();
        panel.add(deleteIdField, gbc);

        // Delete button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton deleteButton = createStyledButton("Delete", e -> deleteStudent());
        panel.add(deleteButton, gbc);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(105, 105, 105)), "Update Student"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Student ID field
        panel.add(createStyledLabel("Student ID:"), gbc);
        gbc.gridx++;
        updateIdField = createStyledTextField();
        panel.add(updateIdField, gbc);

        // New Name field
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createStyledLabel("New Name:"), gbc);
        gbc.gridx++;
        newNameField = createStyledTextField();
        panel.add(newNameField, gbc);

        // Update button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton updateButton = createStyledButton("Update", e -> updateStudent());
        panel.add(updateButton, gbc);

        return panel;
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(105, 105, 105)), "View Students"));

        displayArea = new JTextArea();
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        displayArea.setForeground(Color.DARK_GRAY);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JButton viewButton = createStyledButton("View", e -> viewStudents());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(viewButton, BorderLayout.SOUTH);

        return panel;
    }

    // Styled components
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(72, 61, 139));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(actionListener);
        return button;
    }

    private void insertStudent() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        
        if (name.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            studentDAO.insertStudent(new Student(name, age));
            JOptionPane.showMessageDialog(this, "Student inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Clear fields
            nameField.setText("");
            ageField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String idText = deleteIdField.getText();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the student ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int studentId = Integer.parseInt(idText);
            studentDAO.deleteStudent(studentId);
            JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        String idText = updateIdField.getText();
        String newName = newNameField.getText();
        
        if (idText.isEmpty() || newName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int studentId = Integer.parseInt(idText);
            studentDAO.updateStudent(studentId, newName);
            JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStudents() {
        displayArea.setText("");
        studentDAO.viewStudents().forEach(student -> 
            displayArea.append("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge() + "\n")
        );
    }


    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}





