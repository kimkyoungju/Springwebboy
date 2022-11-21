
let bcno = 2; // 카테고리 번호 전역변수
function setboard(){

let data = {
    btitle :  document.querySelector(".btitle").value ,
    bcontent :  document.querySelector(".bcontent").value,
    bfile : document.querySelector('.bfile').value,
    bcno : bcno
}
  $.ajax({
    url : "/board/setboard",
    type : "post",
    data : JSON.stringify(data),
    contentType : "application/json",
    success : function(re){
        if(re==true){
            alert("글작성성공")
            location.href="/board/list"
            }
        else{alert("글작성실패");}
        }

    })
}


function setbcategory(){
    let data = { bcname : document.querySelector(".bcname").value}
    $.ajax({
        url : "/board/setbcategory",
        type : "post",
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
          if( re == true){ alert('카테고리추가성공'); bcategorylist();}
                     else{ alert('카테고리추가실패')}
        }
    })

}


//3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
    url : "/board/bcategorylist",
    type : "get",
    success : function(re){
    let html ="";
    re.forEach(c=>{
    html += '<button type ="button" class="cbtn" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>'
        console.log(c)
                })
                document.querySelector(".bcategorybox").innerHTML = html
                cbtn = document.querySelectorAll(".cbtn") // 카테고리 버튼들을 호출

        }
    })
}

//4. 카테고리를 선택했을때 선택된 카테고리 번호 변경

function bcnochage(cno){
bcno = cno
alert(bcno +"의 카테고리 선택")
}








