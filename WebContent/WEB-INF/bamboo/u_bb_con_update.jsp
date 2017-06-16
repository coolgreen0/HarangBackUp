<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/header.jsp"%>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<title>대나무숲</title>


<script>
	
	function fnGoback(){
		
		document.gobocktocontent.submit();
	}
	
	
</script>


</head>
<!-- 메인 페이지 구역 , 즉 작업 구역 -->
<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>
			대나무숲 <small>학생이 대숲 글 수정하는 곳</small>
		</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
			<li class="active">대나무숲</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<form action="/HarangProject/bamboo" name="bbpostcomplete"
		method="post">
		<input type="hidden" name="cmd" value="U_BB_CONUP_COMPLETE"> <input
			type="hidden" name="bb_num" value="${bbcon.bb_num }">

		<section class="content">
			<!-- 세로 길이 수정 -->
			<div class="row">
				<!-- 너비 사이즈 수정  : col-->
				<div class="col-md-9">

					<div class='box box-info'>
						<div class='box-header'>
							<h3 class='box-title'>대나무숲 글 수정</h3>
							<br> <br>
							<!-- tools box -->

							<div class="form-group">
								<label>제목</label> <input type="text" class="form-control"
									value="${bbcon.bb_title }" name="bb_title" />
							</div>

							<div class="form-group">
								<label>닉네임</label> <input type="text" class="form-control"
									value="${bbcon.bb_nickname }" name="bb_nickname" />
							</div>








						</div>
						<!-- /.box-header -->





						<div class='box-body pad'>

							<textarea id="editor1" name="bb_content" rows="10" cols="80">
                                            ${bbcon.bb_content }
                    </textarea>
						</div>

						<div class="row">
							<div class="col-md-4"></div>
							<div class="col-md-2">
								<button type="submit" class="btn btn-block btn-primary">수정</button>
							</div>
							<div class="col-md-2">
								<a class="btn btn-block btn-danger" href="javascript:fnGoback()">취소</a>
							</div>
						</div>






					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</section>
	</form>

	<!--  대나무숲 글 수정하기 페이지에서 취소를 눌렀을 때 원래 페이지로 돌아가기 위한 폼 시작 -->
	<form method="post" action="/HarangProject/bamboo"
		name="gobocktocontent">
		<input type="hidden" name="bb_num" value="${bbcon.bb_num}" /> <input
			type="hidden" name="cmd" value="U_BB_CON" />

	</form>
	<!--  대나무숲 글 수정하기 페이지에서 취소를 눌렀을 때 원래 페이지로 돌아가기 위한 폼 끝 -->
	<!-- /. 작업 공간 끝! -->
	<!------------------------------------------------------------------------------------------------------------------->
</div>
<!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->

<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>

<!-- CK Editor -->
<script src="//cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
<!-- jQuery 2.1.3 -->
<script src="../../plugins/jQuery/jQuery-2.1.3.min.js"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="../../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- FastClick -->
<script src='../../plugins/fastclick/fastclick.min.js'></script>
<!-- AdminLTE App -->
<script src="../../dist/js/app.min.js" type="text/javascript"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js" type="text/javascript"></script>
<!-- CK Editor -->
<script src="//cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script
	src="../../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('editor1');
		//bootstrap WYSIHTML5 - text editor
		$(".textarea").wysihtml5();
	});
</script>
