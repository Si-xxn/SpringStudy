package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class) // Junit4 : 메서드별 테스트용
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 참고할 파일
@Log4j2
public class BoardMapperTests { // 테스트용 코드
	
	@Setter(onMethod_ = @Autowired ) // 생성자 자동 주입
	private BoardMapper mapper;
	
	@Test // 메서드별 테스트 JUnit
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test // board 객체 삽입용 테스트
	public void testInsert() {
		BoardVO boardVO = new BoardVO(); // 빈객체 생성
		boardVO.setTitle("매퍼로 작성하는 제목");		
		boardVO.setContent("매퍼로 작성하는 내용");
		boardVO.setWriter("mapper"); // 객체에 내용 삽입 완료
		
		mapper.insert(boardVO);
		
		log.info("입력된 객체 : "+boardVO);
	}
	@Test
	public void testInsertSelectKey() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("select key 제목");		
		boardVO.setContent("select key 내용");
		boardVO.setWriter("selectKey");
		
		mapper.insertSelectKey(boardVO); // 번호 먼저 생성 후 insert
		
		log.info(boardVO);
	}
	
	@Test
	public void testRead() {
		BoardVO boardVO = mapper.read(5L);
		
		log.info(boardVO);
	}
	
	@Test
	public void testUpdate() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(5L); // 게시물 번호
		boardVO.setTitle("수정테스트 제목");		
		boardVO.setContent("수정테스트 내용");
		boardVO.setWriter("user00");
		
		int count = mapper.update(boardVO);
		log.info("Update count : " + count);
		log.info("Update : " + boardVO);
	}
	
	@Test
	public void testDelete() {
		log.info("Delete count : " + mapper.delete(3L));
	}

}
