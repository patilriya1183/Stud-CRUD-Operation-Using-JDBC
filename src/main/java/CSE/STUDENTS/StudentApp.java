//package com.students;
//
//import javax.swing.*;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//import javax.swing.border.TitledBorder;
//
//
//public class StudentApp extends JFrame {
//    private JTextField nameField, ageField, idField, updateNameField, updateIdField;
//    private JTextArea resultArea;
//
//    public StudentApp() {
//        setTitle("Student Management System");
//        setLayout(new BorderLayout());
//        setBackground(Color.decode("#f8f9fa")); // Light gray background to match HTML
//
//        // Set background and add 3D-like panel
//        JPanel inputPanel = new JPanel();
//        inputPanel.setBackground(Color.decode("#f8f9fa")); // Light gray background
//        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        Font labelFont = new Font("Arial", Font.BOLD, 14);
//        Font buttonFont = new Font("Verdana", Font.PLAIN, 12);
//
//        // Insert Section
//        JPanel insertPanel = createPanel("Insert Student", Color.decode("#007bff")); // Blue header color
//        insertPanel.add(createFieldPanel("Name:", nameField = new JTextField(15), labelFont));
//        insertPanel.add(createFieldPanel("Age:", ageField = new JTextField(5), labelFont));
//        JButton insertButton = createStyledButton("Insert", Color.decode("#007bff"), Color.decode("#0056b3"), buttonFont); // Blue buttons
//        insertPanel.add(insertButton);
//
//        // Delete Section
//        JPanel deletePanel = createPanel("Delete Student", Color.decode("#dc3545")); // Red header color
//        deletePanel.add(createFieldPanel("ID to Delete:", idField = new JTextField(15), labelFont));
//        JButton deleteButton = createStyledButton("Delete", Color.decode("#dc3545"), Color.decode("#c82333"), buttonFont); // Red buttons
//        deletePanel.add(deleteButton);
//
//        // Update Section
//        JPanel updatePanel = createPanel("Update Student", Color.decode("#ffc107")); // Yellow header color
//        updateIdField = new JTextField(5);
//        updateNameField = new JTextField(15);
//        updatePanel.add(createFieldPanel("ID:", updateIdField, labelFont));
//        updatePanel.add(createFieldPanel("New Name:", updateNameField, labelFont));
//        JButton updateButton = createStyledButton("Update", Color.decode("#ffc107"), Color.decode("#e0a800"), buttonFont); // Yellow buttons
//        updatePanel.add(updateButton);
//
//        // View Button
//        JButton viewButton = createStyledButton("View All", Color.decode("#28a745"), Color.decode("#218838"), buttonFont); // Green buttons
//
//        // Add Panels to Input Panel
//        inputPanel.add(insertPanel);
//        inputPanel.add(deletePanel);
//        inputPanel.add(updatePanel);
//        inputPanel.add(viewButton);
//
//        // Result Display
//        resultArea = new JTextArea(10, 30);
//        resultArea.setEditable(false);
//        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
//        resultArea.setForeground(Color.decode("#004d00")); // Dark green text
//        resultArea.setBackground(Color.WHITE);
//        resultArea.setBorder(BorderFactory.createLineBorder(Color.decode("#007bff"))); // Blue border
//
//        JScrollPane scrollPane = new JScrollPane(resultArea);
//        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#007bff"), 2));
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//
//        // Set Button Actions
//        insertButton.addActionListener(e -> insertStudent());
//        deleteButton.addActionListener(e -> deleteStudent());
//        updateButton.addActionListener(e -> updateStudent(updateIdField.getText(), updateNameField.getText()));
//        viewButton.addActionListener(e -> viewStudents());
//
//        add(inputPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Window Settings
//        setSize(700, 600); // Adjusted size for more space
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    // Create a styled panel
//    private JPanel createPanel(String title, Color headerColor) {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(headerColor, 2), title, 
//            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), headerColor));
//        panel.setBackground(Color.decode("#f8f9fa")); // Light gray background
//        return panel;
//    }
//
//    // Create a field panel with label and text field
//    private JPanel createFieldPanel(String labelText, JTextField textField, Font labelFont) {
//        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        JLabel label = new JLabel(labelText);
//        label.setFont(labelFont);
//        label.setForeground(Color.decode("#333333"));
//        addStyledField(textField);
//        panel.add(label);
//        panel.add(textField);
//        return panel;
//    }
//
//    // Method to style text fields with a rounded border and padding
//    private void addStyledField(JTextField field) {
//        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        field.setBackground(Color.WHITE);
//        field.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(Color.decode("#CCCCCC"), 2), // Border
//            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding
//        ));
//        field.setPreferredSize(new Dimension(200, 40)); // Increase height for modern look
//    }
//
//    // Method to create a styled button
//    private JButton createStyledButton(String text, Color bgColor, Color hoverColor, Font font) {
//        JButton button = new JButton(text);
//        button.setFont(font);
//        button.setBackground(bgColor); // Button color
//        button.setForeground(Color.WHITE); // White text
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(bgColor.darker(), 2), // Darker border
//                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding
//        ));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button.setBackground(hoverColor); // Darker color on hover
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button.setBackground(bgColor); // Reset to original color
//            }
//        });
//        return button;
//    }
//
//    // Pop-up success or failure messages
//    private void showPopup(String message, boolean success) {
//        JOptionPane.showMessageDialog(this, message, success ? "Success" : "Error",
//                success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
//    }
//
//    // Insert student
//    private void insertStudent() {
//        String name = nameField.getText();
//        int age = Integer.parseInt(ageField.getText());
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Student student = new Student();
//        student.setName(name);
//        student.setAge(age);
//        session.save(student);
//        transaction.commit();
//        session.close();
//        showPopup("Inserted: " + student.getName(), true);
//        resultArea.append("Inserted: " + student.getName() + "\n");
//    }
//
//    // Delete student
//    private void deleteStudent() {
//        int id = Integer.parseInt(idField.getText());
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Student student = session.get(Student.class, id);
//        if (student != null) {
//            session.delete(student);
//            transaction.commit();
//            showPopup("Deleted: " + student.getName(), true);
//            resultArea.append("Deleted: " + student.getName() + "\n");
//        } else {
//            showPopup("Student not found!", false);
//        }
//        session.close();
//    }
//
//    // Update student
//    private void updateStudent(String id, String newName) {
//        int studentId = Integer.parseInt(id);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Student student = session.get(Student.class, studentId);
//        if (student != null) {
//            student.setName(newName);
//            session.update(student);
//            transaction.commit();
//            showPopup("Updated: " + student.getName(), true);
//            resultArea.append("Updated: " + student.getName() + "\n");
//        } else {
//            showPopup("Student not found!", false);
//        }
//        session.close();
//    }
//
//    // View all students
//    private void viewStudents() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        List<Student> students = session.createQuery("FROM Student", Student.class).list();
//        StringBuilder result = new StringBuilder("Students:\n");
//        for (Student student : students) {
//            result.append("ID: ").append(student.getId()).append(", Name: ").append(student.getName()).append(", Age: ").append(student.getAge()).append("\n");
//        }
//        resultArea.setText(result.toString());
//        session.close();
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(StudentApp::new);
//    }
//}













