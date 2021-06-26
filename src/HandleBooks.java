import java.util.Collection;
import java.util.Iterator;

import database.AllBooksData;
import database.BookVO;

public class HandleBooks {

	int searchResult = 0;
	
	public HandleBooks() {
		System.out.println("=======================================================================�������=======================================================================");
		BookVO.printAllBooks();
		selectWork();
	}

	// ���� ����
	public void selectWork() {

		String work = LoginMenu.inputData("�۾��� �����ϼ���[1.�˻�  2.�߰�  3.����  4.����  5.������  6.���ݰ���  7.�۾�����  8.�α׾ƿ�]");
		switch (work) {
			case "1":
				System.out.println("���� �˻��� �����մϴ�.");
				searchBook();
				selectWork();
			case "2":
				System.out.println("������ �߰��մϴ�.");
				addBook();
				selectWork();
			case "3":
				System.out.println("���� ������ �����մϴ�.");
				System.out.println("�˻��� ���� ������ ����� ������ �ּ���.");
				updateBook();
				selectWork();
			case "4":
				System.out.println("������ �����մϴ�.");
				System.out.println("�˻��� ���� ������ ����� ������ �ּ���.");
				removeBook();
				selectWork();
			case "5":
				System.out.println("��� Ȯ���մϴ�.");
				System.out.println("�˻��� ���� ��� Ȯ���� ����� ������ �ּ���.");
				checkStock();
				selectWork();
			case "6":
				System.out.println("������ �����մϴ�.");
				System.out.println("�˻��� ���� ������ ������ ����� ������ �ּ���.");
				changePrice();
				selectWork();
			case "7":
				String task = LoginMenu.inputData("�۾��� �����ϼ���[1.ȸ������  2.��������]");
				switch (task) {
					case "1":
						System.out.println("ȸ�������� �����մϴ�.");
						new ManageUsers();
						break;
					case "2":
						System.out.println("���������� �����մϴ�.");
						new HandleBooks();
						break;
				}
			case "8":
				finish();
				break;
			default:
				System.out.println("�ùٸ� ���� �Է��� �ּ���");
				selectWork();
		}
	}
	
