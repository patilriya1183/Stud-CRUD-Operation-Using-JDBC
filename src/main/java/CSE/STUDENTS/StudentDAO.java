//package com.students;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import java.util.List;
//
//public class StudentDAO {
//
//    public void insertStudent(Student student) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(student);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteStudent(int studentId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Student student = session.get(Student.class, studentId);
//            if (student != null) {
//                session.delete(student);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    public void updateStudent(int studentId, String newName) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Student student = session.get(Student.class, studentId);
//            if (student != null) {
//                student.setName(newName);
//                session.update(student);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    public List<Student> viewStudents() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from Student", Student.class).list();
//        }
//    }
//}

package CSE.STUDENTS;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//Example methods for StudentDAO class

public class StudentDAO {
 // Assuming Hibernate setup with SessionFactory
 private SessionFactory sessionFactory;

 public StudentDAO() {
     sessionFactory = HibernateUtil.getSessionFactory();
 }

 public void insertStudent(Student student) {
     Session session = sessionFactory.openSession();
     Transaction transaction = null;

     try {
         transaction = session.beginTransaction();
         session.save(student);  // Save the student object in the database
         transaction.commit();
     } catch (Exception e) {
         if (transaction != null) transaction.rollback();
         e.printStackTrace();
     } finally {
         session.close();
     }
 }

 public void deleteStudent(int id) {
     Session session = sessionFactory.openSession();
     Transaction transaction = null;

     try {
         transaction = session.beginTransaction();
         Student student = session.get(Student.class, id);  // Fetch the student
         if (student != null) {
             session.delete(student);  // Delete the student object
             transaction.commit();
         }
     } catch (Exception e) {
         if (transaction != null) transaction.rollback();
         e.printStackTrace();
     } finally {
         session.close();
     }
 }

 public void updateStudent(int id, String newName) {
     Session session = sessionFactory.openSession();
     Transaction transaction = null;

     try {
         transaction = session.beginTransaction();
         Student student = session.get(Student.class, id);  // Fetch the student
         if (student != null) {
             student.setName(newName);  // Update the student's name
             session.update(student);  // Update the student object
             transaction.commit();
         }
     } catch (Exception e) {
         if (transaction != null) transaction.rollback();
         e.printStackTrace();
     } finally {
         session.close();
     }
 }

 public List<Student> viewStudents() {
     Session session = sessionFactory.openSession();
     List<Student> students = null;

     try {
         students = session.createQuery("FROM Student", Student.class).list();  // Retrieve all students
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         session.close();
     }

     return students;
 }
}
