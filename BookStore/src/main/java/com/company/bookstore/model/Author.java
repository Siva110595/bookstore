/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Author.
 */
@Entity
@Table(name = "Authors")
public class Author implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The author name. */
	private String authorName;

	/**
	 * Instantiates a new author.
	 *
	 * @param id         the id
	 * @param authorName the author name
	 * @param book       the book
	 */
	public Author(Integer id, String authorName, Book book) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.book = book;
	}

	/** The book. */
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	/**
	 * Instantiates a new author.
	 */
	public Author() {

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the author name.
	 *
	 * @return the author name
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Sets the author name.
	 *
	 * @param authorName the new author name
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Gets the book.
	 *
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * Sets the book.
	 *
	 * @param book the new book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Author [id=" + id + ", authorName=" + authorName + ", book=" + book + "]";
	}

}
