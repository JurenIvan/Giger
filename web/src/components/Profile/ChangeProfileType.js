import * as React from "react";
import { Card, Button, Row } from "react-bootstrap";

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
        /*submit*/
    }
    handleOrganiserCreate() {
        /*submit*/
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
                            <input type="text" placeholder="Organiser name"></input>
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
                            <textarea type="text" placeholder="Biography"></textarea>
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