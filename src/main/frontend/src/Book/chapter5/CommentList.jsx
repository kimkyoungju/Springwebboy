
import React from 'react'
import Comment from './Comment'

//1. 데이터 리스트[ 서버통신과 통신된 결과물]
const Comments = [ // 댓글 3개 객체를 저장하는 리스트 객체
    { //댓글 1
        name: '김경주',
        comment : "안녕하세요 김경주입니다."
    },
    {   //댓글2
      name: '안태섭',
      comment : "집가고 싶어요."
    },
    { //댓글3
      name: '강호동',
      comment : "잘가세요 태섭씨."
    }
];

function CommentList(props){
    return (
        <div>
            {Comments.map((c)=>{
                return(
                    <Comment name={c.name} comment={c.comment}/>
                );

            })
            }

         </div>
        );
    }
export default CommentList;