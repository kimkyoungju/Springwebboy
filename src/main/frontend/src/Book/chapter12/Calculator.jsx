/* 1.임포트 */
import React,{useState} from 'react';
import TemperatureInput from './Temperatureinput'
/*-------*/

/* 2.전역변수 */
//1.컴포넌트
function BoilingVerdict(props) {
    if(props.celsius>=100){return <p>물이 끓습니다</p>;}
    return<p>물이 끓지 않습니다.</p>

}
//2.매개변수 화씨
function toCelsius(fahrenheit){
    return ((fahrenheit - 32) *5 /9);
}
//3. 섭씨 -> 화씨
function toFahrenheit(celsius){
    return (celsius * 9)/ 5+ 32;
}

//4.
function tryConvert(temperature ,convert){
    const input = parseFloat(temperature);
    if(Number.isNaN(input)){return "";}

    const output = convert(input);
    const rounded = Math.round(output*1000)/1000;
    return rounded.toString();
}

/*--------- */

// 3.컴포넌트[함수] 만들기
export default function Calculator( props ){

    /* ------------ 4.js , react[ 함수 , 지역변수]  -------------------*/
        const [temperature ,setTemperature] = useState("");
        const [scale,setScale] = useState("c");
    /* ------------------------------------------ */
    const handleCelsiusChange =(t) =>{setTemperature(t); setScale("c");}
    const handleFahrenheitChange =(t) =>{setTemperature(t); setScale("f");}

    const fahrenheit = scale == "c" ? tryConvert(temperature , toCelsius) : temperature;
    const celsius = scale =="f"? tryConvert(temperature , toFahrenheit) : temperature;
    /* ------------ 5. html or jsx표현식 { }------------------*/
    return(
        <div>
            <TemperatureInput
                scale = "c"
                temperature ={celsius}
                onTemperatureChange ={handleCelsiusChange}
            />
              <TemperatureInput
                     scale = "f"
                     temperature ={fahrenheit}
                     onTemperatureChange ={handleFahrenheitChange}
              />
              <BoilingVerdict celsius ={parseFloat(celsius)} />
        </div>
    )
    /* -------------------------------------------*/
}
