import React from 'react'

export default function BoardWrite(props) {

const setbcategory = () => {alert("카테고리 추가합니다.");
let data = { bcname : document.querySelector(".bcname").value}
/*
    axios
        .post("/board/setbcategory")
        .then((response) => {setbcategory(response.data);})
        .catch((error) => {alert(error)})*/
}

const setboard = () => {alert("게시물 추가합니다.");}
    return(
        <div className>
         <h1>글쓰기페이지</h1>

            <input type="text" className="bcname" />
            <button type="button"onClick={setbcategory}>카테고리추가</button>
            <div className="bcategorybox"></div>

            <form className="boardform">
                제목 : <input type="text" name="btitle" />
                내용 : <input type="text" name="bcontent" />
                첨부파일 : <input type="file" name="bfile" />
                <button type="button" onClick={setboard}>등록</button>

            </form>
        </div>


    );
}