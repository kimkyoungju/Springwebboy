

nlist()
let nno = 2;
function nadd(){



    let data  = {
    ncategory : document.querySelector(".ncategory").value

        }


    console.log(data)


    $.ajax({
        url : "/Nmember/nomemberwrite",
        type : "post",
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
        if(re==true){
         alert("카테고리등록완료")
        }
    }
    })

}


function nview(){
alert("ddd")
    let fomd = document.querySelector(".formd");
    let formdata = new FormData(fomd);
    formdata.set("nno" ,nno)
      $.ajax({
        url : "/Nmember/nwrite",
        data : fomd,
        type : "post",

        contentType : false,
        processData : false,
        success : function(re){
        if(re==true){
            alert("방문록등록완료")
            }
         }
    })
}



function nlist(){
let html = '<tr>  <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th></tr>';
        $.ajax({
            url : "/Nmember/clist",
            type : "get",
            success : function(re){
             console.log(re)
                 re.forEach(c=>{
             html += '<button type="button" onclick="nnoch('+c.nno+')">'+c.ncategory+'</button>'
                })
                 document.querySelector(".nwi").innerHTML = html
           }
        })
}

function nnoch(nno){
    nno = nno
    alert(nno)
}

