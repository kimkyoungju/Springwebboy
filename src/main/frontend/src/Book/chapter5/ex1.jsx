//1. props 사용법

function App(props){

    //1.<컴포넌트명 />
    //2.<컴포넌트명 속성명="문자열" , 속성명={숫자}>
    //props.속성명
    return (
          <Profile
              name="소플"
              introduction="안녕하세요 , 소플입니다"
              viewCount={1500}
          />
    ); //return end
}//end function

//2.
function App(props){

    return(
        <Layout
            width={2560}
            height={1440}
            header={<Header title="소플의 블로그입니다."/>} //layout 컴포넌트에 header 컴포넌트 전달
            footer={<Footer/>} //layout 컴포넌트에 footer 컴포넌트 전달
        />

    )
}