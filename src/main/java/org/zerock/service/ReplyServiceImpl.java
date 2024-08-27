package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service // 스프링에서 서비스 관리
@Log4j2 // log 찍는 용
public class ReplyServiceImpl implements ReplyService{ // ReplyService의 구현 클래스

	@Setter(onMethod_ = @Autowired )
	private ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {
		
		log.info("ServiceImple.register 메서드 실행 : " + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("ServiceImple.get 메서드 실행 : " + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {

		log.info("ServiceImple.modify 메서드 실행 : " + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		
		log.info("ServiceImple.remove 메서드 실행 : " + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("ServiceImple.get 메서드 실행 : " + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}
	
	
}
