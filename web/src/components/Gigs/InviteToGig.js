import React from 'react';
import "./Gigs.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
//import * as Helpers from '../Utils/HelperMethods'
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import Select from 'react-dropdown-select';
import 'antd/dist/antd.css';
//import Cookies from 'js-cookie';


export default class InviteToGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            selectedBand: "",
            bands: [],
            selectedGig: "",
            bandName: "",
            myGigs: []
        }
        //this.handleGetMyGigs = this.handleGetMyGigs.bind(this)
    }

    componentDidMount() {
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

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleBandIdGet = event => {
        event.preventDefault();
        if(this.state.bandName === "") {
            alert("Band name can't be empty")
        }
        else {
            fetcingFactory(endpoints.GET_BAND_ID, this.state.bandName).then(
                response => response.json()
                ).then(response => {
                    if (response.length === 0) {
                        alert("No bands with that name")
                    }
                    else {
                        console.log(response)
                        for(let i = 0; i < response.length; i++) {
                            this.setState(prevState => ({
                                bands: [...prevState.bands, {value: response[i].id, label: response[i].name}]
                              }))
                        }
                        console.log(this.state.bands)
                    }   
            });
        } 
    }

    setValues = selectedGig => {
        this.setState({ selectedGig }
            , () => console.log(this.state.selectedGig)
        );
    }

    setBandValues = selectedBand => {
        this.setState({ selectedBand }
            , () => console.log(this.state.selectedBand)
        );
    }

    handleSubmit = event => {
        event.preventDefault();
        let params = JSON.stringify({
            "bandId": this.state.selectedBand,
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
                <div className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Invite to gig</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>
                                    
                                    <div className="form-group">
                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="bandName" placeholder="Band name" required="required">
                                            </input>
                                        </div>
                                    </div>

                                    <Button type="button" block onClick={this.handleBandIdGet}> Get band id </Button>
                                    <br></br>
                                    <Select
                                        name="selectedBand"
                                        options={this.state.bands}
                                        value={this.state.selectedBand}
                                        placeholder="Select band to invite"
                                        //onChange={this.updateEventType}
                                        onChange={value => this.setBandValues(value[0].value)}
                                    />
                                    <br></br>
                                    <Select
                                        name="selectedGigName"
                                        options={this.state.myGigs}
                                        value={this.state.selectedGigName}
                                        placeholder="Select gig"
                                        //onChange={this.updateEventType}
                                        onChange={value => this.setValues(value[0].value)}
                                    />
                                    <br></br>
                                    <div className="form-group">
                                        <button type="submit" className="btn btn-primary btn-block btn-lg">Invite to gig</button>
                                    </div>



                                </form>
                            </div>
                        </div>
                    </div>
            </React.Fragment>
        );
    }
}