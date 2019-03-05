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

 
public class Main {
	public static void main(String[] args) throws SQLException {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				Ch5Configuration.class);
		
		EntityManagerFactory entityManagerFactory = applicationContext
				.getBean(EntityManagerFactory.class);
		
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		
		
		DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
		
		Connection connection = dataSource.getConnection();
		if(connection != null) {
			System.out.println("Connected");
		}else
			System.out.println("Not Connected");
		
//		
		transaction.begin();
//
		Student student = new Student();
		student.setFirstName("touhidul");
		student.setLastName("islam");
		
		Book book2 = new Book() ; 
		book2.setName("book1");
		 
		Book book3 = new Book() ; 
		book3.setName("book2");
		
		student.getBooks().add(book2) ; 
		student.getBooks().add(book3) ; 
//
		entityManager.persist(student);
		
		transaction.commit();
		entityManager.close();
		
		// another way 
//		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
//				.addAnnotatedClass(Student.class).buildSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		
//		try {
//  
//			Transaction transaction = session.beginTransaction();
//			
//			Student student = new Student();
//			student.setFirstName("John");
//			student.setLastName("Smith");
// 			
//			session.save(student);
//			transaction.commit();
//
// 			
// 		}
//		catch (Exception exception) {
//			exception.printStackTrace();
//		}
//		finally {
//			sessionFactory.close();
//		}

	}

}