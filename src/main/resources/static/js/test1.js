  alert("햄볶하쟝 우리 햄뽁하쟝")
  let member = {
      name : "김경주",
      email : "비밀",
      organization : "qwqe"
  }

  function GetMapping1() {

            $.ajax({
            url: "http://192.168.17.46:8080/api/v1/get-api/hello",

            type : "GET",
            success : re=>{
           alert(re)}


            })
        }

        function GetMapping2() {

            $.ajax({
           url: "http://192.168.17.46:8080/api/v1/get-api/name",
            type : "GET",
            success : re=>{alert(re)}
            })
        }

        function GetMapping3() {

            $.ajax({
           url: "http://192.168.17.46:8080/api/v1/get-api/variable1/{안녕}",
            type : "GET",
            success : re=>{alert(re)}
         })
         }
         function GetMapping4() {

                     $.ajax({
                     url: "http://192.168.17.46:8080/api/v1/get-api/variable2/{ㅋㅋ}",
                     type : "GET",
                     success : re=>{alert(re)}
                  })
          }
          function GetMapping5() {

                               $.ajax({
                               url: "http://192.168.17.46:8080/api/v1/get-api/variable3?variable=테스트모드",
                               type : "GET",
                               success : re=>{alert(re)}
                            })
                    }
        function GetMapping6() {

                                       $.ajax({
                                       url: "http://192.168.17.46:8080/api/v1/get-api/requst1?name=qwe&email=qwe&organization=qwe",
                                       type : "GET",
                                       success : re=>{alert(re)}
                                    })
                            }
        function GetMapping7() {

                        $.ajax({
                         url: "http://192.168.17.46:8080/api/v1/get-api/requst2?<key1=180&key2=qwe&key3=qwe>",
                        type : "GET",
                        success : re=>{alert(re)}
                          })
          }

         function GetMapping8() {

                                       $.ajax({
                                       url: "http://192.168.17.46:8080/api/v1/get-api/requst3?name=김경주&email=비밀&organization=뭔데",
                                       type : "GET",
                                       success : re=>{alert(re)}
                                    })
                            }



function postMapping1(){
    $.ajax({
    url: "/api/v1/post-api/domain",
    type : "post",
    success : function(re){alert(re)}

    })
}

function postMapping2(){

    $.ajax({
    url: "http://192.168.17.46:8080/api/v1/post-api/member",
    type : "post",
    data : JSON.stringify(member),
    contentType : "application/json",
    success : function(re){alert(re)}

    })
}

function postMapping3(){
let member = {
    name : "김경주",
    email : "비밀",
    organization : "qwqe"
}
    $.ajax({
    url: "http://192.168.17.46:8080/api/v1/post-api/member2",
    type : "post",
    data : JSON.stringify(member),
    contentType : "application/json",
    success : function(re){alert(re)}

    })
}

/*===============================================================put=========================*/
function putMapping1(){
$.ajax({
    url: "http://192.168.17.46:8080/api/v1/put-api/member",
    type : "put",
    data : JSON.stringify(member),
    contentType : "application/json",
    success : function(re){alert(re)}

    })
}
function putMapping2() {
$.ajax({
    url: "http://192.168.17.46:8080/api/v1/put-api/member1",
    type : "put",
    data : JSON.stringify(member),
    contentType : "application/json",
    success : function(re){alert(re)}

    })


}
function putMapping3() {
$.ajax({
    url: "http://192.168.17.46:8080/api/v1/put-api/member2",
    type : "put",
    data : JSON.stringify(member),
    contentType : "application/json",
    success : re=>{
    console.log(re);
    console.log(re.name);

    //파싱할 필요가 없다
    //let json = JSON.parse(re);
    //console.log(json);
    }
  })


}

function deletemapping1(){

    $.ajax({
    url : "http://192.168.17.46:8080/api/v1/delete-api/지우고 싶구나?",
       type : "delete",
       success : re=>{

       alert(re);}

})

}
function deletemapping2(){

    $.ajax({
    url : "http://192.168.17.46:8080/api/v1/delete-api/request1?variable=두번이나 눌러?",
       type : "delete",
       success : re=>{

       alert(re);}

})

}