package CSE.STUDENTS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentApp extends JFrame {
    private JTextField nameField, ageField, idField, updateIdField, updateNameField;
    private JTextArea resultArea;

    public StudentApp() {
        setTitle("Basic Student Management System");
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Main container panel
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(4, 1, 10, 10));
        containerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        containerPanel.setBackground(Color.WHITE);

        // Font styles
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Insert Section
        JPanel insertPanel = createPanel("Insert Student", buttonFont);
        insertPanel.add(createFieldPanel("Name:", nameField = new JTextField(), labelFont));
        insertPanel.add(createFieldPanel("Age:", ageField = new JTextField(), labelFont));
        JButton insertButton = createStyledButton("Insert", buttonFont, new Color(50, 150, 50));
        insertPanel.add(insertButton);

        // Delete Section
        JPanel deletePanel = createPanel("Delete Student", buttonFont);
        deletePanel.add(createFieldPanel("ID:", idField = new JTextField(), labelFont));
        JButton deleteButton = createStyledButton("Delete", buttonFont, new Color(200, 50, 50));
        deletePanel.add(deleteButton);

        // Update Section
        JPanel updatePanel = createPanel("Update Student", buttonFont);
        updateIdField = new JTextField();
        updateNameField = new JTextField();
        updatePanel.add(createFieldPanel("ID:", updateIdField, labelFont));
        updatePanel.add(createFieldPanel("New Name:", updateNameField, labelFont));
        JButton updateButton = createStyledButton("Update", buttonFont, new Color(50, 50, 150));
        updatePanel.add(updateButton);

        // View Section
        JButton viewButton = createStyledButton("View All", buttonFont, new Color(50, 150, 200));

        // Add Panels to Container Panel
        containerPanel.add(insertPanel);
        containerPanel.add(deletePanel);
        containerPanel.add(updatePanel);
        containerPanel.add(viewButton);

        // Result Display
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setForeground(Color.DARK_GRAY);
        resultArea.setBackground(Color.LIGHT_GRAY);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set Button Actions
        insertButton.addActionListener(e -> insertStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        updateButton.addActionListener(e -> updateStudent(updateIdField.getText(), updateNameField.getText()));
        viewButton.addActionListener(e -> viewStudents());

        add(containerPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Window Settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Create a styled panel
    private JPanel createPanel(String title, Font buttonFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                new Color(50, 50, 50)
        ));
        panel.setBackground(Color.WHITE);
        return panel;
    }


    // Create a field panel with label and text field
    private JPanel createFieldPanel(String labelText, JTextField textField, Font labelFont) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.DARK_GRAY);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        textField.setPreferredSize(new Dimension(300, 40));
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    // Method to create a styled button
    private JButton createStyledButton(String text, Font font, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(backgroundColor.darker(), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        return button;
    }

    // Insert student
    private void insertStudent() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        resultArea.append("Inserted: Name: " + name + ", Age: " + age + "\n");
        // Here you would typically save this data to a database
    }

    // Delete student
    private void deleteStudent() {
        int id = Integer.parseInt(idField.getText());
        resultArea.append("Deleted: ID: " + id + "\n");
        // Here you would typically delete this data from a database
    }

    // Update student
    private void updateStudent(String id, String newName) {
        int studentId = Integer.parseInt(id);
        resultArea.append("Updated: ID: " + studentId + ", New Name: " + newName + "\n");
        // Here you would typically update this data in a database
    }

    // View all students
    private void viewStudents() {
        resultArea.setText("Displaying all students:\n");
        // Here you would typically retrieve and display data from a database
        resultArea.append("ID: 1, Name: John Doe, Age: 20\n");
        resultArea.append("ID: 2, Name: Jane Smith, Age: 22\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentApp::new);
    }
}






