import React from 'react';
import "./InviteToGig.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
//import * as Helpers from '../Utils/HelperMethods'
import fetcingFactory from "../Utils/external";
import {endpoints} from "../Utils/Types";
import Select from 'react-dropdown-select';
//import Cookies from 'js-cookie';


export default class CreateGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bandId: "",
            selectedGig: "",
            bandName: "",
            myGigs: []
        }
        //this.handleGetMyGigs = this.handleGetMyGigs.bind(this)
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleBandIdGet = event => {
        event.preventDefault();

        fetcingFactory(endpoints.GET_BAND_ID, this.state.bandName).then(
            response => response.json()
            ).then(response => {
                if (response.length === 0) {
                    alert("No bands with that name")
                }
                else {
                    console.log(response)
                    this.setState({
                        bandId: response[0].id
                    })
                    console.log(this.state.bandId)
                }   
            });
    }

    setValues = selectedGig => {
        this.setState({ selectedGig }
            , () => console.log(this.state.selectedGig)
        );
    }

    handleGetMyGigs = event => {
        event.preventDefault();
        fetcingFactory(endpoints.GET_MY_GIGS, "my").then(
            response => response.json()
            ).then(response => {
                if(response.code === 40001) {
                    alert("You have to be an organizer")
                }
                else if (response.length === 0) {
                    alert("No gigs")
                }
                else {
                    console.log(response)
                    for(let i = 0; i < response.length; i++) {
                        this.setState(prevState => ({
                            myGigs: [...prevState.myGigs, {value: response[i].id, label: response[i].name}]
                          }))
                    }
                }   
            });
    }

    handleSubmit = event => {
        event.preventDefault();
        let params = JSON.stringify({
            "bandId": this.state.bandId,
            "gigId": this.state.selectedGig
        });
        console.log(params)
        fetcingFactory(endpoints.INVITE_TO_GIG, params).then(
            response => {
                if (response.status === 200) {
                    window.location.href = "/home";
                } else {
                    console.log(response)
                    alert(response.json())
                }
            });
    }

    render () {
        return (
            <React.Fragment>
                <div className = "InviteToGig">
                    <Form onSubmit={this.handleSubmit}>
                        <div className="col-2">
                            <Form.Label controlId="bandName"> Ime benda: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="bandName">
                                <Form.Control autoFocus type="text" value={this.state.bandName}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>

                        <div className="col-6">
                            <Form.Group>
                                <Button type="button" block onClick={this.handleBandIdGet}> Dohvati ID benda </Button>
                            </Form.Group>
                        </div>

                        <div className="col-6">
                            <Form.Group>
                                <Button type="button" block onClick={this.handleGetMyGigs}> Dohvati svoje gigove </Button>
                            </Form.Group>
                        </div>

                        <div className="col-2">
                            <Form.Label controlId="gigName"> Odaberi gig: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="gigName">
                            <Select
                                name="selectedGigName"
                                options={this.state.myGigs}
                                value={this.state.selectedGigName}
                                //onChange={this.updateEventType}
                                onChange={value => this.setValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>

                        <div nameClass="col-6">
                            <Form.Group>
                                <Button type="submit" block> Pozovi bend u gig </Button>
                            </Form.Group>
                        </div>
                    </Form>
                </div>
            </React.Fragment>
        );
    }
}