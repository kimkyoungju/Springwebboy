//1. 세션 스토리지 호출

let bno = sessionStorage.getItem("bno");

getboard()
//2. 클릭된 게시물 번호의 게시물 정보를 호출하는 메드스
function getboard(){
$.ajax({
    url: "/board/getboard",
    type: "GET",
    data : {"bno": bno},
    success: function(re){
    console.log(re);
        }
    })
}
//3. 수정버튼 클릭시 호출되는 메소드
function upboard(){
    let data = {
        btitle :  document.querySelector(".btitle").value ,
        bcontent :  document.querySelector(".bcontent").value,
        bfile : document.querySelector('.bfile').value,
        bno : bno
    }
      $.ajax({
        url : "/board/upboard",
        type : "put",
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re==true){
                alert("글 수정 성공")
                location.href="/board/view";
                }
            else{alert("글 수정 실패");}
            }

        })

}