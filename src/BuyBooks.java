import database.BookVO;

public class BuyBooks extends HandleBooks{

	public BuyBooks() {
		System.out.println("=======================================================================도서목록=======================================================================");
		BookVO.printAllBooks();
		selectWork();
	}

	// 업무 선택
	public void selectWork() {
		String work = LoginMenu.inputData("작업을 선택하세요[1.도서검색  2.도서구입  3.로그아웃]");
		
		switch (work) {
			case "1":
				System.out.println("도서 검색을 시작합니다.");
				searchBook();
				selectWork();
			case "2":
				System.out.println("도서를 구입합니다.");
				System.out.println("검색을 통해 구입할 대상을 선택해 주세요.");
				buyBook();
				System.out.println("=======================================================================도서목록=======================================================================");
				BookVO.printAllBooks();
				selectWork();
			case "3":
				finish();
			default:
				System.out.println("올바른 값을 입력해 주세요.");
				selectWork();
		}
	}
		
	public void buyBook() {
		BookVO willBeSold = searchBook();
		
		do {
			if (super.searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 구입할 수 있습니다.");
				System.out.println("대상을 다시 선택해 주세요.");
				willBeSold = searchBook();
			} else if (super.searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				System.out.println("대상을 다시 선택해 주세요.");
				willBeSold = searchBook();
			} else break;
		} while(true);
		
		String title = willBeSold.getTitle();
		if (willBeSold.getStock() > 0) {
			int quantity = 0;
			do {
				try {
					quantity = Integer.parseInt(LoginMenu.inputData("몇 권 구입하시겠습니까?"));
					if (quantity > willBeSold.getStock()) {
						System.out.println("재고가 부족하여 구입할 수 없습니다. 수량을 확인한 뒤 다시 입력해 주세요.");
					} else if (quantity < 0) {
						System.out.println("도서는 0권 이상 구입할 수 있습니다. 수량을 확인한 뒤 다시 입력해 주세요.");
					} else break;
				} catch(NumberFormatException nfException) {
					System.out.println("정수를 입력해 주세요.");
				}
			} while(true);
			System.out.printf("%s을/를 %d권 구입합니다. 가격은 %d원입니다. ", title, quantity, quantity * willBeSold.getPrice());
			String areUSure = LoginMenu.inputData("정말로 구입하시겠습니까?[1.예  2.아니오]");
			if(areUSure.equals("1")) {
				willBeSold.setStock(willBeSold.getStock() - quantity);
				System.out.println("=======================================================================구입완료=======================================================================");
				System.out.printf("%s을 %d권 구입했습니다. \n", title, quantity);
				System.out.println("======================================================================================================================================================");
				System.out.println();
			}
		}
		else {
			System.out.println("재고가 부족하여 구입할 수 없습니다.");
			selectWork();
		}
		
	}
}
