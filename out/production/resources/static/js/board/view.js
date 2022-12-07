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
//3. 삭제버튼 ㅡ클릭시 호출되는 메소드
function delboard(){
$.ajax({

   url: "/board/delboard",
    type: "delete",
    data : {"bno": bno},
    success: function(re){
    location.href="/board/list"
      }
    })
}