package com.assessment.bookinfoapplication.functional;

import static com.assessment.bookinfoapplication.testutils.TestUtils.businessTestFile;
import static com.assessment.bookinfoapplication.testutils.TestUtils.currentTest;
import static com.assessment.bookinfoapplication.testutils.TestUtils.testReport;
import static com.assessment.bookinfoapplication.testutils.TestUtils.yakshaAssert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bookinfoapplication.Book;
import com.bookinfoapplication.BookInfoProcessor;

public class FunctionalTests {

	private static final String TEST_INPUT_FILE_PATH = "./src/main/java/com/bookinfoapplication/books.txt";

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@BeforeEach
	public void beforeEach() {
		// Reset books.txt content before each test
		File inputFile = new File(TEST_INPUT_FILE_PATH);
		if (inputFile.exists()) {
			inputFile.delete();
		}
	}

	@Test
	public void testReadBookData() throws IOException {
		String input = "Book1,Author1,100\nBook2,Author2,200\nBook3,Author3,300\nexit";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		try {
			List<Book> books = BookInfoProcessor.readBookData(TEST_INPUT_FILE_PATH);
			yakshaAssert(currentTest(), books != null, businessTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testPerformDataOperations() throws IOException {
		String input = "Book1,Author1,100\nBook2,Author2,200\nBook3,Author3,300\nexit";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		try {
			BookInfoProcessor.performDataOperations(BookInfoProcessor.readBookData(TEST_INPUT_FILE_PATH));
			yakshaAssert(currentTest(), BookInfoProcessor.INPUT_FILE_PATH != "", businessTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testFindBookWithHighestPages() throws IOException {
		String input = "Book1,Author1,100\nBook2,Author2,200\nBook3,Author3,300\nexit";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		try {
			BookInfoProcessor bookInfoProcessor = new BookInfoProcessor();
			List<Book> books = bookInfoProcessor.readBookData(TEST_INPUT_FILE_PATH);
			bookInfoProcessor.performDataOperations(books);
			String authorWithHighestPages = bookInfoProcessor.findBookWithHighestPages(books);
			yakshaAssert(currentTest(), authorWithHighestPages.equals("Author3"), businessTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}
