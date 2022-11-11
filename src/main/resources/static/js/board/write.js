alert("ddd")



function setboard(){

let data = {
    btitle :  document.querySelector(".btitle").value
    bcontent :  document.querySelector(".bcontent").value



}


    $.ajax({
    url : "/board/setboard",
    type : "POST",
    data : data,JSON.stringify(data),
    contentType : "application/json"
    success : re=>{alert(re)}

    })
}