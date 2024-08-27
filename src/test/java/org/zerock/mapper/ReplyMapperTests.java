package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// Java Config
// @ContextConfiguration(classes = {org.zerock.config.RootConfig.class} )
@Log4j2
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Test
	public void testMapper() {
		
		log.info(mapper);
		// INFO  org.zerock.mapper.ReplyMapperTests(testMapper28) - org.apache.ibatis.binding.MapperProxy@6ab7ce48
	}
	
	@Test
	public void testCreate() {
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(11L);
		vo.setReply("댓글 테스트");
		vo.setReplyer("매퍼");
		
		mapper.insert(vo);
	}
	
	@Test
	public void testRead() {
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
		// INFO  org.zerock.mapper.ReplyMapperTests(testRead63) - ReplyVO(rno=10, bno=7, reply=댓글 7, replyer=kkk, replyDate=Tue Aug 27 10:56:44 KST 2024, updateDate=Tue Aug 27 10:56:44 KST 2024)
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno); // 객체 가져옴
		
		vo.setReply("댓글 수정 테스트");
		
		int count = mapper.update(vo); // 수정된 객체를 넣고 int 타입으로 결과를 받음
		
		log.info("Update Count : " + count);
		// INFO  org.zerock.mapper.ReplyMapperTests(testUpdate77) - Update Count : 1
		
		log.info("수정된 객체 : " + vo);
		//  INFO  org.zerock.mapper.ReplyMapperTests(testUpdate80) - 수정된 객체 : ReplyVO(rno=10, bno=7, reply=댓글 수정 테스트, replyer=kkk, replyDate=Tue Aug 27 10:56:44 KST 2024, updateDate=Tue Aug 27 12:06:12 KST 2024)
	}
	
	@Test
	public void testDelete( ) {
		Long targetRno = 1L;
		
		log.info("삭제 후 결과 : " + mapper.delete(targetRno));
		//  INFO  org.zerock.mapper.ReplyMapperTests(testDelete71) - 삭제 후 결과 : 1
		
	}
	
	// 번호를 이용한 객체가 리스트로 출력
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		log.info("Criteria : " + cri); // INFO  org.zerock.mapper.ReplyMapperTests(testList85) - Criteria : Criteria(pageNum=1, amount=10, type=null, keyword=null)
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 10L);
		
		replies.forEach(reply -> log.info(reply));
		// INFO  org.zerock.mapper.ReplyMapperTests(lambda$088) - ReplyVO(rno=2, bno=10, reply=댓글 10, replyer=kkk, replyDate=Tue Aug 27 10:56:31 KST 2024, updateDate=Tue Aug 27 10:56:31 KST 2024)
		// INFO  org.zerock.mapper.ReplyMapperTests(lambda$088) - ReplyVO(rno=16, bno=10, reply=댓글 10, replyer=kkk, replyDate=Tue Aug 27 10:56:52 KST 2024, updateDate=Tue Aug 27 10:56:52 KST 2024)

	}

}
