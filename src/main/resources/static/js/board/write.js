

function setboard(){

let data = {
    btitle :  document.querySelector(".btitle").value ,
    bcontent :  document.querySelector(".bcontent").value,
    bfile : document.querySelector('.bfile').value
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
function categoryadd{
let html = ''
html += '카테고리 선택 :<input type="checkbox" class="add">'

document.querySelector(".checkcategory").innerHTML = html;

/*
    $.ajax({
    url : "/board/categoryadd",
    type : "get",
    data : JSON.stringify(add.value),
    success : function(re){
    alert(re)
    }

    })*/



}