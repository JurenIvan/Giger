import React from "react";
import {Carousel, Card, Avatar} from "antd";
import "./DisplayInstruments.css"


export default class DisplayInstruments extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            instrumentIds: this.props.instrumentIds,
            instrumentList: []
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps) {
            this.setState({instrumentIds: nextProps.instrumentIds});
        }
    }

    render() {
        return (
            <Carousel>
               {
                this.state.instrumentIds?
                this.state.instrumentIds.map(element => {
                   return (
                       <Card title = {element.name}>
                           <Avatar shape="square" size={64} src={element.pictureUrl}/>
                       </Card>
                   )
               }): <div> empty </div>
               }
            </Carousel>
        )
    }
}