//package com.students;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//public class StudentApp extends JFrame {
//
//    private static final Configuration CONFIGURATION = new Configuration().configure().addAnnotatedClass(Student.class);
//    private static final SessionFactory SESSION_FACTORY = CONFIGURATION.buildSessionFactory();
//
//    private JTextField insertField1, insertField2, insertField3;
//    private JTextField deleteField;
//    private JTextField updateField1, updateField2;
//    private JTextArea outputArea;
//    private JPanel mainPanel;
//
//    public StudentApp() {
//        setTitle("Library Management System - STUDENT");
//        setSize(900, 1000);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Main panel layout
//        mainPanel = new JPanel(new GridBagLayout());
//        mainPanel.setBackground(Color.WHITE);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//
//        // Title
//        JLabel lblTitle = createStyledLabel("Student Management Operations", 24, new Color(72, 61, 139));
//        gbc.gridy = 0;
//        mainPanel.add(lblTitle, gbc);
//
//        // Insert Panel
//        JPanel insertPanel = createSectionPanel("Insert Student", new String[]{"Student Name:", "Student ID:", "Course:"});
//        insertField1 = (JTextField) insertPanel.getComponent(1);
//        insertField2 = (JTextField) insertPanel.getComponent(3);
//        insertField3 = (JTextField) insertPanel.getComponent(5);
//        JButton btnInsert = createStyledButton("Insert");
//        insertPanel.add(btnInsert);
//        gbc.gridy++;
//        mainPanel.add(insertPanel, gbc);
//
//        // Delete Panel
//        JPanel deletePanel = createSectionPanel("Delete Student", new String[]{"Delete Student by ID:"});
//        deleteField = (JTextField) deletePanel.getComponent(1);
//        JButton btnDelete = createStyledButton("Delete");
//        deletePanel.add(btnDelete);
//        gbc.gridy++;
//        mainPanel.add(deletePanel, gbc);
//
//        // Update Panel
//        JPanel updatePanel = createSectionPanel("Update Student", new String[]{"Update Student Name:", "Student ID (Mandatory):"});
//        updateField1 = (JTextField) updatePanel.getComponent(1);
//        updateField2 = (JTextField) updatePanel.getComponent(3);
//        JButton btnUpdate = createStyledButton("Update");
//        updatePanel.add(btnUpdate);
//        gbc.gridy++;
//        mainPanel.add(updatePanel, gbc);
//
//        // View Panel
//        JPanel viewPanel = new JPanel(new BorderLayout());
//        viewPanel.setBorder(BorderFactory.createTitledBorder("View Students"));
//        JButton btnView = createStyledButton("View Students");
//        outputArea = new JTextArea(10, 50);
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//        viewPanel.add(btnView, BorderLayout.NORTH);
//        viewPanel.add(scrollPane, BorderLayout.CENTER);
//        gbc.gridy++;
//        mainPanel.add(viewPanel, gbc);
//
//        // Adding main panel to frame
//        add(mainPanel);
//
//        // Logout Button
//        JButton btnLogout = createStyledButton("Logout");
//        gbc.gridy++;
//        mainPanel.add(btnLogout, gbc);
//        add(mainPanel);
//
//        btnLogout.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(
//                        null,
//                        "You have been successfully logged out.",
//                        "Logout Successful",
//                        JOptionPane.INFORMATION_MESSAGE);
//                dispose(); // close the current window
//                // new HomeUI().setVisible(true); // open the home interface
//            }
//        });
//
//        // Action Listeners
//        btnInsert.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Session session = SESSION_FACTORY.openSession();
//                Transaction tx = session.beginTransaction();
//                String studentName = insertField1.getText();
//                int studentID = Integer.parseInt(insertField2.getText());
//                String course = insertField3.getText();
//                Student student = new Student();
//                student.setName(studentName);
//                student.setId(studentID);
//                student.setAge(studentID);
//                session.save(student);
//                tx.commit();
//                session.close();
//                outputArea.append("Inserted: " + studentName + ", " + studentID + ", " + course + "\n");
//            }
//        });
//
//        btnDelete.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Session session = SESSION_FACTORY.openSession();
//                Transaction tx = session.beginTransaction();
//                int studentID = Integer.parseInt(deleteField.getText());
//                Student student = (Student) session.get(Student.class, studentID);
//                if (student != null) {
//                    session.delete(student);
//                    outputArea.append("Deleted: Student ID " + studentID + "\n");
//                } else {
//                    outputArea.append("No student found with ID " + studentID + "\n");
//                }
//                tx.commit();
//                session.close();
//            }
//        });
//
//        btnUpdate.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Session session = SESSION_FACTORY.openSession();
//                Transaction tx = session.beginTransaction();
//                String newStudentName = updateField1.getText();
//                int studentID = Integer.parseInt(updateField2.getText());
//                Student student = (Student) session.get(Student.class, studentID);
//                if (student != null) {
//                    student.setName(newStudentName);
//                    session.update(student);
//                    outputArea.append("Updated: Student ID " + studentID + " to " + newStudentName + "\n");
//                } else {
//                    outputArea.append("No student found with ID " + studentID + "\n");
//                }
//                tx.commit();
//                session.close();
//            }
//        });
//
//        btnView.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Session session = SESSION_FACTORY.openSession();
//                java.util.List<Student> students = session.createQuery("from Student", Student.class).getResultList();
//                outputArea.setText("");
//                for (Student student : students) {
//                    outputArea.append("Student Name: " + student.getName() + ", ID: " + student.getId()+ ", Course: " + student.getAge() + "\n");
//                }
//                session.close();
//            }
//        });
//    }
//
//    // Helper methods for consistent styling
//    private JButton createStyledButton(String text) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Arial", Font.BOLD, 14));
//        button.setBackground(new Color(72, 61, 139));
//        button.setForeground(Color.WHITE);
//        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
//        button.setFocusPainted(false);
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        return button;
//    }
//
//    private JLabel createStyledLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Arial", Font.PLAIN, 14));
//        label.setForeground(new Color(60, 60, 60));
//        return label;
//    }
//
//    private JLabel createStyledLabel(String text, int fontSize, Color color) {
//        JLabel label = new JLabel(text, SwingConstants.CENTER);
//        label.setFont(new Font("Arial", Font.BOLD, fontSize));
//        label.setForeground(color);
//        return label;
//    }
//
//    private JPanel createSectionPanel(String title, String[] labels) {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBorder(BorderFactory.createTitledBorder(title));
//        panel.setBackground(Color.WHITE);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        for (int i = 0; i < labels.length; i++) {
//            JLabel lbl = createStyledLabel(labels[i]);
//            gbc.gridx = 0;
//            gbc.gridy = i;
//            panel.add(lbl, gbc);
//
//            JTextField textField = new JTextField(20);
//            gbc.gridx = 1;
//            panel.add(textField, gbc);
//        }
//
//        return panel;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new StudentApp().setVisible(true);
//            }
//        });
//    }
//}



