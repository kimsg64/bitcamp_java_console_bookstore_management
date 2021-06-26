import java.util.Scanner;
// 사용자로부터 로그인 or 회원가입을 선택받아 진행하는 클래스

import database.AllUsersData;
import database.UserVO;
public class LoginMenu {

	static Scanner scan = new Scanner(System.in);
	
	// 두 개의 boolean 전달(index0: 비밀번호 일치 여부 / index1: 관리자 여부)
	boolean permitted = false;
	
	
	// 메인 메소드로 생성자 메소드 실행 => 생성자 메소드로 로그인 제어 시작  
	public LoginMenu() {
		// 로그인 제어하기
		loginOrSignin();
	}



	// 로그인 제어 메소드
	public void loginOrSignin() {
		do {
			// 로그인 확인
			String loginOrNot = inputData("로그인하시겠습니까? [1.예  2.아니오]");
			// 예 선택시 => 로그인 메소드 실행
			if(loginOrNot.equals("1")) {
				login();
				// 성공하면 여기로 내려옴
				// 관리자인지 확인하고 관리자일 경우 관리자 페이지, 아닐 경우 사용자 페이지 보여주기
				if(permitted) {
					// 가로 길이: 별 35개 영어 10개 별35개 > 150
					System.out.println("======================================================================================================================================================");
					System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★admin mode★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
					System.out.println("======================================================================================================================================================");
					// 수행할 작업 선택
					do { // 어차피 ManageUsers나 HandleBooks로 갈 것이므로 break는 필요없다.
						String task = inputData("수행할 작업을 선택하세요[1.회원관리  2.도서관리]");
						switch (task) {
						// 회원관리
						case "1":
							System.out.println("회원관리를 시작합니다.");
							// 유저 관리 클래스의 메소드 호출
							new ManageUsers();

							// 도서관리
						case "2":
							System.out.println("도서관리를 시작합니다.");
							// 여기도 도서 목록 출력하고 도서 관리 클래스의 메소드 호출
							// ★★★ 근데 사실 setAllBooks랑 setAllUsers가 반복적으로 나타나면 갱신이 안되므로 처음 시작할 때 딱 한 번만 실행되도록 해야 함 ★★★
							new HandleBooks();
						default: System.out.println("올바른 값을 입력해 주세요");
						}
					} while(true);
				} else {
					System.out.println("로그인에 성공하였습니다.");
					new BuyBooks();
				}
			
			} else if(loginOrNot.equals("2")) {
				
				do {
					// 아니오 선택시 => 회원가입 or 프로그램 종료 선택
					String signinOrEnd = inputData("다음 작업을 선택해 주세요. [1.회원가입  2.프로그램 종료]");
					
					// 회원가입 선택시 => 회원가입
					if (signinOrEnd.equals("1")) {
						// 회원가입 기능
						System.out.println("회원가입을 시작합니다.");
						MakeAccount makeUserVO = new MakeAccount();
						makeUserVO.makeUserNo();
						makeUserVO.makeUserAccount();
						break;
					} else if (signinOrEnd.equals("2")) {
						// 프로그램 종료 혹은 다른 값 선택시 => 종료
						System.out.println("프로그램을 종료합니다.");
						System.exit(0);
					} else {
						System.out.println("올바른 값을 입력해 주세요");
					}
				} while(true);
				
			} else {
				// 잘못된 값 입력시 되돌아가기
				System.out.println("올바른 값을 입력해 주세요");
			}
		} while(true);
	}
	
	
	
	// 데이터 입력받는 메소드
	public static String inputData(String message) {
		System.out.printf("%s: ", message);
		return scan.nextLine();
	}
	
	
	
	// 로그인 메소드
	public boolean login() {
		// 사용자로부터 ID와 Password를 입력받고 받아온 값을 VO에서 찾기

		// 입력받은 ID로 VO key값과 대조해 데이터를 가져오고, 해당 VO 안의 password를 accountedPassword에 할당하기
		String accountedPassword = "";
		do {
			try {
				String userId = inputData("ID");
				accountedPassword = (AllUsersData.allUsers.get(userId)).getPassword();
				String password = inputData("Password");
				// pw 일치하면 true, 아니면 false
				permitted = (AllUsersData.allUsers.get(userId)).isPermitted();
				UserVO vo = AllUsersData.allUsers.get(userId);
				if(password.equals(accountedPassword)) {
					break;					
				} else {
					System.out.println("잘못된 비밀번호입니다.");
				}
			} catch (NullPointerException nullEx) {
				System.out.println("가입되지 않은 ID입니다.");
			}
		} while(true);
		return permitted;
	}
	
	
	
}
