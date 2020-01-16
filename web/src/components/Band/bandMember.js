import React from "react";
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";
import Button from "react-bootstrap/Button";
import {Card} from "antd"
import { Avatar } from 'antd';

export default class BandMemberClass extends React.Component {
    constructor(props) {
        super (props);

        this.state = {
            memberName: "",
            contactNumber: "",
            backup: this.props.backup,
            pictureUrl: ""
        }

        this.handleMakeBackup = this.handleMakeBackup.bind(this);
        this.handleMakeMain = this.handleMakeMain.bind(this);
    }

    componentDidMount() {
        console.log("Inside component did mount");
        fetcingFactory(endpoints.GET_MUSICIAN_BASIC, this.props.memberId).then(
            response => response.json()
        ).then(
            json => {
                console.log("!!!!!!!!!!!")
                this.setState({memberName: json.name, contactNumber: json.contactNumber, pictureUrl: json.pictureUrl}, ()=> console.log(json))
            }
        )
    }

    handleMakeBackup() {

    }

    handleMakeMain() {

    }

    render() {
        return (
            <Card title = {this.state.memberName}>
                <Avatar size = {64} src = {this.state.pictureUrl} />
            <p> Contact number: {this.state.contactNumber}</p>
            {this.state.backup?
                <Button 
                onClick = {this.handleMakeMain}
                style ={{
                    fontFamily: 'sans-serif',
                    fontSize: "14px", 
                    fontWeight: "bold",
                    background: "#19aa8d",
                    borderRadius: "3px",
                    border: "none",
                    minWidth: "140px",
                    maxWidth: "100%",
                    outline: "none"
                }}> Make main member</Button>
                :
                <Button 
                onClick = {this.handleMakeBackup}
                style ={{
                    fontFamily: 'sans-serif',
                    fontSize: "14px", 
                    fontWeight: "bold",
                    background: "#19aa8d",
                    borderRadius: "3px",
                    border: "none",
                    minWidth: "140px",
                    maxWidth: "100%",
                    outline: "none"
                }}> Make backup member</Button>
            }
            </Card>
        )
    }
}