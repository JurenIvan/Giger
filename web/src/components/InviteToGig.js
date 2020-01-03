import React from 'react';
import "./InviteToGig.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
//import * as Helpers from '../Utils/HelperMethods'
import fetcingFactory from "../Utils/external";
import {endpoints} from "../Utils/Types";


export default class CreateGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bandId: "",
            gigId: "",
            bandName: "",
            gigName: ""
        }
        this.handleBandIdGet = this.handleBandIdGet.bind(this)
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleBandIdGet = event => {
        event.preventDefault();
        fetcingFactory(endpoints.GETBANDID, this.state.bandName).then(
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
                        <h1>{this.state.bandName}</h1> 
                        <h1>{this.state.bandId}</h1>

                        <div className="col-6">
                            <Form.Group>
                                <Button type="button" block onClick={this.handleBandIdGet}> Dohvati ID benda </Button>
                            </Form.Group>
                        </div>

                        <div className="col-2">
                            <Form.Label controlId="gigName"> Ime giga: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="gigName">
                                <Form.Control autoFocus type="text" value={this.state.gigName}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>

                        <h1>{this.state.gigName}</h1> 
                        <h1>{this.state.gigId}</h1>

                        <div className="col-6">
                            <Form.Group>
                                <Button type="button" block onClick={this.handleGigIdGet}> Dohvati ID benda </Button>
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