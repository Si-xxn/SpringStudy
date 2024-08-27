package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo); // 댓글 등록 -> int 리턴
	
	public ReplyVO get(Long rno); // 댓글 1개 가져옴
	
	public int modify(ReplyVO vo); // 댓글 1개 수정 -> int 리턴
	
	public int remove(Long rno); // 댓글 1개 삭제 -> int 리턴
	
	public List<ReplyVO> getList(Criteria cri, Long bno); // 게시글 번호를 이용해 모든 댓글 리스트로 출력
}
