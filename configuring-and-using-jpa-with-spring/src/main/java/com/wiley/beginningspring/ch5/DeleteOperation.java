package com.wiley.beginningspring.ch5;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

 
public class DeleteOperation {
	public static void main(String[] args) throws SQLException {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				Ch5Configuration.class);
		
		EntityManagerFactory entityManagerFactory = applicationContext
				.getBean(EntityManagerFactory.class);
		
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		// perform delete 
		Student deleteStudent = entityManager.getReference(Student.class, 2L) ; 
		entityManager.remove(deleteStudent); // book will also be deleted 
		
		// perform update 
		Student updatedStudent = entityManager.find(Student.class, 1L) ; 
		updatedStudent.setFirstName("Md. Touhidul"); // previous touhid
		
		transaction.commit();
		entityManager.close();
		
		 
	}

}