import java.util.Collection;
import java.util.Iterator;

import database.AllUsersData;
import database.UserVO;


public class ManageUsers {

	int searchResult = 0;
	
	public ManageUsers() {
		// ���� ��� �� �� �����ֱ�
		System.out.println("=======================================================================ȸ�����=======================================================================");
		UserVO.printUsers();
		// �۾� ����
		selectWork();
	}

// �۾� ���� (�˻�, ����, �߰�, ����, �α׾ƿ�)
	public void selectWork() {

		String work = LoginMenu.inputData("������ �۾��� �����ϼ���[1.�˻� 2.�߰� 3.���� 4.���� 5.�۾����� 6.�α׾ƿ�]");
		
		switch (work) {
			case "1":
				System.out.println("����� �˻��� �����մϴ�.");
				searchUser();
				selectWork();
			case "2":
				System.out.println("����ڸ� �߰��մϴ�.");
				addUser();
				selectWork();
			case "3":
				System.out.println("����� ������ �����մϴ�.");
				System.out.println("�˻��� ���� ������ ����� ������ �ּ���.");
				UserVO willBeUpdated = updateUser();
				// ���� ��� ���
				System.out.println("=======================================================================�����Ϸ�=======================================================================");
				System.out.printf("  ȸ����ȣ\t      �̸�    \t         �ּ�        \t    ����ó    \t      ID\t  ��й�ȣ\t\t �̸���\t\t\t  ����\n");
				System.out.println("======================================================================================================================================================");
				System.out.printf("%s\t %7s\t %10s\t\t %13s \t %10s\t %10s\t %20s\t\t %s\n", willBeUpdated.getUserNo(), willBeUpdated.getUserName(), willBeUpdated.getAddress(), willBeUpdated.getContact(), willBeUpdated.getUserID(), willBeUpdated.getPassword(), willBeUpdated.geteMail(), willBeUpdated.isPermitted() ? "Admin" : "Normal");
				System.out.println("======================================================================================================================================================");
				System.out.println();
				selectWork();
			case "4":
				System.out.println("����ڸ� �����մϴ�.");
				System.out.println("�˻��� ���� ������ ����� ������ �ּ���.");
				removeUser();
				selectWork();
			case "5":
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
			case "6":
				System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
				finish();
			default:
				System.out.println("�ùٸ� ���� �Է��� �ּ���.");
				selectWork();
		}
		
	}
	// �˻�: � ������ �˻����� ����(ȸ����ȣ / �̸� / �ּ� / ��ȭ��ȣ / ID / ����)
	// �Ϻθ� �˻��ص� ���Ե� ���� ǥ��
	// data�� value�� key�� ã�Ƽ� key�� �ٽ� value ��ȯ
	public UserVO searchUser() {
		searchResult = 0;
		UserVO willBeReturned = new UserVO();
		String item = "";
		String itemNo = "";
		boolean canEscape = false;
		do {
			itemNo = LoginMenu.inputData("� �׸����� �˻��ұ��?[1.ȸ����ȣ  2.�̸�  3.�ּ�  4.����ó  5.���̵�  6.�̸���  7.�۾����]");
			switch(itemNo) {
			case "1":
				item = "ȸ����ȣ";
				canEscape = true;
				break;
			case "2":
				item = "�̸�";
				canEscape = true;
				break;
			case "3":
				item = "�ּ�";
				canEscape = true;
				break;
			case "4":
				item = "����ó";
				canEscape = true;
				break;
			case "5":
				item = "���̵�";
				canEscape = true;
				break;
			case "6":
				item = "�̸���";
				canEscape = true;
				break;
			case "7":
				selectWork();
			default:
				System.out.println("�ùٸ� ���� �Է��� �ּ���.");
			}
			if(canEscape) break;
		} while(true);
		System.out.printf("%s(��)�� �˻�\n", item);
		String keyword = LoginMenu.inputData("�˻�� �Է��� �ּ���");
		
		// �Էµ� �˻���� allUsers.values ��, getter�� ���� ���Ե� ���� �ִٸ� ���
		Collection<UserVO> valuesCollection = AllUsersData.allUsers.values();
		Iterator<UserVO> valuesIterator = valuesCollection.iterator();
		System.out.println("=======================================================================�˻����=======================================================================");
		System.out.printf("  ȸ����ȣ\t      �̸�    \t         �ּ�        \t    ����ó    \t      ID\t  ��й�ȣ\t\t �̸���\t\t\t  ����\n");
		System.out.println("======================================================================================================================================================");
		while(valuesIterator.hasNext()) {
			UserVO nextValue = valuesIterator.next();
			// �� UserVO�� ���ؼ� exising ���ڿ��� keyword ���ڿ��� �����ϴ��� üũ�Ͽ� �����ϸ� ��� 
			String existing = "";
			switch(itemNo) {
				case "1":
					existing = nextValue.getUserNo();
					break;
				case "2":
					existing = nextValue.getUserName();
					break;
				case "3":
					existing = nextValue.getAddress();
					break;
				case "4":
					existing = nextValue.getContact();
					break;
				case "5":
					existing = nextValue.getUserID();
					break;
				case "6":
					existing = nextValue.geteMail();
					break;
			}
			
			if(existing.contains(keyword)) {
				System.out.printf("%s\t %7s\t %10s\t\t %13s \t %10s\t %10s\t %20s\t\t %s\n", nextValue.getUserNo(), nextValue.getUserName(), nextValue.getAddress(), nextValue.getContact(), nextValue.getUserID(), nextValue.getPassword(), nextValue.geteMail(), nextValue.isPermitted() ? "Admin" : "Normal");
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
	
	// �߰�: �߰�
	// �ڡڡڡڡ� key �ߺ� �˻�, ���ڼ� ���� �ڡڡڡڡ�
	public void addUser() {
		MakeAccount makeUserVO = new MakeAccount();
		makeUserVO.makeUserNo();
		makeUserVO.makeUserAccount();
		System.out.println("=======================================================================�߰��Ϸ�=======================================================================");
		UserVO.printUsers();
	}
	
	// ����: �̸�/�ּ�/����ó/��й�ȣ/����/���ٱ��� ����
	public UserVO updateUser() {
		// ������ ��� ����
		// searchUser�� �����ϵ� �� �� �����ؾ� �ϹǷ� updaiting�� ����
		UserVO willBeUpdated = searchUser();
		do {
			if (searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				System.out.println("����� �ٽ� ������ �ּ���.");
				willBeUpdated = searchUser();
			} else if (searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				System.out.println("����� �ٽ� ������ �ּ���.");
				willBeUpdated = searchUser();
			} else break;
		} while(true);
		System.out.printf("%s���� ������ �����մϴ�. ", willBeUpdated.getUserName());
		// ������ �׸� ����
		String itemNo = LoginMenu.inputData("������ �׸��� ������ �ּ���[1.�̸�  2.�ּ�  3.����ó  4.��й�ȣ  5.�̸���  6.����]");
		String updated = "";
		// ������ ����� value �߿��� �׸��� ��ġ�ϴ� ���� setter�� ����
		boolean canEscape = false;
		switch(itemNo) {
			case "1":
				do {
					updated = LoginMenu.inputData("������ �̸��� �Է��� �ּ���");
					System.out.printf("%s�� �̸��� �����մϴ�.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkNameLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setUserName(updated);
				break;
			case "2":
				do {
					updated = LoginMenu.inputData("������ �ּҸ� �Է��� �ּ���");
					System.out.printf("%s�� �ּҸ� �����մϴ�.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkAddressLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setAddress(updated);
				break;
			case "3":
				do {
					updated = LoginMenu.inputData("������ ����ó�� �Է��� �ּ���");
					System.out.printf("%s�� ����ó�� �����մϴ�.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkContactLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setContact(updated);
				break;
			case "4":
				do {
					updated = LoginMenu.inputData("������ ��й�ȣ�� �Է��� �ּ���");
					System.out.printf("%s�� ��й�ȣ�� �����մϴ�.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkPasswordLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setPassword(updated);
				break;
			case "5":
				do {
					updated = LoginMenu.inputData("������ �̸����� �Է��� �ּ���");
					System.out.printf("%s�� �̸��� ������ �����մϴ�.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkEmailLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.seteMail(updated);
				break;
			case "6":
				// getter�� ������ üũ
				boolean currentPermission = willBeUpdated.isPermitted();
				if(currentPermission) {
					updated = LoginMenu.inputData("������ ������ �����Ͻðڽ��ϱ�?[1.�� 2.�ƴϿ�]");
				} else {
					updated = LoginMenu.inputData("������ ������ �ο��Ͻðڽ��ϱ�?[1.�� 2.�ƴϿ�]");
				}
				// setter�� �� ���� ����
				if (updated.equals("1")) {
					System.out.printf("%s���� ������ ������ �ο��մϴ�.\n", willBeUpdated.getUserName());
					willBeUpdated.setPermitted(!currentPermission);
				} else if(updated.equals("2")) {
					System.out.println("������ ���� �ο��� ����մϴ�.");
				} else {
					System.out.println("�ùٸ� ���� �Է��� �ּ���.");
					System.out.println("������ �׸��� �ٽ� ������ �ּ���.");
				}
				break;
			default:
				System.out.println("�ùٸ� ���� �Է��� �ּ���.");
				System.out.println("������ ����� �ٽ� ������ �ּ���.");
				updateUser();
		}
		return willBeUpdated;
	}
	
	// ����: ����
	public void removeUser() {
		System.out.println("������ ����� ������ �ּ���");
		UserVO willBeRemoved = searchUser();
		do {
			if (searchResult > 1) {
				System.out.println("�� ���� �ϳ��� �׸� ������ �� �ֽ��ϴ�.");
				System.out.println("����� �ٽ� ������ �ּ���.");
				willBeRemoved = searchUser();
			} else if (searchResult < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
				System.out.println("����� �ٽ� ������ �ּ���.");
				willBeRemoved = searchUser();
			} else break;
		} while(true);
		String areYouSure = LoginMenu.inputData("������ �� ������� ������ �����Ͻðڽ��ϱ�?[1.��  2.�ƴϿ�]");
		if (areYouSure.equals("1")) {
			System.out.printf("%s�� ����� ������ �����Ǿ����ϴ�.\n", willBeRemoved.getUserName());
			AllUsersData.allUsers.remove(willBeRemoved.getUserID(), willBeRemoved);
		} else if(areYouSure.equals("2")) {
			System.out.printf("%s�� ������ ����մϴ�.\n", willBeRemoved.getUserName());
		} else {
			System.out.println("�߸��� ���� �ԷµǾ� ������ ����մϴ�.");
		}
		
		System.out.println("=======================================================================�����Ϸ�=======================================================================");
		UserVO.printUsers();
	}
	// ����: ���� ��������
	public void finish() {
		System.out.println("�α��� ȭ������ ���ư��ϴ�.");
		new LoginMenu();
	}
	
}
