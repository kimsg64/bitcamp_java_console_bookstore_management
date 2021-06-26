import java.util.Scanner;
// ����ڷκ��� �α��� or ȸ�������� ���ù޾� �����ϴ� Ŭ����

import database.AllUsersData;
import database.UserVO;
public class LoginMenu {

	static Scanner scan = new Scanner(System.in);
	
	// �� ���� boolean ����(index0: ��й�ȣ ��ġ ���� / index1: ������ ����)
	boolean permitted = false;
	
	
	// ���� �޼ҵ�� ������ �޼ҵ� ���� => ������ �޼ҵ�� �α��� ���� ����  
	public LoginMenu() {
		// �α��� �����ϱ�
		loginOrSignin();
	}



	// �α��� ���� �޼ҵ�
	public void loginOrSignin() {
		do {
			// �α��� Ȯ��
			String loginOrNot = inputData("�α����Ͻðڽ��ϱ�? [1.��  2.�ƴϿ�]");
			// �� ���ý� => �α��� �޼ҵ� ����
			if(loginOrNot.equals("1")) {
				login();
				// �����ϸ� ����� ������
				// ���������� Ȯ���ϰ� �������� ��� ������ ������, �ƴ� ��� ����� ������ �����ֱ�
				if(permitted) {
					// ���� ����: �� 35�� ���� 10�� ��35�� > 150
					System.out.println("======================================================================================================================================================");
					System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�admin mode�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
					System.out.println("======================================================================================================================================================");
					// ������ �۾� ����
					do { // ������ ManageUsers�� HandleBooks�� �� ���̹Ƿ� break�� �ʿ����.
						String task = inputData("������ �۾��� �����ϼ���[1.ȸ������  2.��������]");
						switch (task) {
						// ȸ������
						case "1":
							System.out.println("ȸ�������� �����մϴ�.");
							// ���� ���� Ŭ������ �޼ҵ� ȣ��
							new ManageUsers();

							// ��������
						case "2":
							System.out.println("���������� �����մϴ�.");
							// ���⵵ ���� ��� ����ϰ� ���� ���� Ŭ������ �޼ҵ� ȣ��
							// �ڡڡ� �ٵ� ��� setAllBooks�� setAllUsers�� �ݺ������� ��Ÿ���� ������ �ȵǹǷ� ó�� ������ �� �� �� ���� ����ǵ��� �ؾ� �� �ڡڡ�
							new HandleBooks();
						default: System.out.println("�ùٸ� ���� �Է��� �ּ���");
						}
					} while(true);
				} else {
					System.out.println("�α��ο� �����Ͽ����ϴ�.");
					new BuyBooks();
				}
			
			} else if(loginOrNot.equals("2")) {
				
				do {
					// �ƴϿ� ���ý� => ȸ������ or ���α׷� ���� ����
					String signinOrEnd = inputData("���� �۾��� ������ �ּ���. [1.ȸ������  2.���α׷� ����]");
					
					// ȸ������ ���ý� => ȸ������
					if (signinOrEnd.equals("1")) {
						// ȸ������ ���
						System.out.println("ȸ�������� �����մϴ�.");
						MakeAccount makeUserVO = new MakeAccount();
						makeUserVO.makeUserNo();
						makeUserVO.makeUserAccount();
						break;
					} else if (signinOrEnd.equals("2")) {
						// ���α׷� ���� Ȥ�� �ٸ� �� ���ý� => ����
						System.out.println("���α׷��� �����մϴ�.");
						System.exit(0);
					} else {
						System.out.println("�ùٸ� ���� �Է��� �ּ���");
					}
				} while(true);
				
			} else {
				// �߸��� �� �Է½� �ǵ��ư���
				System.out.println("�ùٸ� ���� �Է��� �ּ���");
			}
		} while(true);
	}
	
	
	
	// ������ �Է¹޴� �޼ҵ�
	public static String inputData(String message) {
		System.out.printf("%s: ", message);
		return scan.nextLine();
	}
	
	
	
	// �α��� �޼ҵ�
	public boolean login() {
		// ����ڷκ��� ID�� Password�� �Է¹ް� �޾ƿ� ���� VO���� ã��

		// �Է¹��� ID�� VO key���� ������ �����͸� ��������, �ش� VO ���� password�� accountedPassword�� �Ҵ��ϱ�
		String accountedPassword = "";
		do {
			try {
				String userId = inputData("ID");
				accountedPassword = (AllUsersData.allUsers.get(userId)).getPassword();
				String password = inputData("Password");
				// pw ��ġ�ϸ� true, �ƴϸ� false
				permitted = (AllUsersData.allUsers.get(userId)).isPermitted();
				UserVO vo = AllUsersData.allUsers.get(userId);
				if(password.equals(accountedPassword)) {
					break;					
				} else {
					System.out.println("�߸��� ��й�ȣ�Դϴ�.");
				}
			} catch (NullPointerException nullEx) {
				System.out.println("���Ե��� ���� ID�Դϴ�.");
			}
		} while(true);
		return permitted;
	}
	
	
	
}
