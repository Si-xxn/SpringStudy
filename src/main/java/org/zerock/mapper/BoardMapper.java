package org.zerock.mapper;
// DB와 영속성을 가진 패키지

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	// interface 선언 이유 : 추상메서드와 XML을 결합하여 구현 클래스를 사용하는 마이바티스
	// XML을 생성할 때는 resources 안에 폴더를 계층별로 만들고 파일명을 인터페이스와 같게 XML을 생성
	
	// 인터페이스에 자체적인 추상메서를 활용
//	@Select("select * from tbl_board where bno>0") // where BNO>0 -> BNO가 기본키(PK)라 인덱싱이 되어있어서 빠름
	public List<BoardVO> getList(); // 인터페이스는 추상메서드
	// 리턴 - List<BoardVO> 임으로 배열 안쪽에 객체가 BoardVO로 완성된다.
	
	// board 삽입용 코드
	public void insert(BoardVO board);
	
	// board 삽입 할 번호 파악 후 게시물 등록
	public void insertSelectKey(BoardVO board);
	
	// 게시물 번호로 객체 출력 
	public BoardVO read(Long bno);
	
	// 게시물 번호로 게시글 수정
	public int update(BoardVO board);
	
	// 게시물 번호로 게시글 삭제
	public int delete(Long bno);
}
