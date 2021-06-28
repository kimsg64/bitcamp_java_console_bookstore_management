import java.util.Collection;
import java.util.Iterator;

import database.AllUsersData;
import database.UserVO;


public class ManageUsers {

	int searchResult = 0;
	
	public ManageUsers() {
		// 최초 목록 한 번 보여주기
		System.out.println("=======================================================================회원명단=======================================================================");
		UserVO.printUsers();
		// 작업 선택
		selectWork();
	}

// 작업 선택 (검색, 수정, 추가, 제거, 로그아웃)
	public void selectWork() {

		String work = LoginMenu.inputData("수행할 작업을 선택하세요[1.검색 2.추가 3.수정 4.삭제 5.작업변경 6.로그아웃]");
		
		switch (work) {
			case "1":
				System.out.println("사용자 검색을 시작합니다.");
				searchUser();
				selectWork();
			case "2":
				System.out.println("사용자를 추가합니다.");
				addUser();
				selectWork();
			case "3":
				System.out.println("사용자 정보를 수정합니다.");
				System.out.println("검색을 통해 수정할 대상을 선택해 주세요.");
				UserVO willBeUpdated = updateUser();
				// 수정 결과 출력
				System.out.println("=======================================================================수정완료=======================================================================");
				System.out.printf("  회원번호\t      이름    \t         주소        \t    연락처    \t      ID\t  비밀번호\t\t 이메일\t\t\t  권한\n");
				System.out.println("======================================================================================================================================================");
				System.out.printf("%s\t %7s\t %10s\t\t %13s \t %10s\t %10s\t %20s\t\t %s\n", willBeUpdated.getUserNo(), willBeUpdated.getUserName(), willBeUpdated.getAddress(), willBeUpdated.getContact(), willBeUpdated.getUserID(), willBeUpdated.getPassword(), willBeUpdated.geteMail(), willBeUpdated.isPermitted() ? "Admin" : "Normal");
				System.out.println("======================================================================================================================================================");
				System.out.println();
				selectWork();
			case "4":
				System.out.println("사용자를 삭제합니다.");
				System.out.println("검색을 통해 삭제할 대상을 선택해 주세요.");
				removeUser();
				selectWork();
			case "5":
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
			case "6":
				System.out.println("로그아웃 되었습니다.");
				finish();
			default:
				System.out.println("올바른 값을 입력해 주세요.");
				selectWork();
		}
		
	}
	// 검색: 어떤 값으로 검색할지 선택(회원번호 / 이름 / 주소 / 전화번호 / ID / 메일)
	// 일부만 검색해도 포함된 내용 표시
	// data의 value로 key를 찾아서 key로 다시 value 반환
	public UserVO searchUser() {
		searchResult = 0;
		UserVO willBeReturned = new UserVO();
		String item = "";
		String itemNo = "";
		boolean canEscape = false;
		do {
			itemNo = LoginMenu.inputData("어떤 항목으로 검색할까요?[1.회원번호  2.이름  3.주소  4.연락처  5.아이디  6.이메일  7.작업취소]");
			switch(itemNo) {
			case "1":
				item = "회원번호";
				canEscape = true;
				break;
			case "2":
				item = "이름";
				canEscape = true;
				break;
			case "3":
				item = "주소";
				canEscape = true;
				break;
			case "4":
				item = "연락처";
				canEscape = true;
				break;
			case "5":
				item = "아이디";
				canEscape = true;
				break;
			case "6":
				item = "이메일";
				canEscape = true;
				break;
			case "7":
				selectWork();
			default:
				System.out.println("올바른 값을 입력해 주세요.");
			}
			if(canEscape) break;
		} while(true);
		System.out.printf("%s(으)로 검색\n", item);
		String keyword = LoginMenu.inputData("검색어를 입력해 주세요");
		
		// 입력된 검색어는 allUsers.values 중, getter를 통해 포함된 값이 있다면 출력
		Collection<UserVO> valuesCollection = AllUsersData.allUsers.values();
		Iterator<UserVO> valuesIterator = valuesCollection.iterator();
		System.out.println("=======================================================================검색결과=======================================================================");
		System.out.printf("  회원번호\t      이름    \t         주소        \t    연락처    \t      ID\t  비밀번호\t\t 이메일\t\t\t  권한\n");
		System.out.println("======================================================================================================================================================");
		while(valuesIterator.hasNext()) {
			UserVO nextValue = valuesIterator.next();
			// 각 UserVO에 대해서 exising 문자열이 keyword 문자열을 포함하는지 체크하여 포함하면 출력 
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
		System.out.printf("검색결과 %d건\n", searchResult);
		System.out.println("======================================================================================================================================================");
		System.out.println();
		return willBeReturned;
	}
	
	// 추가: 추가
	// ★★★★★ key 중복 검사, 글자수 제한 ★★★★★
	public void addUser() {
		MakeAccount makeUserVO = new MakeAccount();
		makeUserVO.makeUserNo();
		makeUserVO.makeUserAccount();
		System.out.println("=======================================================================추가완료=======================================================================");
		UserVO.printUsers();
	}
	
	// 수정: 이름/주소/연락처/비밀번호/메일/접근권한 수정
	public UserVO updateUser() {
		// 수정할 대상 선택
		// searchUser를 시행하되 한 명만 선택해야 하므로 updaiting을 전달
		UserVO willBeUpdated = searchUser();
		do {
			if (searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 수정할 수 있습니다.");
				System.out.println("대상을 다시 선택해 주세요.");
				willBeUpdated = searchUser();
			} else if (searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				System.out.println("대상을 다시 선택해 주세요.");
				willBeUpdated = searchUser();
			} else break;
		} while(true);
		System.out.printf("%s님의 정보를 수정합니다. ", willBeUpdated.getUserName());
		// 수정할 항목 선택
		String itemNo = LoginMenu.inputData("수정할 항목을 선택해 주세요[1.이름  2.주소  3.연락처  4.비밀번호  5.이메일  6.권한]");
		String updated = "";
		// 수정할 대상의 value 중에서 항목이 일치하는 것을 setter로 수정
		boolean canEscape = false;
		switch(itemNo) {
			case "1":
				do {
					updated = LoginMenu.inputData("수정할 이름을 입력해 주세요");
					System.out.printf("%s의 이름을 수정합니다.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkNameLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setUserName(updated);
				break;
			case "2":
				do {
					updated = LoginMenu.inputData("수정할 주소를 입력해 주세요");
					System.out.printf("%s의 주소를 수정합니다.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkAddressLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setAddress(updated);
				break;
			case "3":
				do {
					updated = LoginMenu.inputData("수정할 연락처를 입력해 주세요");
					System.out.printf("%s의 연락처를 수정합니다.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkContactLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setContact(updated);
				break;
			case "4":
				do {
					updated = LoginMenu.inputData("수정할 비밀번호를 입력해 주세요");
					System.out.printf("%s의 비밀번호를 수정합니다.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkPasswordLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.setPassword(updated);
				break;
			case "5":
				do {
					updated = LoginMenu.inputData("수정할 이메일을 입력해 주세요");
					System.out.printf("%s의 이메일 정보를 수정합니다.\n", willBeUpdated.getUserName());
					canEscape = MakeAccount.checkEmailLetters(updated);
					if(canEscape) break;
				} while(true);
				willBeUpdated.seteMail(updated);
				break;
			case "6":
				// getter로 권한을 체크
				boolean currentPermission = willBeUpdated.isPermitted();
				if(currentPermission) {
					updated = LoginMenu.inputData("관리자 권한을 해제하시겠습니까?[1.예 2.아니오]");
				} else {
					updated = LoginMenu.inputData("관리자 권한을 부여하시겠습니까?[1.예 2.아니오]");
				}
				// setter로 새 권한 설정
				if (updated.equals("1")) {
					System.out.printf("%s에게 관리자 권한을 부여합니다.\n", willBeUpdated.getUserName());
					willBeUpdated.setPermitted(!currentPermission);
				} else if(updated.equals("2")) {
					System.out.println("관리자 권한 부여를 취소합니다.");
				} else {
					System.out.println("올바른 값을 입력해 주세요.");
					System.out.println("수정할 항목을 다시 선택해 주세요.");
				}
				break;
			default:
				System.out.println("올바른 값을 입력해 주세요.");
				System.out.println("수정할 대상을 다시 선택해 주세요.");
				updateUser();
		}
		return willBeUpdated;
	}
	
	// 제거: 제거
	public void removeUser() {
		System.out.println("삭제할 대상을 선택해 주세요");
		UserVO willBeRemoved = searchUser();
		do {
			if (searchResult > 1) {
				System.out.println("한 번에 하나의 항목만 삭제할 수 있습니다.");
				System.out.println("대상을 다시 선택해 주세요.");
				willBeRemoved = searchUser();
			} else if (searchResult < 1) {
				System.out.println("검색 결과가 없습니다.");
				System.out.println("대상을 다시 선택해 주세요.");
				willBeRemoved = searchUser();
			} else break;
		} while(true);
		String areYouSure = LoginMenu.inputData("정말로 위 사용자의 정보를 삭제하시겠습니까?[1.예  2.아니오]");
		if (areYouSure.equals("1")) {
			System.out.printf("%s의 사용자 정보가 삭제되었습니다.\n", willBeRemoved.getUserName());
			AllUsersData.allUsers.remove(willBeRemoved.getUserID(), willBeRemoved);
		} else if(areYouSure.equals("2")) {
			System.out.printf("%s의 삭제를 취소합니다.\n", willBeRemoved.getUserName());
		} else {
			System.out.println("잘못된 값이 입력되어 삭제를 취소합니다.");
		}
		
		System.out.println("=======================================================================삭제완료=======================================================================");
		UserVO.printUsers();
	}
	// 종료: 이전 선택으로
	public void finish() {
		System.out.println("로그인 화면으로 돌아갑니다.");
		new LoginMenu();
	}
	
}
