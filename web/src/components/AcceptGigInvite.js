import React from 'react';
import "./AcceptGigInvite.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
//import * as Helpers from '../Utils/HelperMethods'
import fetcingFactory from "../Utils/external";
import {endpoints} from "../Utils/Types";
import Select from 'react-dropdown-select';

export default class AcceptGigInvite extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bands: [],
            selectedBand: "",
            bandId: "",
            selectedInvite: "",
            bandName: "",
            invitesId: [],
            isSearching: false,
            accept: true
        }
        this.handleRadioChange = this.handleRadioChange.bind(this)
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_BANDS_LEAD, "").then(
            response => response.json()
            ).then(response => {
                console.log(response.code)
                if(response.code === 40001){
                    if(response.violationErrors[0].code === 40003) {
                        alert("You are not a leader of any bands")
                    }
                }
                else {
                    for(let i=0; i<response.length;i++) {
                        this.setState(prevState => ({
                            bands: [...prevState.bands, {value: response[i].id, label: response[i].name}]
                          }))
                    }
                    console.log(this.state.bands)
                }
            })
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    setValues = selectedBand => {
        this.setState({isSearching: true});
        this.setState({ selectedBand }
            //, () => console.log(this.state.selectedBand)
            , () =>
        fetcingFactory(endpoints.GET_BAND_GIGS, this.state.selectedBand).then(
            response => response.json()
            ).then(response => {
                if (response.code === 40001) {
                    alert("You are not in that bend")
                    this.setState({isSearching: false})
                }
                else if (response.length === 0) {
                    console.log(response)
                    alert("No gigs with that id")
                    this.setState({isSearching: false})
                }
                else {
                    //console.log(response)
                    for(let i = 0; i < response.length; i++) {
                        //console.log(response)
                        let helperArray = this.state.invitesId;
                        let inviteId = response[i].id;
                        let inviteLabel = "";
                        fetcingFactory(endpoints.GET_GIG, inviteId).then(
                            response => {
                               return response.json()
                            }
                        ).then(
                            json => {
                                inviteLabel = json.name
                                //console.log(json.name)
                                helperArray.push({value: inviteId, label: inviteLabel});
                            }
                        )
                        //helperArray.push({value: inviteId, label: inviteLabel});
                        this.setState({invitesId: helperArray}
                            //, () => console.log(this.state.invitesId)
                            )
                        this.setState({isSearching: false})
                        
                    }
                }   
            })
         );
    }

    setGigValues = selectedInvite => {
        this.setState({ selectedInvite }
            , () => console.log(this.state.selectedInvite)
        );
    }

    handleSubmit = event => {
        event.preventDefault();
        //console.log(this.state.selectedInvite)
        //console.log(this.state.bandId)

        let params = JSON.stringify({
            "bandId": this.state.selectedBand,
            "gigId": this.state.selectedInvite
        });
        console.log(params)
        if(this.state.accept === true) {
            console.log("Prihvati")
            fetcingFactory(endpoints.ACCEPT_GIG, params).then(
            response => {
                if (response.status === 200) {
                    window.location.href = "/home";
                } else {
                    console.log(response)
                    alert(response.json())
                }
            }); }
        else {
            console.log("Odbij")
            fetcingFactory(endpoints.CANCEL_GIG, params).then(
                response => {
                    if (response.status === 200) {
                        window.location.href = "/home";
                    } else {
                        console.log(response)
                        alert(response.json())
                    }
                }); 
        }
    }

    handleRadioChange(event) {
        const accept = event.currentTarget.value === 'true' ? true: false;
        this.setState({ accept });
        console.log(this.state.accept)
    }

    render () {
        return (
            <React.Fragment>
                <div className = "InviteToGig">
                    <Form onSubmit={this.handleSubmit}>
                        <div className="col-2">
                            <Form.Label controlId="chooseBend"> Odaberi bend: </Form.Label>
                        </div>

                        <div className="col-6">
                            <Form.Group controlId="chooseBand">
                            <Select
                                disabled={this.state.isSearching}
                                name="selectedBand"
                                options={this.state.bands}
                                value={this.state.selectedband}
                                //onChange={this.updateEventType}
                                onChange={value => this.setValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>

                        <div className="col-2">
                            <Form.Label controlId="gigName"> Odaberi gig: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="gigName">
                            <Select
                                disabled={this.state.isSearching}
                                name="selectedInvite"
                                options={this.state.invitesId}
                                value={this.state.selectedInvite}
                                //onChange={this.updateEventType}
                                onChange={value => this.setGigValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>

                        <div className="col-6">
                            <label><input 
                                type="radio"
                                name="accept"
                                value="true"
                                checked={this.state.accept === true}
                                onChange={this.handleRadioChange}
                                ></input>Prihvati</label>
                                <label><input 
                                type="radio"
                                name="accept"
                                value="false"
                                checked={this.state.accept === false}
                                onChange={this.handleRadioChange}
                                ></input>Odbij</label>
                        </div>

                        <div nameClass="col-6">
                            <Form.Group>
                                <Button type="submit" block> Pozovi/odbij poziv za gig </Button>
                            </Form.Group>
                        </div>
                    </Form>
                </div>
            </React.Fragment>
        );
    }
}