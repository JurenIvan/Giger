import React from "react";
import {Carousel, Card, Avatar} from "antd";

import * as Helper from "../../Utils/HelperMethods";
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
            this.setState({instrumentIds: nextProps.instrumentIds}, () => {
                if (this.state.instrumentIds) {
                    let list = Helper.getInstrumentList(this.state.instrumentIds);
                    this.setState({instrumentList: list}, () => console.log(this.state.instrumentList, list));
                }
            });
        }
    }

    render() {
        return (
            <Carousel>
               {
                this.state.instrumentList?
                this.state.instrumentList.map(element => {
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