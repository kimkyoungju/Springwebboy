/*




function getmember(){


    let check={
        memail: document.querySelector(".memail").value,
        mpassword: document.querySelector(".mpassword").value
    }
    let html = ''
    console.log(check)
    $.ajax({
    url : "/member/getmember",
    type : "post",
    data : JSON.stringify(check),
    contentType: "application/json",
    success: function(re){
        if(re==1){
        alert("로그인성공")
        }
    }

    })

}





 // document.querySelector(".insertemail").innerHTML = "이메일 입력 : <input type="text" class="findpa">"

*/
