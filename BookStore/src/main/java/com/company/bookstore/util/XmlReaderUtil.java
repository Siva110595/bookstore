/* Copyright 2018-2019 by company.,
 *
 * Company Address.
 * All rights reserved.
 */
package com.company.bookstore.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;

/**
 * The Class XmlReaderUtil.
 */
public class XmlReaderUtil {
	private final static Logger logger = Logger.getLogger(XmlReaderUtil.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws SAXException the SAX exception
	 * @throws IOException  Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws SAXException, IOException {
		getBooks();

	}

	/**
	 * This method used to get the books from xml.
	 *
	 * @return the books
	 * @throws SAXException the SAX exception
	 * @throws IOException  Signals that an I/O exception has occurred.
	 */
	public static List<Book> getBooks() throws SAXException, IOException {
		logger.debug("XmlReaderUtil::getBooks() is being invoked");
		File file = new File(
				"C:\\Users\\Siva Anandhi\\eclipse-workspace\\BookStore\\src\\main\\resources\\bookstore.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		List<Book> books = null;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			books = readBooksFromXML(doc);
			System.out.println(books.size());
		} catch (ParserConfigurationException e) {
			logger.error("XmlReaderUtil:: getBooks():  An error occurred", e);
		}
		logger.debug("XmlReaderUtil::getBooks() is ended");
		return books;

	}

	/**
	 * This method used to get the books from XML.
	 *
	 * @param doc the doc
	 * @return the books from XML
	 */
	private static List<Book> readBooksFromXML(Document doc) {
		logger.debug("XmlReaderUtil::readBooksFromXML(Document doc) is being invoked");
		List<Book> bookList = new ArrayList<>();
		NodeList bookNodes = doc.getElementsByTagName("book");
		for (int i = 0; i < bookNodes.getLength(); i++) {
			Node bookNode = bookNodes.item(i);
			if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
				Element bookElement = (Element) bookNode;
				Book book = new Book();
				book.setCategory(bookElement.getAttribute("category"));
				book.setLanguage(
						bookElement.getElementsByTagName("title").item(0).getAttributes().item(0).getTextContent());
				book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
				book.setYear(Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent()));
				book.setPrice(Double.parseDouble(bookElement.getElementsByTagName("price").item(0).getTextContent()));
				Node authorNode = bookElement.getElementsByTagName("authors").item(0);
				NodeList authorNodeList = authorNode.getChildNodes();
				Set<Author> authors = new HashSet<>();
				for (int j = 0; j < authorNodeList.getLength(); j++) {
					String authorName = authorNodeList.item(j).getTextContent().trim();
					if (authorName != null && !authorName.trim().equals("")) {
						Author author = new Author();
						author.setAuthorName(authorName);
						author.setBook(book);
						authors.add(author);
					}

				}

				book.setAuthors(authors);
				bookList.add(book);

			}
		}
		logger.debug("XmlReaderUtil::readBooksFromXML(Document doc) is ended");
		return bookList;
	}

}