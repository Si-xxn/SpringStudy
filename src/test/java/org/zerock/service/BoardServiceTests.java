package org.zerock.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private BoardService service; // setService(BoardService)
	// 인터페이스를 필드로 생성 -> 서비스 객체를 실행 시 Impl 클래스 실행된다.
	
	@Test
	public void testExist() {
		// 객체 생성 유무 판단용 테스트
		log.info(service); 
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("서비스로 만든 제목");		
		board.setContent("서비스로 만든 내용");
		board.setWriter("service");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	@Test
	public void testGet() {
		log.info(service.get(1L));
	}
	
	@Test
	public void testDelete() {
		log.info("Remove result : " + service.remove(2L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(1L);
		
		if(board == null) {
			log.info("찾는 객체가 없습니다.");
			return;
		}
		
		board.setTitle("서비스에서 제목 수정");
		log.info("Modify result : " + service.modify(board));
	}

}
