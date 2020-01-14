import React from "react";
import {Row, Col} from "react-bootstrap";
import {Card, Avatar} from "antd"

 
export default class BasicProfile extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: this.props.name,
            pictureUrl: this.props.pictureUrl
        }
    }

    componentWillReceiveProps(nextProps){
        this.setState({name: nextProps.name, pictureUrl: nextProps.pictureUrl})
    }

    render() {
        console.log(this.props)
        console.log(this.state)
        return(
            <Card 
            style={{ width: 300 }}
            cover={
                <img
                  alt="Profile picture"
                  src={this.state.pictureUrl}
                />
                }
            >
               <h1>
                   {this.state.name}
               </h1>
            </Card>
        )
    }
}