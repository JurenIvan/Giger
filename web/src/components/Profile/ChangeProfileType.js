import * as React from "react";
import { Button, Row } from "react-bootstrap";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import ProfileSideNav from "./ProfileSideNav";
import { Card, Switch} from "antd"

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
            organiserName: ""
        }

        this.handleChangeOrganiser = this.handleChangeOrganiser.bind(this);
        this.handleChangeMusician = this.handleChangeMusician.bind(this);
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

    handleChangeOrganiser(checked){
        this.setState({isOrganiser: checked})
    }
    handleChangeMusician(checked) {
        this.setState({isMusician: checked})
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
        const checkboxOrg = () => {
            return (
                <Switch onChange={(e) => {this.handleChangeOrganiser(e.currentTarget.checked)}}
                        checked = {this.state.isInitOrganiser || this.state.isOrganiser} 
                        disabled = {this.state.isInitOrganiser}>
                </Switch>
            )
        }
        const checkboxMus = () => {
            return (
                <Switch onChange={(e) => {this.handleChangeMusician(e.currentTarget.checked)}}
                        checked = {this.state.isInitMusician || this.state.isMusician} 
                        disabled = {this.state.isInitMusician}>
                </Switch>
                )
            }
        return (
            <React.Fragment>
            <ProfileSideNav />
            <Card
            title = "I am organizer"
            style = {{width: 450}}
             loading = {this.state.isInitOrganiser && !this.state.isOrganiser}
             extra = {checkboxOrg()}
             >
                <input type="text" value = {this.state.organiserName} onChange = {(e => {this.setState({organiserName: e.currentTarget.value})})} placeholder="Organiser name"></input>
                <div>
                    <Button block onClick={this.handleOrganiserCreate}>Confirm</Button>
                </div>
            </Card>

            <Card
            title = "I am musician"
            style = {{width: 450}}
             loading = {this.state.isInitMusician || this.state.isMusician}
             extra = {checkboxMus()}
             >
                 <div>
                    <Row>
                        <textarea type="text" placeholder="Biography" value = {this.state.musicianBio} onChange = {(e) => {this.setState({musicianBio: e.currentTarget.value})}}/>
                    </Row>  
                    <Row>
                        <label>My calendar will be public: </label>
                        <input type="checkbox"></input>
                        <Button block onClick = {this.handleMusicianCreate}>Confirm</Button>
                    </Row>   
                </div>
            </Card>
           
            </React.Fragment>
        )
    }
}