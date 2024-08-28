/**
 *  댓글, AJAX 처리용 자바 스크립트 파일
 */

console.log("댓글용 모듈 실행중...........");

// 변수 생성 (replyService)

var replyService = (function() {
	function add(reply, callback, error) {
		console.log("댓글 추가용 함수.........")

		$.ajax({
			type: 'post',
			url: '/replies/new',
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				} // callback if 종료
			}, // success 종료
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				} // error if 종료
			} // error 종료			
		}) // $.ajax		
	} // function add(reply, callback) 종료

	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;

		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data) {
				if (callback) {
					// callback(data);
					callback(data.replyCnt, data.list);
				} 
		}).fail(function(xhr, status, err) {

				if (error) {
					error();
				} // if error

			}); // .fail function 
	}// getList function
	
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if(callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		});
	}
	
	function update(reply, callback, error) {
		console.log("RNO : " + reply.rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		});
	}
	
	function get(rno, callback, error) {
		$.get("/replies/" + rno + ".json", function(result){
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();	
			}
		});
	}
	
	function displayTime(timeValue) {
		var today = new Date();
		var gap = today.getTime() - timeVlaue;
		var dateObj = new Date(timeValue);
		var str="";
		
		if(gap < (1000 * 60 * 60 * 24)){
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return[ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].json('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return[yy, '/', (mm > 9 ? '' : '0') + mm,'/', (dd > 9 ? '' : '0') + dd].json('');
		}
	}
	;
	return {
		add: add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	};
	
	
})();

