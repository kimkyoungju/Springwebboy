import React from 'react'

class Ex1_Event extends React.Component {

        constructor(props) {
            super(props)
            this.state = {isToggle: true};
            this.handleClick = this.handleClick.bind(this);

        }

        handleClick(){
            this.setState(prevState=>({
                    isToggle: !prevState.isToggle
             }));
        }
        //------------------------------------------
        render(){
                return (
                    <button onClick={this.handleClick}>
                        {this.state.isToggle? '켜짐' : '꺼짐'}
                    </button>
                );
        }
}

export default Ex1_Event;