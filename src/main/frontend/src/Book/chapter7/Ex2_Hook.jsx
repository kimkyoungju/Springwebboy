//p.226

import React , {useState , useEffect}from 'react';

//state 상태에 따른 온라인지 오프라인인지 텍스트로 보여주는 컴포넌트
/*
export default UserStatus(props) {

            //변수명 , 변경함수명                초기값
    const [ isOnline, setIsOnline ] =useState(null);

    //const isOnline = true; // [x] : 변수는 변하지만 변환 변수의 값을 재 렌더링 안됨 업데이트자동안됨

    //생명주기 : mount: 렌더링후 1번 , update: 재렌더링마다 ,numount: 렌더링
    useEffect(()=>{ //state ,props 사용가능
        function handleStateChange(state) {setIsOnline(state.isOnline);}
        ServeAPI.subsribeUserStatus(props.user.id, handleStateChange);
        return ()=>{ ServeAPI.unsubsribeUserStatus(props.user.id, handleStateChange);}
    })
    //1.
    if(isOnline == null){return '대기중...';}
    return isOnline ? '온라인' : '오프라인';

    //2.
    return(
        <li style={{color : isOnline ?'green' : 'black'}}>
                {props.user.name}
        </li>

    )

}*/
const userList = [{id:1, name : 'Inje'},{id:1, name : 'kim'},{id:1, name : 'steve'}]

function chatuserStatus(props){
    const [userId , setUserId] = useState(1); // select 가 체인지 될때마다 회원 아이디 변경
    const isOnline = useUserStatus(userId); // 해당 유저의 온라인 상태여부 가져온다
}



//1. 커스텀 훅 [여러 컴포넌트에서 동일한 훅을 사용시 = 재사용성]
function useUserStatus(userId) {
    const [ isOnline, setIsOnline ] =useState(null);

    useEffect(()=>{ //state ,props 사용가능
        function handleStateChange(state) {setIsOnline(state.isOnline);}
        ServeAPI.subsribeUserStatus(props.user.id, handleStateChange);
        return ()=>{ ServerAPI.unsubscribeUserStatus(props.user.id, handleStateChange);}
    })
    return isOnline
}

//2.온라인 상태를 문자로 표시하는 컴포넌트
function UserStatus(props){
    const isOnline = useUserStatus(props.user.id)
    if(isOnline == null){return '대기중..';}
    return isOnline ? '온라인' : '오프라인';
}
//3. 온라인 상태를 색상으로 표시하는 컴포넌트

function UserListItem(props){
    const isOnline = useUserStatus(props.user.id)
    return( <li style={{color : isOnline ? 'green' : 'black'}}>{props.user.name}</li>)
}


