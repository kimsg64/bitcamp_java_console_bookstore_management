import database.AllBooksData;
import database.AllUsersData;

// ���� �޼ҵ带 �����ϴ� Ŭ����
public class Run {

	public Run() {
	}
	
	public static void main(String args[]) {
		// ���α׷� ���۽� ó�� �� ���� �⺻ ������ ����
		AllBooksData.setAllBooks();
		AllUsersData.setAllUsers();
		// ���� �α��� �޴� ����
		new LoginMenu();
	} 

}
