import * as React from "react";
import { Button, Row } from "react-bootstrap";
import fetchingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";

export default class ChangeProfileType extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isOragniser: false,
            isMusician: false,
            musicianPublicCalendar: false,
            musicianBio: "",
            organiserName: ""
        }

        this.handleChangeOrganiser = this.handleChangeOrganiser.bind(this);
        this.handleChangeMusician = this.handleChangeMusician.bind(this);
        this.handleMusicianCreate = this.handleMusicianCreate.bind(this);
        this.handleOrganiserCreate = this.handleOrganiserCreate.bind(this);
    }

    handleChangeOrganiser(checked){
        this.setState({isOragniser: checked})
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
       
        fetchingFactory(endpoints.CREATE_MUSICIAN, JSON.stringify(params)).then(response => {
            if (response.status === 200) {
                alert("Musician created!")
            } else {
                alert("Musician creation failed!")
            }
        })
    }
    handleOrganiserCreate() {
        fetchingFactory(endpoints.CREATE_ORGANIZER, this.state.organiserName).then(
            response => {
                if(response.status === 200) {
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
            <div>
                <div className="row">
                    <input type="checkbox" checked={this.state.isOragniser} onChange={(e) => this.handleChangeOrganiser(e.currentTarget.checked)}/>
                    <label> I am organiser!</label>
                </div>
                {
                    this.state.isOragniser? 
                        <div>
                            <input type="text" value = {this.state.organiserName} onChange = {(e => {this.setState({organiserName: e.currentTarget.value})})} placeholder="Organiser name"></input>
                            <div>
                                <Button block onClick={this.handleOrganiserCreate}>Confirm</Button>
                            </div>
                        </div>
                        :
                    null
                }
            </div>
            <div>
            <div className="row">
                <input type="checkbox" checked={this.state.isMusician} onChange={(e) => this.handleChangeMusician(e.currentTarget.checked)}/>
                <label> I am musician!</label>
            </div>
            {
                this.state.isMusician? 
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
                     :
                   null
            }
            </div>
            </React.Fragment>
        )
    }
}