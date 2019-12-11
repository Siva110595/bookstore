/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.company.bookstore.model.Book;
import com.company.bookstore.service.BookStoreService;

/**
 * The Class BookStoreController.
 */
@ManagedBean()
@SessionScoped
public class BookStoreController implements Serializable {

	private final static Logger logger = Logger.getLogger(BookStoreController.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The source. */
	private String source;

	/** The book store service. */
	@ManagedProperty(value = "#{bookStoreService}")
	private BookStoreService bookStoreService;

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source instance
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Sets the book store service instance.
	 *
	 * @param bookStoreService the new book store service
	 */
	public void setBookStoreService(BookStoreService bookStoreService) {
		this.bookStoreService = bookStoreService;
	}

	/**
	 * This method used to load books.
	 *
	 * @return the string
	 */
	public String loadBooks() {
		logger.debug(this.getClass() + "loadBooks() is being invoked");
		String message = "";
		try {
			if (bookStoreService.storeBooks("XML") == true) {
				message = "Books load Successfully";
			} else {
				message = "An error occurred while loading books";
			}
		} catch (SAXException | IOException ex) {
			logger.error(this.getClass() + "loadBooks(): An error occurred while loading books", ex);
		}
		logger.debug(this.getClass() + "loadBooks() is ended");
		return message;
	}

	/**
	 * This method used to get the book list.
	 *
	 * @return the book list
	 */
	public List<Book> getBookList() {
		logger.debug(this.getClass() + "getBookList() is being invoked");
		List<Book> bookList = bookStoreService.getBookList();
		logger.debug(this.getClass() + "getBookList() is ended");
		return bookList;
	}

	/**
	 * This method delete a book.
	 *
	 * @param book the book
	 */
	public void deleteBook(Book book) {
		logger.debug(this.getClass() + "deleteBook(Book book) is being invoked");
		bookStoreService.deleteBook(book);
		logger.debug(this.getClass() + "deleteBook(Book book) is ended");
	}

	/**
	 * This method used to enable the edit action.
	 *
	 * @param book the book
	 */
	public void editAction(Book book) {
		logger.debug(this.getClass() + "editAction(Book book) is being invoked");
		book.setEditable(true);
		logger.debug(this.getClass() + "editAction(Book book) is ended");
	}

	/**
	 * This method used to save book.
	 *
	 * @param book the book
	 * @return the string
	 */
	public String saveBook(Book book) {
		logger.debug(this.getClass() + "saveBook(Book book) is being invoked");
		book.setEditable(false);
		bookStoreService.saveBook(book);
		logger.debug(this.getClass() + "saveBook(Book book) is ended");
		return "success";

	}

}
