import database.BookVO;

public class BuyBooks extends HandleBooks{

	public BuyBooks() {
		System.out.println("=======================================================================�������=======================================================================");
		BookVO.printAllBooks();
		selectWork();
	}

	// ���� ����
	public void selectWork() {
		String work = LoginMenu.inputData("�۾��� �����ϼ���[1.�����˻�  2.��������  3.�α׾ƿ�]");
		
		switch (work) {
			case "1":
				System.out.println("���� �˻��� �����մϴ�.");
				searchBook();
				selectWork();
			case "2":
				System.out.println("������ �����մϴ�.");
				System.out.println("�˻��� ���� ������ ����� ������ �ּ���.");
				buyBook();
				System.out.println("=======================================================================�������=======================================================================");
				BookVO.printAllBooks();
				selectWork();
			case "3":
				finish();
			default:
				System.out.println("�ùٸ� ���� �Է��� �ּ���.");
				selectWork();
		}
	}
		
	public void buyBook() {
		BookVO willBeSold = searchBook();
		
		do {
			if (super.searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				System.out.println("����� �ٽ� ������ �ּ���.");
				willBeSold = searchBook();
			} else if (super.searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				System.out.println("����� �ٽ� ������ �ּ���.");
				willBeSold = searchBook();
			} else break;
		} while(true);
		
		String title = willBeSold.getTitle();
		if (willBeSold.getStock() > 0) {
			int quantity = 0;
			do {
				try {
					quantity = Integer.parseInt(LoginMenu.inputData("�� �� �����Ͻðڽ��ϱ�?"));
					if (quantity > willBeSold.getStock()) {
						System.out.println("��� �����Ͽ� ������ �� �����ϴ�. ������ Ȯ���� �� �ٽ� �Է��� �ּ���.");
					} else if (quantity < 0) {
						System.out.println("������ 0�� �̻� ������ �� �ֽ��ϴ�. ������ Ȯ���� �� �ٽ� �Է��� �ּ���.");
					} else break;
				} catch(NumberFormatException nfException) {
					System.out.println("������ �Է��� �ּ���.");
				}
			} while(true);
			System.out.printf("%s��/�� %d�� �����մϴ�. ������ %d���Դϴ�. ", title, quantity, quantity * willBeSold.getPrice());
			String areUSure = LoginMenu.inputData("������ �����Ͻðڽ��ϱ�?[1.��  2.�ƴϿ�]");
			if(areUSure.equals("1")) {
				willBeSold.setStock(willBeSold.getStock() - quantity);
				System.out.println("=======================================================================���ԿϷ�=======================================================================");
				System.out.printf("%s�� %d�� �����߽��ϴ�. \n", title, quantity);
				System.out.println("======================================================================================================================================================");
				System.out.println();
			}
		}
		else {
			System.out.println("��� �����Ͽ� ������ �� �����ϴ�.");
			selectWork();
		}
		
	}
}
