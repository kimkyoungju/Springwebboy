

//1. 게시물 출력
boardlist()
function boardlist(){

    $.ajax({
    url : "/board/boardlist",
    type : "get",
    success : function(list){
     let html = '<tr>  <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th></tr>';
                list.forEach( (b) => {
                    html +=
                    '<tr>  <td> '+b.bno+' </td> <td onclick="getview('+b.bno+')">'+b.btitle+' </td> <td> '+b.memail+' </td></tr>';
                })
                document.querySelector(".btable").innerHTML = html;

    }

    })
}
//2. 게시물 조회 페이지[페이지 전환 -> 클릭한 게시물 번호 기록(java(세션 - 서버가 종료될때까지) , 템플릿 ,js(페이지 전환시 초기화/세션, 쿠키)]

function getview(bno){

//1. 클릭한 게시물 번호 저장
//localStorage.getItem
sessionStorage.setItem("bno",bno);

//2. 페이지 전환
location.href="/board/view";
}