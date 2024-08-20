package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2	 // 로그 출력
@Service // Spring Service 담당 코드 (비즈니스 로직 영역 담당)
		 // root-context.xml - context 항목 추가
@AllArgsConstructor // 모든 매개값을 이용해서 자동으로 생성자 만듬
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired )
	private BoardMapper mapper;	// 필드 생성
	
	@Override
	public void register(BoardVO board) {
		// board 객체가 넘어온 값을 DB insert 처리함
		log.info("BoardServiceImpl.register 메서드 실행");
		mapper.insertSelectKey(board); // 자동번호 먼저 생성하고 insert 처리
	}

	@Override
	public BoardVO get(Long bno) {
		// BNO 이용해서 매퍼에 select 조건으로 검색
		log.info("BoardServiceImpl.get 메서드 실행");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		// 게시글 수정 담당
		log.info("BoardServiceImpl.modify 메서드 실행");
		return mapper.update(board) == 1; // 결과가 1이면 true | 1이 아니면 false
	}

	@Override
	public boolean remove(Long bno) {
		// 게시글 삭제 담당
		log.info("BoardServiceImpl.remove 메서드 실행");
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		// board 테이블에 모든 값을 리스트로 출력
		log.info("BoardServiceImpl.getList 메서드 실행");
		return mapper.getList();
	}

}
