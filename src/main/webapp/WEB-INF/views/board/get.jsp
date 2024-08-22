<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ include file="../includes/header.jsp" %>    
<!-- list.jsp에서 /board/register 경로 호출하면 get메서드 실행 폼 박스 출력 -->
<!-- 입력 완료 클릭 시 VO 객체 생성 후 /board/register 경로로 post 메서드 실행 -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 상세보기</h1>
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
	
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<div class="panel-body">
				<!-- <form role="form" action="/board/register" method="post"> -->
					<div class="form-group">
						<label>번호</label>
						<input class="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>Title</label> <input class="form-control" name="title" value='<c:out value="${board.title}" />' readonly="readonly"/>
					</div> <!-- title .form-group end -->
					
					<div class="form-group">
						<label>Content</label>
						<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
					</div> <!-- content .form-group end -->
					
					<div class="form-group">
						<label>Writer</label> <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly="readonly"/>
					</div> <!-- writer .form-group end -->
					
					<button data-oper='modify' class="btn btn-default">수정</button>
					<%-- onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'" --%>
					<!-- 링크 : /board/modify?bno=게시글 번호 -->
					<!-- <button data-oper='delete' class="btn btn-default">삭제</button> -->
					<button data-oper='list' class="btn btn-info" >목록</button>
					<!-- onclick="location.href='/board/list'" -->
					
					<form id='operForm' action="/board/modify" method="get" >
						<input type="hidden" id='bno' name='bno' value='<c:out value="${board.bno }"/>'>
					</form>
					
				<!-- </form> -->
			</div> <!-- .panel-body end -->
		</div> <!-- .panel panel-default end-->
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<script type="text/javascript">
$(document).ready(function(){
	var operForm = $("#operForm");
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action", "/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit();
	});
});
</script>
<%@ include file="../includes/footer.jsp" %>