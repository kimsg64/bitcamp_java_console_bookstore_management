import java.util.Collection;
import java.util.Iterator;

import database.AllBooksData;
import database.BookVO;

public class HandleBooks {

	int searchResult = 0;
	
	public HandleBooks() {
		System.out.println("=======================================================================도서목록=======================================================================");
		BookVO.printAllBooks();
		selectWork();
	}

	// 업무 선택
	public void selectWork() {

		String work = LoginMenu.inputData("작업을 선택하세요[1.검색  2.추가  3.수정  4.제거  5.재고관리  6.가격관리  7.작업변경  8.로그아웃]");
		switch (work) {
			case "1":
				System.out.println("도서 검색을 시작합니다.");
				searchBook();
				selectWork();
			case "2":
				System.out.println("도서를 추가합니다.");
				addBook();
				selectWork();
			case "3":
				System.out.println("도서 정보를 수정합니다.");
				System.out.println("검색을 통해 수정할 대상을 선택해 주세요.");
				updateBook();
				selectWork();
			case "4":
				System.out.println("도서를 제거합니다.");
				System.out.println("검색을 통해 제거할 대상을 선택해 주세요.");
				removeBook();
				selectWork();
			case "5":
				System.out.println("재고를 확인합니다.");
				System.out.println("검색을 통해 재고를 확인할 대상을 선택해 주세요.");
				checkStock();
				selectWork();
			case "6":
				System.out.println("가격을 변경합니다.");
				System.out.println("검색을 통해 가격을 변경할 대상을 선택해 주세요.");
				changePrice();
				selectWork();
			case "7":
				String task = LoginMenu.inputData("작업을 선택하세요[1.회원관리  2.도서관리]");
				switch (task) {
					case "1":
						System.out.println("회원관리를 시작합니다.");
						new ManageUsers();
						break;
					case "2":
						System.out.println("도서관리를 시작합니다.");
						new HandleBooks();
						break;
				}
			case "8":
				finish();
				break;
			default:
				System.out.println("올바른 값을 입력해 주세요");
				selectWork();
		}
	}
	
		// 책 검색
		public BookVO searchBook() {
			searchResult = 0;
			BookVO willBeReturned = new BookVO();
			String item = "";
			String itemNo = "";
			boolean canEscape = false;
			do {
				itemNo = LoginMenu.inputData("어떤 항목으로 검색할까요?[1.제목  2.저자  3.출판사  4.장르  5.작업취소]");
				switch(itemNo) {
				case "1":
					item = "제목";
					canEscape = true;
					break;
				case "2":
					item = "저자";
					canEscape = true;
					break;
				case "3":
					item = "출판사";
					canEscape = true;
					break;
				case "4":
					item = "장르";
					canEscape = true;
					break;
				case "5":
					System.out.println("작업을 취소합니다.");
					selectWork();
				default:
					System.out.println("올바른 값을 입력해 주세요");
				}
				if (canEscape) break;
			} while(true);
			
			System.out.printf("%s(으)로 검색\n", item);
			String keyword = LoginMenu.inputData("검색어를 입력해 주세요");
			// 입력된 검색어는 allUsers.values 중, getter를 통해 포함된 값이 있다면 출력
			Collection<BookVO> valuesCollection = AllBooksData.allBooks.values();
			Iterator<BookVO> valuesIterator = valuesCollection.iterator();
			
			System.out.println("=======================================================================검색결과=======================================================================");
			System.out.printf("제목                                              저자                          출판사              장르               재고      판매가\n");
			System.out.println("======================================================================================================================================================");
			while(valuesIterator.hasNext()) {
				BookVO nextValue = valuesIterator.next();
				// 각 UserVO에 대해서 exising 문자열이 keyword 문자열을 포함하는지 체크하여 포함하면 출력 
				String existing = "";
				switch(itemNo) {
					case "1":
						existing = nextValue.getTitle();
						break;
					case "2":
						existing = nextValue.getAuthor();
						break;
					case "3":
						existing = nextValue.getPublisher();
						break;
					case "4":
						existing = nextValue.getGenre();
						break;
				}
				
				if(existing.contains(keyword)) {
					BookVO.printOneBook(nextValue);
					searchResult++;
					willBeReturned = nextValue;
				}
			}
			System.out.println();
			System.out.printf("검색결과 %d건\n", searchResult);
			System.out.println("======================================================================================================================================================");
			System.out.println();
			return willBeReturned;
	}
		public void addBook() {
			String title = LoginMenu.inputData("추가할 책의 제목을 입력해 주세요");
			String author = LoginMenu.inputData("저자명을 입력해 주세요");
			String publisher = LoginMenu.inputData("출판사를 입력해 주세요");
			String genre = LoginMenu.inputData("장르를 입력해 주세요");
			int stock = Integer.parseInt(LoginMenu.inputData("재고를 입력해 주세요"));
			int price = Integer.parseInt(LoginMenu.inputData("가격을 입력해 주세요"));
			AllBooksData.allBooks.put(title, new BookVO(title, author, publisher, genre, stock, price));
			System.out.println("=======================================================================추가완료=======================================================================");
			BookVO.printAllBooks();
		}
		
