package database;

import java.util.HashMap;

public class AllBooksData {

	// 모든 책의 정보를 담고 있는 객체
	public static HashMap<String, BookVO> allBooks = new HashMap<String, BookVO>();
	
	public AllBooksData() {
	}

	// 데이터베이스가 없으므로 임시로 책 몇 권 추가해 두기(title을 key로)
	public static void setAllBooks () {
		allBooks.put("혼자 공부하는 자바스크립트", new BookVO("혼자 공부하는 자바스크립트", "윤인성", "한빛미디어", "IT모바일", 10, 24000));
		allBooks.put("문명1", new BookVO("문명1", "베르나르 베르베르", "열린책들", "소설", 50, 14800));
		allBooks.put("문명2", new BookVO("문명2", "베르나르 베르베르", "열린책들", "소설", 50, 14800));
		allBooks.put("기사단장 죽이기1", new BookVO("기사단장 죽이기1", "무라카미 하루키", "문학동네", "일본소설", 50, 16300));
		allBooks.put("기사단장 죽이기2", new BookVO("기사단장 죽이기2", "무라카미 하루키", "문학동네", "일본소설", 60, 16300));
		allBooks.put("프로그래머의 길, 멘토에게 묻다", new BookVO("프로그래머의 길, 멘토에게 묻다", "데이브 후버", "인사이트", "IT모바일", 20, 14000));
		allBooks.put("성공 프로젝트, 마이클 조던 되기", new BookVO("성공 프로젝트, 마이클 조던 되기", "팻 윌리엄스", "해냄", "성공학", 35, 9000));
		allBooks.put("당신의 뇌, 미래의 뇌", new BookVO("당신의 뇌, 미래의 뇌", "김대식", "해나무", "자연과학", 24, 16800));
		allBooks.put("나생문", new BookVO("나생문", "아쿠타가와 류노스케", "소와다리", "고전문학", 3, 9800));
		allBooks.put("한권으로 그리는 컴퓨터 과학 로드맵", new BookVO("한권으로 그리는 컴퓨터 과학 로드맵", "블라드스톤 페헤이라 필루", "인사이트", "IT모바일", 30, 18000));
	}
}
