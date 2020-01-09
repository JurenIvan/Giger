import React from "react";
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";
import Button from "react-bootstrap/Button";
import {Card} from "antd"

export default class bandMemberClass extends React.Component {
    constructor(props) {
        super (props);

        this.state = {
            memberName: "",
            contactNumber: "",
            backup: this.props.backup
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
                this.setState({memberName: json.name, contactNumber: json.contactNumber}, ()=> console.log(json))
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
            <p> Contact number: {this.state.contactNumber}</p>
            {this.state.backup?
                <Button onClick = {this.handleMakeMain}> Make main member</Button>
                :
                <Button onClick = {this.handleMakeBackup}> Make backup member</Button>
            }
            </Card>
        )
    }
}