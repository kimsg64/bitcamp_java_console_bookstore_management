package database;

import java.util.HashMap;

public class AllUsersData {
	
	// ��� ������ ������ ��� �ִ� ��ü
	public static HashMap<String, UserVO> allUsers = new HashMap<String, UserVO>();
	
	public AllUsersData() {
	}

	// �����ͺ��̽��� �����Ƿ� �ӽ÷� ȸ�� �� �� �߰��� �α�(ID�� key��)
	public static void setAllUsers() {
		allUsers.put("kimadmin", new UserVO("000604011742", "�����", "���α�", "010-1234-5678", "kimadmin", "iloveadmin", "kimadmin@gmail.com", true, true));
		allUsers.put("adminman", new UserVO("000604011986", "����Ŵ", "���ֵ�", "010-1234-5678", "adminman", "12345678", "adminlove@gmail.com", true, false));
		allUsers.put("lbjeducated", new UserVO("031230061055", "���ü�", "������", "010-8765-5678", "lbjeducated", "lakers23", "noteducated@nate.com", false, false));
		allUsers.put("splashbros", new UserVO("090314113555", "�̸���", "������", "010-3948-9184", "splashbros", "states31", "splashbros@gmail.com", false, false));
		allUsers.put("dametime", new UserVO("120715002731", "������", "���빮��", "010-3954-3945", "dametime", "blazers0", "dametime@gmail.com", false, false));
		allUsers.put("cp3fagungsa", new UserVO("050506034733", "����", "���ı�", "010-0394-9874", "cp3fagungsa", "phoenix3", "fagungsa@gmail.com", false, false));
		allUsers.put("lkncp3lqw", new UserVO("000506034733", "Ȳ�ݵ�����", "������", "010-9845-9874", "lkncp3lqw", "password", "fagungsa@naver.com", false, false));
		allUsers.put("cp3gugucon", new UserVO("058756034733", "���Ĵ�", "������", "010-1298-5482", "cp3gugucon", "goatoaheall", "gugucom@naver.com", false, false));
	}
	
}