//300 리스트와 키
import React from 'react';

const students = [ { name: "Inje"} , { name: "Steve"} , { name:"Bill"} , { name:"Jeff"}]

export default function AttendanceBook(props){
    //jsx 표현식 {js코드}

     return(
                <ul>
                    {
                        students.map((s) =>{return <li>{s.name}</li>;})
                    }
                </ul>
            )
}