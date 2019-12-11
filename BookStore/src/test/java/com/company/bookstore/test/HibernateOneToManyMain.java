
package com.company.bookstore.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.util.HibernateUtil;

public class HibernateOneToManyMain {

	public static void main(String[] args) {

		Book book = new Book();
////		book.setId(12);
		book.setLanguage("EN");
		book.setCategory("web");
		book.setTitle("Book1");
		book.setYear(2019);
		book.setPrice(14.02);

		Author author1 = new Author();
////		author1.setId(12);
		author1.setAuthorName("abc");
		author1.setBook(book);

		Author author2 = new Author();
		author2.setId(12);
		author2.setAuthorName("def");
		author2.setBook(book);

		Set<Author> authors = new HashSet<Author>();
		authors.add(author1);
		authors.add(author2);

		book.setAuthors(authors);

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created");
//			// start transaction
			tx = session.beginTransaction();
//
//			// Save the Model objects
			session.save(book);
			session.save(author1);
			session.save(author2);

//			// Commit transaction
			tx.commit();
			System.out.println("Book ID=" + book.getId());
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

}
