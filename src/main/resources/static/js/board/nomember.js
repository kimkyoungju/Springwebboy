function nadd(){

let data  = {
ncategory : document.querySelector(".ncategory").value,
 ncontent : document.querySelector(".ncontent").value
}
console.log(data)


$.ajax({
    url : "/board/nomemberwrite",
    type : "post",
    data : JSON.stringify(data),
    contentType : "application/json",
    success : function(re){
    alert(re)
    }
})

}