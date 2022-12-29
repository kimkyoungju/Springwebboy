import React,{useState ,useEffect , useRef} from 'react';
import { useDaumPostcodePopup } from 'react-daum-postcode'; //npm install react-daum-postcode
import axios from 'axios';
import icon from '../../img/pngwing.png'

export default function RoomWrite(props) {
                /*카카오 주소api*/
 const [address ,setAddress]=useState({name: '' , lat : '', lng: ''});

 const open = useDaumPostcodePopup("//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");

  const handleClick = () => {open({ onComplete: handleComplete }); };

  const handleComplete = (data) => {


    //4.주소 --> 좌표변환
    axios.get("https://dapi.kakao.com/v2/local/search/address.json?query="+data.address
    ,{headers:{Authorization:'KakaoAK 449e7218d276ea80b7b68ca4db6da395' }})
    .then(res=>{
        const location = res.data.documents[0]
        console.log(location)
        console.log(location.x);
        console.log(location.y);
        //5. state 업데이트

        setAddress({name: data.address , lat : location.y , lng : location.x});
    })

   };// e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'

/*

    curl -v -X GET "https://dapi.kakao.com/v2/local/search/address.json" \
      -H "Authorization: KakaoAK ${REST_API_KEY}" \
      --data-urlencode "query=전북 삼성동 100" ;
*/



  /*-----------------카카오지도 api--------------------------------*/


  const mapContainer = useRef(null);
  const {kakao} = window;


 /* var mapContainer = document.getElementById('map'), // 지도를 표시할 div*/
    const mapOption = {
          center: new kakao.maps.LatLng(address.lat ,address.lng ), // 지도의 중심좌표
          level: 3 // 지도의 확대 레벨
      };

  useEffect(() => {

    //1. 해당 div에 옵션을 넣은 map객체 생성
    var map = new kakao.maps.Map(mapContainer.current , mapOption);
    //2.
            var markerImageUrl = 'http://localhost:8080/static/media/pngwing.842a759eda275ac82d16.png',
    		    markerImageSize = new kakao.maps.Size(40, 42), // 마커 이미지의 크기
    		    markerImageOptions = {
    		        offset : new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
    		    };
    		// 마커 이미지를 생성한다
    		var markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);

    		// 지도에 마커를 생성하고 표시한다
    		var marker = new kakao.maps.Marker({
    		    position: new kakao.maps.LatLng(address.lat, address.lng), // 마커의 좌표
    		    image : markerImage, // 마커의 이미지
    		    map: map // 마커를 표시할 지도 객체
        });
  })
      /*--------방등록 번튼을 눌렀을때------*/
    const onWrite =()=>{
        //1. 방이름 ,가격, 거래방식, 여러개 이미지 , 주소명 ,위도 ,경도 --> 컨트롤 로 -> 서비스
        let form = document.querySelector('.main');
        let formdata = new FormData(form);
        formdata.set("rname" , address.name);
        formdata.set("rlat" ,address.lat)
        formdata.set("rlng" ,address.lng)

        axios.post("/room/setroom" , formdata , {headers: {'Content-Type':'multipart/form-data'}})
            .then(re=>{
                if(re.data == true){alert("방등록성공"); window.location.href="/";}
                else{alert("방 등록 실패 ");}
            })

    }


    return(
            <>
                <h3> 방 등록 </h3>

                <form className="main">
                  방이름  :  <input type="text" name="rtitle" />
                  가격 :  <input type="text" name="rprice" />
                  거래방식 :
                    <select name="rtrans">
                        <option value="매매">매매</option>
                        <option value="전세">전세</option>
                        <option value="월세">월세</option>
                    </select>
                  이미지 : <input type="file" multiple="multiple" name="rimg" />
                  {/*카카오주소*/}
                  위치[좌표] :
                               <div>{address.name}</div>
                               <button type='button' onClick={handleClick}>
                                 방 위치 찾기
                               </button>
                  {/*카카오map*/}
                  {/*jsx 스타일링 : style={{속성:값 , 속성= 값,*/}
                        <div id="map"
                            ref={ mapContainer}
                            style={{width:'100%',height:'350px'}}>
                        </div>
                         <button type="button" onClick={onWrite}>등록</button>
                </form>

            </>
    )

}