package database;

import java.util.HashMap;

public class AllUsersData {
	
	// 모든 유저의 정보를 담고 있는 객체
	public static HashMap<String, UserVO> allUsers = new HashMap<String, UserVO>();
	
	public AllUsersData() {
	}

	// 데이터베이스가 없으므로 임시로 회원 몇 명 추가해 두기(ID를 key로)
	public static void setAllUsers() {
		allUsers.put("kimadmin", new UserVO("000604011742", "김관리", "종로구", "010-1234-5678", "kimadmin", "iloveadmin", "kimadmin@gmail.com", true, true));
		allUsers.put("adminman", new UserVO("000604011986", "어드민킴", "제주도", "010-1234-5678", "adminman", "12345678", "adminlove@gmail.com", true, false));
		allUsers.put("lbjeducated", new UserVO("031230061055", "남궁성", "마포구", "010-8765-5678", "lbjeducated", "lakers23", "noteducated@nate.com", false, false));
		allUsers.put("splashbros", new UserVO("090314113555", "이마더", "강남구", "010-3948-9184", "splashbros", "states31", "splashbros@gmail.com", false, false));
		allUsers.put("dametime", new UserVO("120715002731", "유저팍", "동대문구", "010-3954-3945", "dametime", "blazers0", "dametime@gmail.com", false, false));
		allUsers.put("cp3fagungsa", new UserVO("050506034733", "계사용", "송파구", "010-0394-9874", "cp3fagungsa", "phoenix3", "fagungsa@gmail.com", false, false));
		allUsers.put("lkncp3lqw", new UserVO("000506034733", "황금독수리", "강남구", "010-9845-9874", "lkncp3lqw", "password", "fagungsa@naver.com", false, false));
		allUsers.put("cp3gugucon", new UserVO("058756034733", "김파더", "마포구", "010-1298-5482", "cp3gugucon", "goatoaheall", "gugucom@naver.com", false, false));
	}
	
}