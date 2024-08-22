<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ include file="../includes/header.jsp" %>    
<!-- list.jsp에서 /board/register 경로 호출하면 get메서드 실행 폼 박스 출력 -->
<!-- 입력 완료 클릭 시 VO 객체 생성 후 /board/register 경로로 post 메서드 실행 -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 수정</h1>
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
	
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify Page</div>
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
					<div class="form-group">
						<label>번호</label>
						<input class="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>Title</label> <input class="form-control" name="title" value='<c:out value="${board.title}" />'/>
					</div> <!-- title .form-group end -->
					
					<div class="form-group">
						<label>Content</label>
						<textarea class="form-control" rows="3" name="content"><c:out value="${board.content}"/></textarea>
					</div> <!-- content .form-group end -->
					
					<div class="form-group">
						<label>Writer</label> <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly="readonly" />
					</div> <!-- writer .form-group end -->
					
					<div class="form-group">
						<label>RegDate</label> <input class="form-control" name="regdate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate }"/>' readonly="readonly" />
					</div> <!-- regDate .form-group end -->
					
					<div class="form-group">
						<label>Update Date</label> <input class="form-control" name="updateDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate }"/>' readonly="readonly" />
					</div> <!-- updateDate .form-group end -->
					
					<button type="submit" data-oper='modify' class="btn btn-default">수정</button>
					<button type="submit" data-oper='remove' class="btn btn-default">삭제</button>
					<button type="submit" data-oper='list' class="btn btn-info" onclick="location.href='/board/list'">목록</button>	
					<!-- submit 여러개인 경우 javaScript 이용함 -->	
				</form>
			</div> <!-- .panel-body end -->
		</div> <!-- .panel panel-default end-->
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<script type="text/javascript">
$(document).ready(function(){ /* 브라우저 시작 시 실행 */
	var formObj = $("form"); /* 상단 코드중에 form 태그를 formObj로 관여한다. */
	$('button').on("click", function(e){
		e.preventDefault(); 
		/* <form>태그의 모든 버튼은 기본적으로 submit 처리 -> 기본 동작을 막고 마지막에 직접 submit()수행 */
		var operation = $(this).data("oper");
		console.log(operation); /* 개발자 도구 console에서 확인 */
		
		if(operation === 'remove') { /* data-oper='remove' -> 삭제 컨트롤러 요청 */
			formObj.attr("action","/board/remove");
		} else if(operation === 'list') { /* data-oper='list' -> list.jsp로 이동 */
			formObj.attr("action", "board/list").attr("method", "get");
			formObj.empty();
			
		}
		formObj.submit(); /* data-oper='modify' -> 24행 실행 */
	});
});
</script>
<%@ include file="../includes/footer.jsp" %>