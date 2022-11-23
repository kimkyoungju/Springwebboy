



const domContainer = document.querySelector("#root")
console.log(domContainer)
//2. 리액트 렌더링[render( 위치) , 넣어주는것 ]
ReactDOM.render(React.createElement(mybutton),domContainer)

//3.mybutton 생성함수
function mybutton (props) {

    const[isClicked, setIsClicked] = React.useState(false);

    return React.createElement(
        'button', // 태그명
        {onClick: () => setIsClicked(true)}, // 옵션 : 이벤트
        isClicked ? 'Clicked!' : 'Click here!' // value값
    )
}

