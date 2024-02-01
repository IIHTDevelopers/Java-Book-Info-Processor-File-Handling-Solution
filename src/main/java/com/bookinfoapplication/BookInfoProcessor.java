package com.bookinfoapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BookInfoProcessor {

	public static final String INPUT_FILE_PATH = "./src/main/java/com/bookinfoapplication/books.txt";

	public static void main(String[] args) {
		try {
			List<Book> books = readBookData(INPUT_FILE_PATH);
			performDataOperations(books);
			System.out.println(findBookWithHighestPages(books));
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static List<Book> readBookData(String filePath) throws IOException {
		List<Book> books = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				System.out.println("Enter book details (title, author, pages) or 'exit' to finish:");
				String input = reader.readLine().trim();
				if (input.equalsIgnoreCase("exit")) {
					break;
				}

				String[] data = input.split(",");
				if (data.length == 3) {
					String title = data[0].trim();
					String author = data[1].trim();
					int pages = Integer.parseInt(data[2].trim());
					books.add(new Book(title, author, pages));
				} else {
					System.out.println("Invalid input. Please provide title, author, and pages separated by commas.");
				}
			}
		}
		return books;
	}

	public static void performDataOperations(List<Book> books) {
		for (Book book : books) {
			int newPages = book.getPages() * 2;
			book.setPages(newPages);
		}
	}

	public static String findBookWithHighestPages(List<Book> books) {
		if (!books.isEmpty()) {
			Book bookWithHighestPages = books.get(0);
			for (Book book : books) {
				if (book.getPages() > bookWithHighestPages.getPages()) {
					bookWithHighestPages = book;
				}
			}
			return bookWithHighestPages.getAuthor();
		} else {
			return "";
		}
	}
}
