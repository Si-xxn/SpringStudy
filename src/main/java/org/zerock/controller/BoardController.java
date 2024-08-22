package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller // 스프링이 컨트롤러 역할을 제공
@Log4j2
@RequestMapping("/board/*") // URL 생성 >> http://localhost:80/board/??
@AllArgsConstructor	// 모든 필드값으로 생성자 만듬
public class BoardController {

	// 필드
	private BoardService service; // BoardController boardController = new BoardController(service)
	
	@GetMapping("/list") // http://localhost:80/board/list
	public void list(Model model) { // Model : 스프링이 관리하는 메모리
		log.info("BoardController.list 메서드 실행");
		model.addAttribute("list", service.getList()); // name : list , Object : List<BoardVO>
	}
	
	@GetMapping("/register") // // http://localhost:80/board/register
	public void register() {
		// 페이지를 보여주는 역할만 하기 때문에 별도의 처리가 필요하지 않음
		log.info("BoardController.register get메서드 실행");
		
		// 리턴 void : URL과 같은 jsp 파일을 찾는다.(http://localhost:80/board/register.jsp)
	}
	
	@PostMapping("/register") // http://localhost:80/board/register
	public String register(BoardVO board, RedirectAttributes rttr) {
		// RedirectAttributes : 일회성을 가지는 값을 제공 (addFlashAttribute("name","value")
		log.info("BoardController.register post메서드 실행");
		service.register(board); // 프론트에서 form 값이 객체로 넘어옴
		
		rttr.addFlashAttribute("result", board.getBno()); // 객체에 있는 BNO값을 1회성으로 model영역이 가지고있음
		
		return "redirect:/board/list"; // 등록 후 list 페이지로 보냄 http://localhost:80/board/list
	}
	
	@GetMapping({"/get", "/modify"}) // http://localhost:80/board/get
	// 이중화 작업 : http://localhost:80/board/get -> board/get.jsp
	// 이중화 작업 : http://localhost:80/board/modify -> board/modify.jsp
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("BoardController.get 메서드 실행");
		model.addAttribute("board", service.get(bno));
		// 서비스 계층에 get메서드에 BNO 값을 넣어 주면 SQL 처리후 객체 나옴
	}
	
	@PostMapping("/modify") // http://localhost:80/board/modify
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("BoardController.modify 메서드 실행");
		
		if(service.modify(board)) {// service.modify의 리턴 타입이 boolean
			rttr.addFlashAttribute("result", "success"); //수정 성공 시 success 메시지 보냄
		} else {
			rttr.addFlashAttribute("result", "fail"); // 수정 실패 시 fail 메시지 보냄
		}
		return "redirect:/board/list"; // 결론 http://localhost:80/board/list
	}
	
	@PostMapping("/remove") // http://localhost:80/board/remove
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) { // 번호를 받아 delete 쿼리 실행
		log.info("BoardController.remove 메서드 실행");
		
		if(service.remove(bno)) {// service.delete의 리턴 타입이 boolean
			rttr.addFlashAttribute("result", "success"); //수정 성공 시 success 메시지 보냄
		} else {
			rttr.addFlashAttribute("result", "fail"); // 수정 실패 시 fail 메시지 보냄
		}
		
		return "redirect:/board/list";
	}
	
}
