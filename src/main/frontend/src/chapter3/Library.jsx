//p109
import React from 'react'; //1. 리액트 라이브러리
import Book from"./Book"; //2. 북 컴포넌트


//함수
function Library(props) {
    return(
        <div>
               <Book name="처음 만난 김경주" numOfPage={300}/>
               <Book name="처음 만난 안태섭" numOfPage={400}/>
               <Book name="처음 만난 심영섭" numOfPage={300}/>
               <Book name="처음 만난 조영웅" numOfPage={500}/>

        </div>
    )//return end
}

//함수 내보내기

export default Library;
