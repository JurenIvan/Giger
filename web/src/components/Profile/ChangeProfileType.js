import * as React from "react";
import {  Row } from "react-bootstrap";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import ProfileSideNav from "./ProfileSideNav";
// eslint-disable-next-line
import { Card, Switch, Input, Button} from "antd"

export default class ChangeProfileType extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isOrganiser: false,
            isMusician: false,
            isInitOrganiser: false,
            isInitMusician: false,
            musicianPublicCalendar: false,
            musicianBio: "",
            organiserName: "",
            postContent: ""
        }

        
        this.handleMusicianCreate = this.handleMusicianCreate.bind(this);
        this.handleOrganiserCreate = this.handleOrganiserCreate.bind(this);
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_ROLES).then(
            response => {
                if (response.ok) {
                    return response.json()
                } else {
                    alert("Something went wrong!");
                }
            }
            ).then(
                json => {
                    if(json) {
                        console.log(json)
                        for (let i = 0; i < json.length; i++) {
                            if (json[i] === "MUSICIAN") {
                                this.setState({isInitMusician: true})
                            } else if (json[i] === "ORGANIZER") {
                                this.setState({isInitOrganiser: true})
                            }
                        }
                    }
                }
            )
    }

    handleMusicianCreate() {
        let params = {
            "bio": this.state.musicianBio,
            "publicCalendar": this.state.musicianPublicCalendar,
            "instrumentids" : []
        }
       
        fetcingFactory(endpoints.CREATE_MUSICIAN, JSON.stringify(params)).then(response => {
            if (response.ok) {
                alert("Musician created!")
            } else {
                alert("Musician creation failed!")
            }
        })
    }
    handleOrganiserCreate() {
        fetcingFactory(endpoints.CREATE_ORGANIZER, this.state.organiserName).then(
            response => {
                if(response.ok) {
                    alert("Organizer created!");
                } else {
                    alert("Organizer creation failed!")
                }
            }
        )
    }

    render() {
        return (
            <React.Fragment>
            <ProfileSideNav />
            <Card
            title = "I want to make an organizer profile"
            style = {{width: 450}}
             >  
            {this.state.isInitOrganiser?[
                 <div>
                     <p>You are already an organizer</p>
                 </div>
             ]:[
                 <div>
                    <Input type="text" value = {this.state.organiserName} onChange = {(e => {this.setState({organiserName: e.currentTarget.value})})} placeholder="Organizer name"></Input>
                    <br></br><br></br>
                    <div>
                        <Button block 
                        onClick={this.handleOrganiserCreate}
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
                        }}
                        >Confirm</Button>
                    </div>
                 </div>
             ]}
            </Card>
            <Card
            title = "I am a musician"
            style = {{width: 450, marginTop: 10}}
            >
            {this.state.isInitMusician?[
                <div>
                    <p>You are already a musician</p>
                </div>
            ]: [
                 <div>
                    <Row>
                        <textarea type="text" placeholder="Biography" value = {this.state.musicianBio} onChange = {(e) => {this.setState({musicianBio: e.currentTarget.value})}}/>
                    </Row>  
                    <Row>
                        <label>My calendar will be public: </label>
                        <input type="checkbox"></input>
                        <Button
                        block 
                        onClick = {this.handleMusicianCreate}
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
                        }}>Confirm</Button>
                    </Row>   
                </div>
            ]}
            </Card>
           
            </React.Fragment>
        )
    }
}