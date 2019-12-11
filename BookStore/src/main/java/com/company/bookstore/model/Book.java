/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The Class Book.
 */
@Entity
@Table(name = "books")
public class Book implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The category. */
	private String category;

	/** The language. */
	private String language;

	/** The title. */
	private String title;

	/** The year. */
	private Integer year;

	/** The price. */
	private Double price;

	/** The editable. */
	@Transient
	public boolean editable;

	/**
	 * Checks if is editable.
	 *
	 * @return true, if is editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Instantiates a new book.
	 *
	 * @param id       the id
	 * @param category the category
	 * @param language the language
	 * @param title    the title
	 * @param year     the year
	 * @param price    the price
	 * @param authors  the authors
	 */
	public Book(Integer id, String category, String language, String title, Integer year, Double price,
			Set<Author> authors) {
		super();
		this.id = id;
		this.category = category;
		this.language = language;
		this.title = title;
		this.year = year;
		this.price = price;
		this.authors = authors;
	}

	/** The authors. */
	@OneToMany(mappedBy = "book")
	private Set<Author> authors;

	/**
	 * Instantiates a new book.
	 */
	public Book() {
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets the authors.
	 *
	 * @return the authors
	 */
	public Set<Author> getAuthors() {
		return authors;
	}

	/**
	 * Sets the authors.
	 *
	 * @param authors the new authors
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", category=" + category + ", language=" + language + ", title=" + title + ", year="
				+ year + ", price=" + price + ", authors=" + authors + "]";
	}

}
