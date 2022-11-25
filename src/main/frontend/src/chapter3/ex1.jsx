

//리액트 사용하기 전
const name ="소플" //js  문자변수
const element = '<h1>안녕, '+name+'</h1>'; // js+ html
document.querySelector("#root").innerHTML = element



//리액트 사용
//p.104
const name ="소플" //js  문자변수
const element = <h1>안녕, {name}</h1>; // js+ html
    //jsx 변수 호출 표현식 : {변수명}



//ReactDOM.render(요소변수 , dom컨테이너)
ReactDOM.render(element ,document.querySelector("#root");


//------------------------함수표현식
// 함수
function formatName(user){

    return user.firstName +' '+ user.lastName;
}

// 유저 객체
const user = {firstName : 'inje' ,lastName : 'Lee'}

//html 구성
const element = (<h1>Hello ,{formatName(user)}</h1>)

//해당 id에 html 랜더링(뿌려주기\넣어주기\표시하기)
ReactDOM.render(element,document.querySelector("#root"))

//---------------활용------------------

function getGreeting(user){




    if(user){
    return <h1> hello ,{formatName(user)}!</h1>// 유저가 있을경우
    }
    return <h1>hello ,Straner</h1>

}

// jsx 식 html
const element = (<div> <h1> 안녕하세요</h1> <h2>열심히 리액트할까요?</h2></div>)
const element = '<div> <h1> 안녕하세요</h1> <h2>열심히 리액트할까요?</h2></div>'



