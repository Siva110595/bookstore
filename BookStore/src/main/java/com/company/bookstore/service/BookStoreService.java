/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.company.bookstore.dao.BookStoreDao;
import com.company.bookstore.model.Book;
import com.company.bookstore.util.XmlReaderUtil;

/**
 * The Class BookStoreService.
 */
@ManagedBean
@SessionScoped
public class BookStoreService implements Serializable {

	private final static Logger logger = Logger.getLogger(BookStoreService.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The book store dao. */
	@ManagedProperty(value = "#{bookStoreDao}")
	private BookStoreDao bookStoreDao;

	/**
	 * Sets the book store dao instance.
	 *
	 * @param bookStoreDao the new book store dao
	 */
	public void setBookStoreDao(BookStoreDao bookStoreDao) {
		this.bookStoreDao = bookStoreDao;
	}

	/**
	 * This method used to store books.
	 *
	 * @param source the source
	 * @return true, if successful
	 * @throws SAXException the SAX exception
	 * @throws IOException  Signals that an I/O exception has occurred.
	 */
	public boolean storeBooks(String source) throws SAXException, IOException {
		logger.debug(this.getClass() + "storeBooks(String source) is being invoked");
		boolean isSuccess = false;
		if (source.equalsIgnoreCase("XML")) {
			isSuccess = bookStoreDao.storeBooks(XmlReaderUtil.getBooks());
		}
		logger.debug(this.getClass() + "storeBooks(String source) is ended");
		return isSuccess;
	}

	/**
	 * This method used to get book list.
	 *
	 * @return the book list
	 */
	public List<Book> getBookList() {
		logger.debug(this.getClass() + "getBookList() is being invoked");
		List<Book> bookList = bookStoreDao.getBookList();
		logger.debug(this.getClass() + "getBookList() is ended");
		return bookList;
	}

	/**
	 * This method deletes a book.
	 *
	 * @param bok the bok
	 */
	public void deleteBook(Book bok) {
		logger.debug(this.getClass() + "deleteBook(Book bok) is being invoked");
		bookStoreDao.deleteBook(bok);
		logger.debug(this.getClass() + "deleteBook(Book bok) is ended");
	}

	/**
	 * This method save book.
	 *
	 * @param book the book
	 */
	public void saveBook(Book book) {
		logger.debug(this.getClass() + "saveBook(Book book) is being invoked");
		bookStoreDao.saveBook(book);
		logger.debug(this.getClass() + "saveBook(Book book) is ended");
	}

}