		public void updateBook() {
			BookVO willBeUpdated = searchBook();
			if (searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 수정할 수 있습니다.");
				willBeUpdated = searchBook();
			} else if (searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				willBeUpdated = searchBook();
			}
			System.out.printf("%s의 정보를 수정합니다.\n", willBeUpdated.getTitle());
			// 수정할 항목 선택
			String itemNo = LoginMenu.inputData("수정할 항목을 선택해 주세요[1.제목  2.저자  3.출판사  4.장르  5.작업취소]");
			String updated = "";
			// 수정할 대상의 value 중에서 항목이 일치하는 것을 setter로 수정
			switch(itemNo) {
				case "1":
					updated = LoginMenu.inputData("수정할 제목을 입력해 주세요.");
					System.out.printf("%s의 제목을 수정합니다.\n", willBeUpdated.getTitle());
					willBeUpdated.setTitle(updated);;
					break;
				case "2":
					updated = LoginMenu.inputData("수정할 저자명을 입력해 주세요.");
					System.out.printf("%s의 저자명을 수정합니다.\n", willBeUpdated.getTitle());
					willBeUpdated.setAuthor(updated);
					break;
				case "3":
					updated = LoginMenu.inputData("수정할 출판사를 입력해 주세요.");
					System.out.printf("%s의 출판사를 수정합니다.\n", willBeUpdated.getTitle());
					willBeUpdated.setPublisher(updated);;
					break;
				case "4":
					updated = LoginMenu.inputData("수정할 장르를 입력해 주세요.");
					System.out.printf("%s의 장르를 수정합니다.\n", willBeUpdated.getTitle());
					willBeUpdated.setGenre(updated);
					break;
				case "5":
					System.out.println("작업을 취소합니다.");
					selectWork();
				default:
					System.out.println("올바른 값을 입력해 주세요.");
					System.out.println("수정할 항목을 다시 선택해 주세요.");
					updateBook();
			}
			// 수정 결과 출력
			System.out.println("=======================================================================수정완료=======================================================================");
			System.out.printf("제목                                              저자                          출판사              장르               재고      판매가\n");
			System.out.println("======================================================================================================================================================");
			BookVO.printOneBook(willBeUpdated);
			System.out.println("======================================================================================================================================================");
			System.out.println();
		}
		
		public void removeBook() {
			System.out.println("삭제할 대상을 선택해 주세요");
			BookVO willBeRemoved = searchBook();
			if (searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 삭제할 수 있습니다.");
				willBeRemoved = searchBook();
			} else if (searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				willBeRemoved = searchBook();
			}
			
			String areYouSure = LoginMenu.inputData("정말로 위 도서의 정보를 삭제하시겠습니까?[1.예  2.아니오]");
			if (areYouSure.equals("1")) {
				System.out.printf("%s의 도서 정보가 삭제되었습니다.\n", willBeRemoved);
				AllBooksData.allBooks.remove(willBeRemoved.getTitle(), willBeRemoved);
			} else if(areYouSure.equals("2")) {
				System.out.printf("%s의 삭제를 취소합니다.\n", willBeRemoved);
			} else {
				System.out.println("잘못된 값이 입력되어 삭제를 취소합니다.");
			}
			
			System.out.println("=======================================================================삭제완료=======================================================================");
			BookVO.printAllBooks();
		}
		
		public void checkStock() {
			System.out.println("재고관리 대상을 선택해 주세요");
			BookVO willBeHandled = searchBook();
			if (searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 관리할 수 있습니다.");
				willBeHandled = searchBook();
			} else if (searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				willBeHandled = searchBook();
			}
			System.out.printf("%s의 현재 재고: %d\n", willBeHandled.getTitle(), willBeHandled.getStock());
			
			int calculated = 0;
			
			do {
				try {
					calculated = Integer.parseInt(LoginMenu.inputData("추가할 수량을 입력해 주세요. 감소시키는 경우에는 음수를 입력해 주세요"));
					if (willBeHandled.getStock() + calculated > 0) {
						break;						
					} else {
						System.out.println("재고를 0 이상의 정수로 다시 설정해 주세요.");
					}
				} catch(Exception ex) {
					System.out.println("숫자만 입력할 수 있습니다.");
				}
			} while(true);

			willBeHandled.setStock(willBeHandled.getStock() + calculated);
			System.out.printf("%s의 재고가 %d로 변경되었습니다.\n", willBeHandled.getTitle(), willBeHandled.getStock());
			System.out.println("======================================================================================================================================================");
			BookVO.printAllBooks();
		}
		
		public void changePrice() {
			System.out.println("가격관리 대상을 선택해 주세요");
			BookVO willBeHandled = searchBook();
			if (searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 관리할 수 있습니다.");
				willBeHandled = searchBook();
			} else if (searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				willBeHandled = searchBook();
			}
			System.out.printf("%s의 현재 가격: %d원\n", willBeHandled.getTitle(), willBeHandled.getPrice());
			
			int calculated = 0;
			
			do {
				try {
					calculated = Integer.parseInt(LoginMenu.inputData("추가할 금액을 입력해 주세요. 감소시키는 경우에는 음수를 입력해 주세요"));
					if (willBeHandled.getPrice() + calculated > 0) {
						break;						
					} else {
						System.out.println("가격을 0 이상의 정수로 다시 설정해 주세요.");
					}
				} catch(Exception ex) {
					System.out.println("숫자만 입력할 수 있습니다.");
				}
			} while(true);
			
			willBeHandled.setPrice(willBeHandled.getPrice() + calculated);
			System.out.printf("%s의 가격이 %d원으로 변경되었습니다.\n", willBeHandled.getTitle(), willBeHandled.getPrice());
			System.out.println("======================================================================================================================================================");
			BookVO.printAllBooks();
		}
		
		public void finish() {
			System.out.println("로그인 화면으로 돌아갑니다.");
			new LoginMenu();
		}
		
}
