<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ include file="../includes/header.jsp" %>    
<!-- list.jsp에서 /board/register 경로 호출하면 get메서드 실행 폼 박스 출력 -->
<!-- 입력 완료 클릭 시 VO 객체 생성 후 /board/register 경로로 post 메서드 실행 -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 등록 페이지</h1>
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
	
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Register</div>
			<div class="panel-body">
				<form role="form" action="/board/register" method="post">
					<div class="form-group">
						<label>Title</label> <input class="form-control" name="title" />
					</div> <!-- title .form-group end -->
					
					<div class="form-group">
						<label>Content</label>
						<textarea class="form-control" rows="3" name="content" ></textarea>
					</div> <!-- content .form-group end -->
					
					<div class="form-group">
						<label>Writer</label> <input class="form-control" name="writer" />
					</div> <!-- writer .form-group end -->
					
					<button type="submit" class="btn btn-default">저장</button>
					<button type="reset" class= "btn btn-default">초기화</button>	
					
				</form>
			</div> <!-- .panel-body end -->
		</div> <!-- .panel panel-default end-->
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
<%@ include file="../includes/footer.jsp" %>