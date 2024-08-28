package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	// xml와 연동해서 sql 처리
	// 추상메서드 필요
	
	public int insert(ReplyVO reply); // 외부에서 폼으로 ReplyVO 객체 넘어옴 -> return 타입은 int 	
	
	public ReplyVO read(Long rno); // 댓글 번호로 댓글 객체 가져옴
	
	public int update(ReplyVO vo); // 객체가 넘어와서 수정됨 -> int 타입으로 결과 리턴
	
	public int delete(Long rno); // 댓글 번호로 레코드 삭제 후 int 타입으로 결과 리턴
	
	// 댓글 리스트 : 페이징처리 + BNO(PK + FK) -> 여러개의 파라미터인 경우 @Param()을 사용하면 편함
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri, // 페이징 처리 기분점
			@Param("bno") Long bno);    // 게시물 번호 (PK + FK)
	
	public int getCountByBno(Long bno);
	
}