		// å �˻�
		public BookVO searchBook() {
			searchResult = 0;
			BookVO willBeReturned = new BookVO();
			String item = "";
			String itemNo = "";
			boolean canEscape = false;
			do {
				itemNo = LoginMenu.inputData("� �׸����� �˻��ұ��?[1.����  2.����  3.���ǻ�  4.�帣  5.�۾����]");
				switch(itemNo) {
				case "1":
					item = "����";
					canEscape = true;
					break;
				case "2":
					item = "����";
					canEscape = true;
					break;
				case "3":
					item = "���ǻ�";
					canEscape = true;
					break;
				case "4":
					item = "�帣";
					canEscape = true;
					break;
				case "5":
					System.out.println("�۾��� ����մϴ�.");
					selectWork();
				default:
					System.out.println("�ùٸ� ���� �Է��� �ּ���");
				}
				if (canEscape) break;
			} while(true);
			
			System.out.printf("%s(��)�� �˻�\n", item);
			String keyword = LoginMenu.inputData("�˻�� �Է��� �ּ���");
			// �Էµ� �˻���� allUsers.values ��, getter�� ���� ���Ե� ���� �ִٸ� ���
			Collection<BookVO> valuesCollection = AllBooksData.allBooks.values();
			Iterator<BookVO> valuesIterator = valuesCollection.iterator();
			
			System.out.println("=======================================================================�˻����=======================================================================");
			System.out.printf("����                                              ����                          ���ǻ�              �帣               ���      �ǸŰ�\n");
			System.out.println("======================================================================================================================================================");
			while(valuesIterator.hasNext()) {
				BookVO nextValue = valuesIterator.next();
				// �� UserVO�� ���ؼ� exising ���ڿ��� keyword ���ڿ��� �����ϴ��� üũ�Ͽ� �����ϸ� ��� 
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
			System.out.printf("�˻���� %d��\n", searchResult);
			System.out.println("======================================================================================================================================================");
			System.out.println();
			return willBeReturned;
	}
		public void addBook() {
			String title = LoginMenu.inputData("�߰��� å�� ������ �Է��� �ּ���");
			String author = LoginMenu.inputData("���ڸ��� �Է��� �ּ���");
			String publisher = LoginMenu.inputData("���ǻ縦 �Է��� �ּ���");
			String genre = LoginMenu.inputData("�帣�� �Է��� �ּ���");
			int stock = Integer.parseInt(LoginMenu.inputData("��� �Է��� �ּ���"));
			int price = Integer.parseInt(LoginMenu.inputData("������ �Է��� �ּ���"));
			AllBooksData.allBooks.put(title, new BookVO(title, author, publisher, genre, stock, price));
			System.out.println("=======================================================================�߰��Ϸ�=======================================================================");
			BookVO.printAllBooks();
		}
		
		public void updateBook() {
			BookVO willBeUpdated = searchBook();
			if (searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				willBeUpdated = searchBook();
			} else if (searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				willBeUpdated = searchBook();
			}
			System.out.printf("%s�� ������ �����մϴ�.\n", willBeUpdated.getTitle());
			// ������ �׸� ����
			String itemNo = LoginMenu.inputData("������ �׸��� ������ �ּ���[1.����  2.����  3.���ǻ�  4.�帣  5.�۾����]");
			String updated = "";
			// ������ ����� value �߿��� �׸��� ��ġ�ϴ� ���� setter�� ����
			switch(itemNo) {
				case "1":
					updated = LoginMenu.inputData("������ ������ �Է��� �ּ���.");
					System.out.printf("%s�� ������ �����մϴ�.\n", willBeUpdated.getTitle());
					willBeUpdated.setTitle(updated);;
					break;
				case "2":
					updated = LoginMenu.inputData("������ ���ڸ��� �Է��� �ּ���.");
					System.out.printf("%s�� ���ڸ��� �����մϴ�.\n", willBeUpdated.getTitle());
					willBeUpdated.setAuthor(updated);
					break;
				case "3":
					updated = LoginMenu.inputData("������ ���ǻ縦 �Է��� �ּ���.");
					System.out.printf("%s�� ���ǻ縦 �����մϴ�.\n", willBeUpdated.getTitle());
					willBeUpdated.setPublisher(updated);;
					break;
				case "4":
					updated = LoginMenu.inputData("������ �帣�� �Է��� �ּ���.");
					System.out.printf("%s�� �帣�� �����մϴ�.\n", willBeUpdated.getTitle());
					willBeUpdated.setGenre(updated);
					break;
				case "5":
					System.out.println("�۾��� ����մϴ�.");
					selectWork();
				default:
					System.out.println("�ùٸ� ���� �Է��� �ּ���.");
					System.out.println("������ �׸��� �ٽ� ������ �ּ���.");
					updateBook();
			}
			// ���� ��� ���
			System.out.println("=======================================================================�����Ϸ�=======================================================================");
			System.out.printf("����                                              ����                          ���ǻ�              �帣               ���      �ǸŰ�\n");
			System.out.println("======================================================================================================================================================");
			BookVO.printOneBook(willBeUpdated);
			System.out.println("======================================================================================================================================================");
			System.out.println();
		}
		
		public void removeBook() {
			System.out.println("������ ����� ������ �ּ���");
			BookVO willBeRemoved = searchBook();
			if (searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				willBeRemoved = searchBook();
			} else if (searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				willBeRemoved = searchBook();
			}
			
			String areYouSure = LoginMenu.inputData("������ �� ������ ������ �����Ͻðڽ��ϱ�?[1.��  2.�ƴϿ�]");
			if (areYouSure.equals("1")) {
				System.out.printf("%s�� ���� ������ �����Ǿ����ϴ�.\n", willBeRemoved);
				AllBooksData.allBooks.remove(willBeRemoved.getTitle(), willBeRemoved);
			} else if(areYouSure.equals("2")) {
				System.out.printf("%s�� ������ ����մϴ�.\n", willBeRemoved);
			} else {
				System.out.println("�߸��� ���� �ԷµǾ� ������ ����մϴ�.");
			}
			
			System.out.println("=======================================================================�����Ϸ�=======================================================================");
			BookVO.printAllBooks();
		}
		
		public void checkStock() {
			System.out.println("������ ����� ������ �ּ���");
			BookVO willBeHandled = searchBook();
			if (searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				willBeHandled = searchBook();
			} else if (searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				willBeHandled = searchBook();
			}
			System.out.printf("%s�� ���� ���: %d\n", willBeHandled.getTitle(), willBeHandled.getStock());
			
			int calculated = 0;
			
			do {
				try {
					calculated = Integer.parseInt(LoginMenu.inputData("�߰��� ������ �Է��� �ּ���. ���ҽ�Ű�� ��쿡�� ������ �Է��� �ּ���"));
					if (willBeHandled.getStock() + calculated > 0) {
						break;						
					} else {
						System.out.println("��� 0 �̻��� ������ �ٽ� ������ �ּ���.");
					}
				} catch(Exception ex) {
					System.out.println("���ڸ� �Է��� �� �ֽ��ϴ�.");
				}
			} while(true);

			willBeHandled.setStock(willBeHandled.getStock() + calculated);
			System.out.printf("%s�� ��� %d�� ����Ǿ����ϴ�.\n", willBeHandled.getTitle(), willBeHandled.getStock());
			System.out.println("======================================================================================================================================================");
			BookVO.printAllBooks();
		}
		
		public void changePrice() {
			System.out.println("���ݰ��� ����� ������ �ּ���");
			BookVO willBeHandled = searchBook();
			if (searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				willBeHandled = searchBook();
			} else if (searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				willBeHandled = searchBook();
			}
			System.out.printf("%s�� ���� ����: %d��\n", willBeHandled.getTitle(), willBeHandled.getPrice());
			
			int calculated = 0;
			
			do {
				try {
					calculated = Integer.parseInt(LoginMenu.inputData("�߰��� �ݾ��� �Է��� �ּ���. ���ҽ�Ű�� ��쿡�� ������ �Է��� �ּ���"));
					if (willBeHandled.getPrice() + calculated > 0) {
						break;						
					} else {
						System.out.println("������ 0 �̻��� ������ �ٽ� ������ �ּ���.");
					}
				} catch(Exception ex) {
					System.out.println("���ڸ� �Է��� �� �ֽ��ϴ�.");
				}
			} while(true);
			
			willBeHandled.setPrice(willBeHandled.getPrice() + calculated);
			System.out.printf("%s�� ������ %d������ ����Ǿ����ϴ�.\n", willBeHandled.getTitle(), willBeHandled.getPrice());
			System.out.println("======================================================================================================================================================");
			BookVO.printAllBooks();
		}
		
		public void finish() {
			System.out.println("�α��� ȭ������ ���ư��ϴ�.");
			new LoginMenu();
		}
		
}
