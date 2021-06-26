package database;

import java.util.Collection;
import java.util.Iterator;

public class BookVO {

	private String title;		// 제목
	private String author;		// 저자
	private String publisher;	// 출판사
	private String genre;		// 분야
	private int stock;			// 재고
	private int price;			// 판매가
	
	public BookVO() {
	}
	
	public BookVO(String title, String author, String publisher, String genre, int stock, int price) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.stock = stock;
		this.price = price;
	}
	
	public static void printAllBooks() {
		Collection<BookVO> booksValue = AllBooksData.allBooks.values();
		Iterator<BookVO> booksIterator = booksValue.iterator();
		//                         (12)4(40)                                      4(21)                  6(15)             4(10)           4(4)     6(6) 
		System.out.printf("제목                                              저자                          출판사              장르               재고      판매가\n");
		System.out.println("======================================================================================================================================================");
		while(booksIterator.hasNext()) {
			BookVO oneBook = booksIterator.next();
			printOneBook(oneBook);
		}
		System.out.println("======================================================================================================================================================");
		System.out.println();
	}
	
	public static void printOneBook(BookVO oneBook) {
		String titlePattern = setFiftyLetters(checkKorean(oneBook.getTitle()));
		String authoerPattern = setThirtyLetters(checkKorean(oneBook.getAuthor()));
		String publisherPattern = setTwentyLetters(checkKorean(oneBook.getPublisher()));
		String genrePattern = setTwentyLetters(checkKorean(oneBook.getGenre()));
		String stockPattern = setTenInts(checkKorean(oneBook.getStock()+""));
		String pricePattern = setTenInts(checkKorean(oneBook.getPrice()+""));
		// 한글은 1s인데 콘솔에는 2칸으로 찍혀서 그렇다.
		System.out.printf(titlePattern, oneBook.getTitle());
		System.out.printf(authoerPattern, oneBook.getAuthor());
		System.out.printf(publisherPattern, oneBook.getPublisher());
		System.out.printf(genrePattern, oneBook.getGenre());
		System.out.printf(stockPattern, oneBook.getStock());
		System.out.printf(pricePattern, oneBook.getPrice());
		System.out.println();
	}

	public static int checkKorean(String korean) {
		char[] charArray = korean.toCharArray();
		int realLength = charArray.length;
		for(int i=0; i<charArray.length; i++) {
			if (charArray[i] > 127) {
				realLength++;
			}
		}
		return realLength;
	}
	
	public static String setFiftyLetters(int realLength) {
		int space = 50 - realLength;
		String spaceStr = "";
		for (int i=0; i<space; i++) {
			spaceStr += " ";
		}
		String pattern = "%s" + spaceStr;
		return pattern;
	}
	
	public static String setThirtyLetters(int realLength) {
		int space = 30 - realLength;
		String spaceStr = "";
		for (int i=0; i<space; i++) {
			spaceStr += " ";
		}
		String pattern = "%s" + spaceStr;
		return pattern;
	}
	
	public static String setTwentyLetters(int realLength) {
		int space = 20 - realLength;
		String spaceStr = "";
		for (int i=0; i<space; i++) {
			spaceStr += " ";
		}
		String pattern = "%s" + spaceStr;
		return pattern;
	}
	
	public static String setTenInts(int realLength) {
		int space = 10 - realLength;
		String spaceStr = "";
		for (int i=0; i<space; i++) {
			spaceStr += " ";
		}
		String pattern = "%d" + spaceStr;
		return pattern;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
