import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManagementApp extends JFrame {
    private JTextField nameField, emailField, courseField;
    private JTextArea resultArea;
    private JButton insertButton, updateButton, deleteButton, viewButton;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/my_database";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Riya@2921"; 
    private Connection connection;

    public StudentManagementApp() {
    	try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 60, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 60, 165, 25);
        add(emailField);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20, 100, 80, 25);
        add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(100, 100, 165, 25);
        add(courseField);

        insertButton = new JButton("Insert");
        insertButton.setBounds(20, 140, 80, 25);
        add(insertButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(110, 140, 80, 25);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(200, 140, 80, 25);
        add(deleteButton);

        viewButton = new JButton("View");
        viewButton.setBounds(290, 140, 80, 25);
        add(viewButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add a scroll pane for the resultArea
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(20, 180, 350, 150);
        add(scrollPane);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudents();
            }
        });
    }

    private void insertStudent() {
        String name = nameField.getText();
        String email = emailField.getText();
        String course = courseField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, course);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student inserted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateStudent() {
        String name = nameField.getText();
        String email = emailField.getText();
        String course = courseField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE students SET name = ?, course = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, course);
            statement.setString(3, email);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No student found with the given email!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteStudent() {
        String email = emailField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM students WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No student found with the given email!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void viewStudents() {
        resultArea.setText(""); // Clear previous results
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT name, email, course FROM students LIMIT 10";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String course = resultSet.getString("course");

                resultArea.append("Name: " + name + "\n");
                resultArea.append("Email: " + email + "\n");
                resultArea.append("Course: " + course + "\n");
                resultArea.append("--------------------\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementApp().setVisible(true);
        });
    }
}



//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class StudentManagementApp extends JFrame {
//    private JTextField nameField, emailField, courseField;
//    private JButton insertButton, updateButton, deleteButton, viewButton;
//
//    public StudentManagementApp() {
//        setTitle("Student Management System");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//
//        JLabel nameLabel = new JLabel("Name:");
//        nameLabel.setBounds(20, 20, 80, 25);
//        add(nameLabel);
//
//        nameField = new JTextField();
//        nameField.setBounds(100, 20, 165, 25);
//        add(nameField);
//
//        JLabel emailLabel = new JLabel("Email:");
//        emailLabel.setBounds(20, 60, 80, 25);
//        add(emailLabel);
//
//        emailField = new JTextField();
//        emailField.setBounds(100, 60, 165, 25);
//        add(emailField);
//
//        JLabel courseLabel = new JLabel("Course:");
//        courseLabel.setBounds(20, 100, 80, 25);
//        add(courseLabel);
//
//        courseField = new JTextField();
//        courseField.setBounds(100, 100, 165, 25);
//        add(courseField);
//
//        insertButton = new JButton("Insert");
//        insertButton.setBounds(20, 140, 80, 25);
//        add(insertButton);
//
//        updateButton = new JButton("Update");
//        updateButton.setBounds(110, 140, 80, 25);
//        add(updateButton);
//
//        deleteButton = new JButton("Delete");
//        deleteButton.setBounds(200, 140, 80, 25);
//        add(deleteButton);
//
//        viewButton = new JButton("View");
//        viewButton.setBounds(290, 140, 80, 25);
//        add(viewButton);
//
//        insertButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                insertStudent();
//            }
//        });
//
//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateStudent();
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteStudent();
//            }
//        });
//
//        viewButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                viewStudents();
//            }
//        });
//    }
//
//    private void insertStudent() {
//        String name = nameField.getText();
//        String email = emailField.getText();
//        String course = courseField.getText();
//
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, name);
//            statement.setString(2, email);
//            statement.setString(3, course);
//            statement.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Student inserted successfully!");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void updateStudent() {
//        // Similar to insertStudent, but with an UPDATE SQL query.
//    }
//
//    private void deleteStudent() {
//        // Similar to insertStudent, but with a DELETE SQL query.
//    }
//
//    private void viewStudents() {
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "SELECT * FROM students";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
//                String course = resultSet.getString("course");
//
//                // Display or process the result set.
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new StudentManagementApp().setVisible(true);
//        });
//    }
//}



//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class StudentManagementApp extends JFrame {
//    private JTextField nameField, emailField, courseField;
//    private JTextArea resultArea;
//    private JButton insertButton, updateButton, deleteButton, viewButton;
//
//    public StudentManagementApp() {
//        setTitle("Student Management System");
//        setSize(500, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//
//        JLabel nameLabel = new JLabel("Name:");
//        nameLabel.setBounds(20, 20, 80, 25);
//        add(nameLabel);
//
//        nameField = new JTextField();
//        nameField.setBounds(100, 20, 165, 25);
//        add(nameField);
//
//        JLabel emailLabel = new JLabel("Email:");
//        emailLabel.setBounds(20, 60, 80, 25);
//        add(emailLabel);
//
//        emailField = new JTextField();
//        emailField.setBounds(100, 60, 165, 25);
//        add(emailField);
//
//        JLabel courseLabel = new JLabel("Course:");
//        courseLabel.setBounds(20, 100, 80, 25);
//        add(courseLabel);
//
//        courseField = new JTextField();
//        courseField.setBounds(100, 100, 165, 25);
//        add(courseField);
//
//        insertButton = new JButton("Insert");
//        insertButton.setBounds(20, 140, 80, 25);
//        add(insertButton);
//
//        updateButton = new JButton("Update");
//        updateButton.setBounds(110, 140, 80, 25);
//        add(updateButton);
//
//        deleteButton = new JButton("Delete");
//        deleteButton.setBounds(200, 140, 80, 25);
//        add(deleteButton);
//
//        viewButton = new JButton("View");
//        viewButton.setBounds(290, 140, 80, 25);
//        add(viewButton);
//
//        resultArea = new JTextArea();
//        resultArea.setBounds(20, 180, 350, 150);
//        resultArea.setEditable(false);
//        add(resultArea);
//
//        insertButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                insertStudent();
//            }
//        });
//
//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateStudent();
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteStudent();
//            }
//        });
//
//        viewButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                viewStudents();
//            }
//        });
//    }
//
//    private void insertStudent() {
//        String name = nameField.getText();
//        String email = emailField.getText();
//        String course = courseField.getText();
//
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, name);
//            statement.setString(2, email);
//            statement.setString(3, course);
//            statement.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Student inserted successfully!");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void updateStudent() {
//        // Similar to insertStudent, but with an UPDATE SQL query.
//    }
//
//    private void deleteStudent() {
//        // Similar to insertStudent, but with a DELETE SQL query.
//    }
//
//    private void viewStudents() {
//        resultArea.setText(""); // Clear previous results
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "SELECT * FROM students";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
//                String course = resultSet.getString("course");
//
//                resultArea.append("Name: " + name + "\n");
//                resultArea.append("Email: " + email + "\n");
//                resultArea.append("Course: " + course + "\n");
//                resultArea.append("--------------------\n");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new StudentManagementApp().setVisible(true);
//        });
//    }
//}
