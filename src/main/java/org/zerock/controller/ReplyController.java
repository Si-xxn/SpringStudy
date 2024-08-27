package org.zerock.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController // Rest 로 응답 -> view(JSP)가 아닌 json, xml로 나온다
@RequestMapping("/replies/") // URL 생성
@Log4j2
@AllArgsConstructor // new ReplyController(ReplyService);
public class ReplyController { // Rest 방식의 컨트롤러로 구현 + Ajax 처리함
	private ReplyService service;
	
	// http://localhost:80/replies/new
	@PostMapping(value="/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE}) // JSON 방식의 데이터만 처리 -> 문자열 반환
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) { // @RequestBody : JSON 데이터를 ReplyVO 타입으로 변환하도록 지정
		// 리턴은 200  |  500 으로 처리된다.
		log.info("ReplyVO 객체 json 입력 값 : " + vo); // 파라미터로 받은 입력 값 처리
		
		int insertCount = service.register(vo); // 결과 값 1 또는 0
		log.info("서비스 + 매퍼 처리 결과 : " + insertCount);
		
		return insertCount == 1
				? new  ResponseEntity<>("success", HttpStatus.OK) // 200 정상(결과 값이 1이면)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500에러 -> 서버(내부)오류(결과 값이 1이 아니면)
		// 삼항 연산자 : if로 리턴을 할 때 정상 처리인지 오류 값인지 전달
	}
	
	//http://localhost:80/replies/new/pages/10/1 -> xml
	//http://localhost:80/replies/new/pages/10/1.json -> json
	@GetMapping(value="/pages/{bno}/{page}",
			produces = { MediaType.APPLICATION_XML_VALUE,
						 MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		
		log.info("ReplyController.getList()메서드 실행");
		log.info("Page 번호 : " + page);
		log.info("찾을 번호 : " + bno);
		
		Criteria cri = new Criteria(page, 10);
		
		log.info("Criteria : "+cri);
		
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
		// [{"rno":2,"bno":10,"reply":"댓글 10","replyer":"kkk","replyDate":1724723791000,"updateDate":1724723791000},
		// {"rno":16,"bno":10,"reply":"댓글 10","replyer":"kkk","replyDate":1724723812000,"updateDate":1724723812000},
		// {"rno":21,"bno":10,"reply":"postmanTest","replyer":"kkk","replyDate":1724735370000,"updateDate":1724735370000}]
	}
	
	// http://localhost:80/replies/11
	@GetMapping(value="/{rno}",
			produces = { MediaType.APPLICATION_XML_VALUE,
						 MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		log.info("ReplyController.get()메서드 실행 ");
		log.info("찾을 rno : " + rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
		// <ReplyVO><rno>4</rno><bno>8</bno><reply>댓글 8</reply><replyer>kkk</replyer><replyDate>1724723793000</replyDate><updateDate>1724723793000</updateDate></ReplyVO>
	}
	
	// http://localhost:80/replies/4
	@DeleteMapping(value="/{rno}",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		log.info("ReplyController.remove 메서드 실행");
		log.info("삭제 rno : " + rno);
		
		return service.remove(rno) == 1
		? new  ResponseEntity<>("success", HttpStatus.OK) // 200 정상(결과 값이 1이면)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500에러 -> 서버(내부)오류(결과 값이 1이 아니면)
	}
	
	
	
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
			value="/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
//											이미 폼에 있는 값				수정 할 번호
		vo.setRno(rno); // 가지고 있는 객체의 rno 값
		log.info("수정 댓글 번호 : " + rno);
		
		
		return service.modify(vo) == 1
		? new  ResponseEntity<>("success", HttpStatus.OK) // 200 정상(결과 값이 1이면)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500에러 -> 서버(내부)오류(결과 값이 1이 아니면)	;
	}
}
