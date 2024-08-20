package org.zerock.domain;
// domain 패키지 - 객체담당

import java.util.Date;

import lombok.Data;

@Data // getter/setter , toString, equals, 생성자 등 관리
public class BoardVO { // tbl_board 테이블 객체 담당

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
}
