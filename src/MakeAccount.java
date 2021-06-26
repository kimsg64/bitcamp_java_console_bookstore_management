import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import database.AllUsersData;
import database.UserVO;

public class MakeAccount {

	public MakeAccount() {
	}

	// ��¥ ������ userNo�� ����
	public String makeUserNo() {
		Calendar now = Calendar.getInstance();
		Date currentDate = now.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String userNo = format.format(currentDate);
		return userNo;
	}
	
	// ���ο� UserVO ��ü �����ؼ� allUsers�� ������Ʈ
	// userNo: �����ؼ� �ο�
	// userName, address, contact, userID, password, eMail: �Է¹��� ��� ���
	// permitted: �⺻ false �ο�(master���� ȸ������ �������� ���� ����)
	public void makeUserAccount() {
		// �ڡڡ� �Է� ���ڿ� ���� ���� �α� �ڡڡ�
		boolean canEscape = false;
		String name = "";
		String address = "";
		String contact = "";
		String id = "";
		String password = "";
		String email = "";
		
		do {
			name = LoginMenu.inputData("�̸��� �Է��ϼ���");
			canEscape = checkNameLetters(name);
			if (canEscape) break;
		} while(true);
			
		do {
			address = LoginMenu.inputData("�ּҸ� �Է��ϼ���");
			canEscape = checkAddressLetters(address);
			if (canEscape) break;
		} while(true);
		
		do {
			contact = LoginMenu.inputData("����ó�� �Է��ϼ���");
			canEscape = checkContactLetters(contact);

			if (canEscape) break;
		} while(true);
		
		do {
			do {
				// �Է��� userId�� allUsers�� Ű�ν� �����ϸ� �ߺ��̹Ƿ� ���ֱ�
				id = LoginMenu.inputData("���ο� ID�� �Է��ϼ���");
				canEscape = checkSameID(id);
				if (canEscape) break;	
			} while(true);
			
			canEscape = checkIdLetters(id);
			if (canEscape) break;
		} while(true);

		
		do {
			password = LoginMenu.inputData("��й�ȣ�� �Է��ϼ���");
			canEscape = checkPasswordLetters(password);

			if (canEscape) break;
		} while(true);
		
		do {
			do {
				// �Է��� eamil�� @�� �������� �ʰų� .�� �������� �ʰų� @�� . �ڿ� �ְų� �������� 2���� ���ϰų�... �� �ٽ� 
				email = LoginMenu.inputData("�̸��� �ּҸ� �Է��ϼ���");
				canEscape = checkEmail(email);
				if (canEscape) break;	
			} while(true);
			
			canEscape = checkEmailLetters(email);
			if (canEscape) break;
		} while(true);


		AllUsersData.allUsers.put(id, new UserVO(makeUserNo(), name, address, contact, id, password, email, false, false));
	}
	
	public static boolean checkNameLetters(String name) {
		if(name.length() > 6 || name.length() < 2) {
			System.out.println("�̸��� 2~6�ڱ����� �Է� �����մϴ�.");
			return false;
		} else return true;
	}
	
	public static boolean checkAddressLetters(String address) {
		if(address.length() > 4 || address.length() < 2) {
			System.out.println("�ּҴ� 2~4�ڱ����� �Է� �����մϴ�.");
			return false;
		} else return true;
	}
	
	public static boolean checkContactLetters(String contact) {
		if(contact.length() > 13 || contact.length() < 9) {
			System.out.println("����ó�� ������(-)�� �����Ͽ� 13�ڱ����� �Է� �����մϴ�.");
			return false;
		} else return true;
	}
	
	public static boolean checkIdLetters(String id) {
		if(id.length() > 11 || id.length() < 5) {
			System.out.println("ID�� 5~11�ڱ����� �Է� �����մϴ�.");
			return false;
		} else return true;
	}
	
	public static boolean checkPasswordLetters(String password) {
		if(password.length() > 11 || password.length() < 5) {
			System.out.println("��й�ȣ�� 5~11�ڱ����� �Է� �����մϴ�.");
			return false;
		} else return true;
	}
	
	public static boolean checkEmailLetters(String email) {
		if(email.length() > 20 || email.length() < 15) {
			System.out.println("�̸����� �������� �����Ͽ� 15~20�ڱ����� �Է� �����մϴ�.");
			return false;
		} else return true;
	}
	
	public static boolean checkSameID(String id) {
		Set<String> keys = AllUsersData.allUsers.keySet();
		Iterator<String> keysIterator = keys.iterator();
		while(keysIterator.hasNext()) {
			String oneKey = keysIterator.next();
			if(oneKey.equals(id)) {
				System.out.println("�̹� ��ϵ� ID�Դϴ�.");
				return false;
			}
		}
		return true;				
	}
	
	public static boolean checkEmail(String email) {
		int atmark = email.indexOf("@"); // indexOf�� ã�� ��Ұ� ������ -1�� ��ȯ�Ѵ�.
		int comma = email.indexOf(".");
		//  @�� �������� �ʰų� .�� �������� �ʰų� @�� . �ڿ� �ְų�    �������� 2���� ���ϰų� ... ���� �� ���� �� �ִµ� �������� �Ѵ�.
		if (atmark == -1 || comma == -1 || atmark > comma || comma - atmark <= 2) { // �߸��� ������ �Ÿ���(false ��ȯ)
			System.out.println("������ �ּҸ� ��Ȯ�� �Է��� �ּ���.");
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	
	
}