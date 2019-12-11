/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.dao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.company.bookstore.controller.BookStoreController;
import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.util.HibernateUtil;

/**
 * The Class BookStoreDao.
 */
@ManagedBean
@SessionScoped
public class BookStoreDao {

	private final static Logger logger = Logger.getLogger(BookStoreDao.class);

	/**
	 * This method used to store books in Database.
	 *
	 * @param books the books
	 * @return true, if successful
	 */
	public boolean storeBooks(List<Book> books) {
		logger.debug(this.getClass() + "storeBooks(List<Book> books) is being invoked");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			if (!books.isEmpty()) {
				// Save the Model objects
				for (Book book : books) {
					// start transaction
					transaction = session.beginTransaction();
					session.saveOrUpdate(book);
					logger.info(this.getClass() + ">>>" + book.getTitle() + "<<<");
					for (Author author : book.getAuthors()) {
						session.saveOrUpdate(author);
						logger.info(this.getClass() + ">>>" + author.getAuthorName() + "<<<");
					}
					// Commit transaction
					transaction.commit();
				}
			}

		} catch (Exception ex) {
			logger.error(this.getClass() + "storeBooks(List<Book> books): An error occurred while loading books", ex);
			// handle exception here
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (Exception ex) {
			}
		}
		logger.debug(this.getClass() + "storeBooks(List<Book> books) is ended");
		return true;
	}

	/**
	 * This method used to get book list from database.
	 *
	 * @return the book list
	 */
	public List<Book> getBookList() {
		logger.debug(this.getClass() + "getBookList() is being invoked");
		Session session = null;
		Transaction transaction = null;
		List<Book> bookList = null;
		try {
			session = HibernateUtil.getSession();
			// getting transaction object from session object
			transaction = session.beginTransaction();
			Query query = session.createQuery("select bok from Book bok");
			bookList = query.list();
			transaction.commit();
		} catch (Exception ex) {
			logger.error(this.getClass() + "getBookList(): An error occurred while getting books", ex);
			// handle exception here
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (Exception ex) {
			}
		}
		logger.debug(this.getClass() + "getBookList() is ended");
		return bookList;
	}

	/**
	 * This method deletes an employee from database.
	 *
	 * @param bok the bok
	 */
	public void deleteBook(Book bok) {
		logger.debug(this.getClass() + "deleteBook(Book bok) is being invoked");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(bok);
			transaction.commit();
			logger.info(this.getClass() + ">>>" + bok.getTitle() + "<<<");
		} catch (Exception ex) {
			logger.error(this.getClass() + "deleteBook(Book bok): An error occurred while deleting a book", ex);
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (Exception ex) {
			}
		}
		logger.debug(this.getClass() + "deleteBook(Book bok) is ended");
	}

	/**
	 * This method used to save book in database.
	 *
	 * @param book the book
	 */
	public void saveBook(Book book) {
		logger.debug(this.getClass() + "saveBook(Book book) is being invoked");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();
		} catch (Exception ex) {
			logger.error(this.getClass() + "saveBook(Book book): An error occurred while saving book", ex);
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (Exception ex) {
			}
		}
		logger.debug(this.getClass() + "saveBook(Book book) is ended");
	}

}