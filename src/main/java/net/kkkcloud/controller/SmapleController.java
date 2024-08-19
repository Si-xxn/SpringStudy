package net.kkkcloud.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.kkkcloud.domain.SampleDTO;
import net.kkkcloud.domain.SampleDTOList;
import net.kkkcloud.domain.TodoDTO;

@Controller // servelt-context.xml 에 있는 <context : component - scan
			// base-pakage="net.kkkcloud.controller"코드라 관리함.
@RequestMapping("/sample/*") // URL 생성됨 http://localhost:80/sample/*
// * 하위폴더 | ** 하위에 하위(모든폴더)
@Log4j2
public class SmapleController {

	@RequestMapping("") // http://localhost:80 /sample/"" >> 빈값
	public void basic() {
		// 리턴타입이 void 경우에는 경로와 같은 URL.jsp 를 찾는다.
		// 파일 [/WEB-INF/views/sample.jsp] 찾을 수 없습니다.

		log.info("SampleController.basic()메서드 실행");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST }) // http://localhost:80/sample/basic
	// get 메서드로 호출 post 메서드로 호출
	public void basicGet() {
		log.info("SampleController.basicGet() get + post 방식의 메서드 호출용");
	}

	@GetMapping("/basicOnlyGet")
	public void basicGetOnly() { // http://localhost:80/sample/basicOnlyGet

		log.info("SampleController.basicGetOnly() get 방식의 메서드로 호출용");
	}

	@PostMapping("/basicOnlyPost")
	public void basicPostOnly() { // http://localhost:80/sample/basicOnlyPost

		log.info("SampleController.basicPostOnly() get 방식의 메서드로 호출용");
	}

	@GetMapping("/ex01") // http://localhost:80/sample/ex01
	public String ex01(SampleDTO sampleDTO) {

		log.info("SmpleController.ex01(SampleDTO sampleDTO) 메서드 실행" + sampleDTO);

		return "ex01"; // /WEB-INF/views/ex01.jsp
	}

	@GetMapping("/ex02") // http://localhost:80/sample/ex02?id=kkk&age=30
	public String ex02(@RequestParam("id") String name, @RequestParam("age") int age) {
		// @RequestParam 파라미터로 사용된 변수 이름과 전달되는 파라미터의 이름이 다른 경우 유용
		// 타입을 정해서 넣을 수 있어 안전하다.
		SampleDTO sampleDTO = new SampleDTO();
		sampleDTO.setName(name);
		sampleDTO.setAge(age);
		log.info("name : " + sampleDTO.getName());
		log.info("age : " + sampleDTO.getAge());

		return "ex02";

	}

	// 리스트 처리

	@GetMapping("/ex02List") // http://localhost:80/sample/ex02List?ids=111&ids=222&ids=333
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids : " + ids);

		return "ex02List";
	}

	// 배열 처리
	@GetMapping("/ex02Array") // http://localhost:80/sample/ex02Array?ids=111&ids=222&ids=333
	public String ex02Array(@RequestParam("ids") String[] ids) {

		log.info("array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}

	@GetMapping("/ex02Bean") // http://localhost:80/sample/ex02Bean?list[0].name=kkw&list[0].age=30 -> 특수 문자
								// [] 로 오류가 난다.
								// http://localhost:80/sample/ex02Bean?list%3Flist%5B0%5D.name%3Dkkw%26list%5B0%5D.age%3D30
	public String ex02Bean(SampleDTOList list) { // 리스트 객체를 매개값으로 받음

		log.info("list dtos : " + list);

		return "ex02Bean";
	}

	// 상단에서 만든 initBinder를 활용한 날짜타입 입력

	@GetMapping("/ex03") //// http://localhost:80/sample/ex03?title=study&dueDate=2024-08-14&check=true
	public String ex03(TodoDTO todoDTO) {
		log.info("todo : " + todoDTO);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page")int page) {
		// @ModelAttribute("page") >> URL을 통해서 넘어온 값을 model에 담음
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04"; // URL : http://localhost:80/sample/ex04.jsp
		// view : servlet-context.xml 에서 담당 >> 실제경로 : /WEB-INF/views/sample/ex04.jsp
	}
	
	// 리턴 타입 테스트
	@GetMapping("/ex05") // http://localhost:80/sample/ex05
	public void ex05() { // void >> /WEB-INF/views/sample/ex05.jsp 찾음(URL과 똑같은 jsp 파일을 찾음)
		log.info("/ex05 메서드 실행");		
	}
	
	// 컨트롤러에서 처리한 값은 json 으로 출력 테스트
	@GetMapping("/ex06") // http://localhost:80/sample/ex06
	public @ResponseBody SampleDTO ex06() { // @ResponseBody SampleDTO 응답바디영역에 객체를 담아 리턴
		log.info("/ex06 메서드 실행");
		
		SampleDTO dto = new SampleDTO(); // 빈객체 생성
		dto.setName("엠비씨");
		dto.setAge(20);
		
		return dto; // json {"name":"엠비씨","age":20} >> 백개발자는 json 으로만 보냄
//														  프론트개발자는 화면에 div, table 등 이용해서 보여줌
	}
	
	// 응답헤더에 값을 추가하여 보냄
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07 메서드 실행");
		
		String msg = "{\"name\":\"엠비씨\",\"age\":20}";			// json {"name":"엠비씨","age":20}
		HttpHeaders header = new HttpHeaders();						// HttpHeaders 객체 생성
		header.add("Content-Type", "application/json;charset=UTF-8"); // 헤더에 타입 추가 json 명시
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK); // HttpStatus.OK 200 정상코드임을 보냄
	}
	
	@GetMapping("/exUpload") // http://localhost:80/sample/exUpload
	public void exUpload() {
		log.info("/exUpload 메서드 실행");
		
		// 리턴 타입 void >> http://localhost:80/sample/exUpload.jsp
	}
	
	@PostMapping("exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("------------------------");
			log.info("name : " + file.getOriginalFilename()); // 원본 파일명 출력
			log.info("size : " + file.getSize());			  // 파일 크기
			log.info("toString : " + file.toString());		  // toString 메서드 출력
		});
	}

}