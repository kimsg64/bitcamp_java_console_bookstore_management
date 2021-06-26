package database;

import java.util.Collection;
import java.util.Iterator;

public class UserVO {
	// 각 유저의 데이터를 받는 형식을 가지고 있는 클래스
	private String userNo;				// 유저 번호(가입년월일000)
	private String userName;			// 이름
	private String address;				// 주소
	private String contact;				// 연락처
	private String userID;				// ID
	private String password;			// Password
	private String eMail;				// E-Mail
	private boolean master = false;		// 접근 권한 부여 권한
	private boolean permitted = false;	// 접근 권한

	public UserVO() {
	}

	// 입력용 생성자 메소드
	public UserVO(String userNo, String userName, String address, String contact, String userID, String password, String eMail, boolean permitted, boolean master) {
		this.userNo = userNo;
		this.userName = userName;
		this.address = address;
		this.contact = contact;
		this.userID = userID;
		this.password = password;
		this.eMail = eMail;
		this.permitted = permitted;
		this.master = master;
	}

	// 데이터 출력 메소드
	public static void printUsers() {
		//					   12	        (6)4(5)           (8)4(8)           (3)6(4)
		System.out.printf("회원번호              이름\t         주소        \t    연락처    \t      ID\t  비밀번호\t\t 이메일\t\t\t  권한\n");
		System.out.println("======================================================================================================================================================");
		Collection<UserVO> usersValue = AllUsersData.allUsers.values();
		Iterator<UserVO> usersIterator = usersValue.iterator();
		while(usersIterator.hasNext()) {
			UserVO oneMember = usersIterator.next();
			// 					12   14    20     14       
			System.out.printf("%s\t %7s\t %10s\t\t %13s \t %10s\t %10s\t %20s\t\t %s\n", oneMember.getUserNo(), oneMember.getUserName(), oneMember.getAddress(), oneMember.getContact(), oneMember.getUserID(), oneMember.getPassword(), oneMember.geteMail(), oneMember.isPermitted() ? "Admin" : "Normal");
		}
		System.out.println("======================================================================================================================================================");
		System.out.println();
	}
	

	// getter and setter
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public boolean isPermitted() {
		return permitted;
	}
	public void setPermitted(boolean permitted) {
		this.permitted = permitted;
	}
	public boolean isMaster() {
		return master;
	}
	public void setMaster(boolean master) {
		this.master = master;
	}
	

}

