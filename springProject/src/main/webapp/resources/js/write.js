//모든 요소에 대해 자동 검색 비활성화
	Dropzone.autoDiscover = false;
	
	var dropzoneUploader = new Dropzone("form#uploader",{
		init: function(){
			var dropzone = this;
			
			$("#upload").click(function(){
				/*
			 autoProcessQueue: 
			 공식 설명 
			 [false이면 파일이 대기열에 추가되지만 대기열이 자동으로 처리되지 않습니다.
			    파일을 보내기 전에 추가 사용자 입력이 필요한 경우 (또는 모든 파일을 한 번에 보내려는 경우) 
			    유용 할 수 있습니다. 파일을 보낼 준비가 되었으면 myDropzone.processQueue().]
			 해설: autoProcessQueue가 false로 설정된 경우 파일이 대기열에 추가되지만 자동으로 올라가지 않는다.
			 서버로 파일을 보내기 전 같이 보낼 다른 입력이 있을 경우 유용하다. 모두 보낼 준비가 되면 processQueue()를 사용.
				 */
				dropzoneUploader.processQueue();
			});
			
			dropzone.on("sending", function(file, xhr, formData){
				formData.append("contents",$("#contents").val());
			});
		}, 
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		paralleUploads: 10,
		autoProcessQueue: false,
		url: "/main/write",
		type: "POST",
		success: function(){
			console.log("post write 성공");
			location.href = '/main/mainHome';
		},
		error: function(e){
			console.log("dropzone파일 업로드 error");
			console.log(e);
			alert("에러가 발생했습니다. 다시 시도해주세요.");
			location.reload();
		},
		acceptedFiles: ".jpeg,.jpg,.png,.gif",
		uploadMultiple: true,
	});
	
