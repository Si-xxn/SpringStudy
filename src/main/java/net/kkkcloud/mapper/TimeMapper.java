package net.kkkcloud.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TimeMapper {
	// root-context.xml 에서 적용한 mybatis를 스캔 당한다.
	// <mybatis-spring:scan base-package="net.kkkcloud.mapper"/> - 패키지를 통해서 mybatis 가 검색한다.
	
	// 추상메서드가 있다.(추상메서드는 실행블럭 없이 ; 으로 끝난다.
	
	// create
	@Insert("insert into Board values()")
	public String createBoard();
	
	// read
	@Select("select sysdate from dual") 
	public String getTime();
	
	// @Select로 쿼리를 처리하는 것에 한계가 있어서 xml 활용
	// resources 폴더에 패키지명과 같은 폴더를 다단계로 생성하고 xml 파일명을 인터페이스 명과 일치시킴
	public String getTimeXML();
	
	// update
	@Update("update set values ()")
	public String updateBoard();
	
	// delete
	@Delete("delete from board where 조건")
	public String deleteBoard();
}
