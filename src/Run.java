import database.AllBooksData;
import database.AllUsersData;

// 메인 메소드를 시작하는 클래스
public class Run {

	public Run() {
	}
	
	public static void main(String args[]) {
		// 프로그램 시작시 처음 한 번만 기본 데이터 세팅
		AllBooksData.setAllBooks();
		AllUsersData.setAllUsers();
		// 이후 로그인 메뉴 실행
		new LoginMenu();
	} 

}
