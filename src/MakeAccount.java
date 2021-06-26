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

	// 날짜 정보로 userNo를 생성
	public String makeUserNo() {
		Calendar now = Calendar.getInstance();
		Date currentDate = now.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String userNo = format.format(currentDate);
		return userNo;
	}
	
	// 새로운 UserVO 객체 생성해서 allUsers에 업데이트
	// userNo: 생성해서 부여
	// userName, address, contact, userID, password, eMail: 입력받은 대로 등록
	// permitted: 기본 false 부여(master만이 회원정보 수정에서 수정 가능)
	public void makeUserAccount() {
		// ★★★ 입력 문자에 길이 제한 두기 ★★★
		boolean canEscape = false;
		String name = "";
		String address = "";
		String contact = "";
		String id = "";
		String password = "";
		String email = "";
		
		do {
			name = LoginMenu.inputData("이름을 입력하세요");
			canEscape = checkNameLetters(name);
			if (canEscape) break;
		} while(true);
			
		do {
			address = LoginMenu.inputData("주소를 입력하세요");
			canEscape = checkAddressLetters(address);
			if (canEscape) break;
		} while(true);
		
		do {
			contact = LoginMenu.inputData("연락처를 입력하세요");
			canEscape = checkContactLetters(contact);

			if (canEscape) break;
		} while(true);
		
		do {
			do {
				// 입력한 userId가 allUsers의 키로써 존재하면 중복이므로 없애기
				id = LoginMenu.inputData("새로운 ID를 입력하세요");
				canEscape = checkSameID(id);
				if (canEscape) break;	
			} while(true);
			
			canEscape = checkIdLetters(id);
			if (canEscape) break;
		} while(true);

		
		do {
			password = LoginMenu.inputData("비밀번호를 입력하세요");
			canEscape = checkPasswordLetters(password);

			if (canEscape) break;
		} while(true);
		
		do {
			do {
				// 입력한 eamil에 @이 존재하지 않거나 .이 존재하지 않거나 @이 . 뒤에 있거나 도메인이 2글자 이하거나... 면 다시 
				email = LoginMenu.inputData("이메일 주소를 입력하세요");
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
			System.out.println("이름은 2~6자까지만 입력 가능합니다.");
			return false;
		} else return true;
	}
	
	public static boolean checkAddressLetters(String address) {
		if(address.length() > 4 || address.length() < 2) {
			System.out.println("주소는 2~4자까지만 입력 가능합니다.");
			return false;
		} else return true;
	}
	
	public static boolean checkContactLetters(String contact) {
		if(contact.length() > 13 || contact.length() < 9) {
			System.out.println("연락처는 하이픈(-)을 포함하여 13자까지만 입력 가능합니다.");
			return false;
		} else return true;
	}
	
	public static boolean checkIdLetters(String id) {
		if(id.length() > 11 || id.length() < 5) {
			System.out.println("ID는 5~11자까지만 입력 가능합니다.");
			return false;
		} else return true;
	}
	
	public static boolean checkPasswordLetters(String password) {
		if(password.length() > 11 || password.length() < 5) {
			System.out.println("비밀번호는 5~11자까지만 입력 가능합니다.");
			return false;
		} else return true;
	}
	
	public static boolean checkEmailLetters(String email) {
		if(email.length() > 20 || email.length() < 15) {
			System.out.println("이메일은 도메인을 포함하여 15~20자까지만 입력 가능합니다.");
			return false;
		} else return true;
	}
	
	public static boolean checkSameID(String id) {
		Set<String> keys = AllUsersData.allUsers.keySet();
		Iterator<String> keysIterator = keys.iterator();
		while(keysIterator.hasNext()) {
			String oneKey = keysIterator.next();
			if(oneKey.equals(id)) {
				System.out.println("이미 등록된 ID입니다.");
				return false;
			}
		}
		return true;				
	}
	
	public static boolean checkEmail(String email) {
		int atmark = email.indexOf("@"); // indexOf는 찾는 요소가 없으면 -1을 반환한다.
		int comma = email.indexOf(".");
		//  @이 존재하지 않거나 .이 존재하지 않거나 @이 . 뒤에 있거나    도메인이 2글자 이하거나 ... 뭔가 더 있을 수 있는데 이정도만 한다.
		if (atmark == -1 || comma == -1 || atmark > comma || comma - atmark <= 2) { // 잘못된 데이터 거르기(false 반환)
			System.out.println("도메인 주소를 정확히 입력해 주세요.");
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	
	
}