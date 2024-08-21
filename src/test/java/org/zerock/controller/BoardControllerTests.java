package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // 프론트 테스트용
@ContextConfiguration({ // 배열처리
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j2
public class BoardControllerTests {
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx; // 톰캣 대신
	
	private MockMvc mockMvc; // 크롬 대신
	
	@Before // JUnit 으로 import >> 구동 전에 선행되어야 하는 코드 작성
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test // URL과 결과를 처리해주는 테스트
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")) // URL 
				.andReturn()											   // 결과
				.getModelAndView() 										   // 모델에서 뷰까지
				.getModelMap()); 										   // 표형식 출력
	}
	
	@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "컨트롤러 테스트 제목")
				.param("content", "컨트롤러 테스트 내용")
				.param("writer", "controller")
				).andReturn().getModelAndView().getViewName();
		// getViewName : return 값을 받아서 String 처리 = resultPage
		log.info(resultPage); // 결과 : redirect:/board/list
	}
	
	@Test // BNO 넘어가면 돌아오는 값은 객체
	public void testGet() throws Exception { // http://localhost:80/board/get?bno=10
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "6"))
				.andReturn()
				.getModelAndView().getModelMap());				
	}
	
	@Test
	public void testModify() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "6")
				.param("title", "컨트롤러 수정 테스트 제목")
				.param("content", "컨트롤러 수정 테스트 내용")
				.param("writer", "controller")
				).andReturn().getModelAndView().getViewName();
		// getViewName : return 값을 받아서 String 처리 = resultPage
		log.info("결과 : " + resultPage); // 결과 : redirect:/board/list
	}
	
	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "7"))
				.andReturn().getModelAndView().getViewName();
		// getViewName : return 값을 받아서 String 처리 = resultPage
		log.info("결과 : " + resultPage); // 결과 : redirect:/board/list
	}
	
}
