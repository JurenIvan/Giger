import React from 'react';
import "./Gigs.css";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import Select from 'react-dropdown-select';
import { Radio } from 'antd';
import 'antd/dist/antd.css';

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
                else if(response.length===0) {
                    alert("You are not a leader of any bands")
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

    handleRadioChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
          accept: e.target.value,
        });
    }

    render () {
        return (
            <React.Fragment>
                <div className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Manage gig invite</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>
                                    <Select
                                        disabled={this.state.isSearching}
                                        name="selectedBand"
                                        options={this.state.bands}
                                        value={this.state.selectedBand}
                                        placeholder="Select band to invite"
                                        //onChange={this.updateEventType}
                                        onChange={value => this.setValues(value[0].value)}
                                    />
                                    <br></br>
                                    <Select
                                        disabled={this.state.isSearching}
                                        name="selectedInvite"
                                        options={this.state.invitesId}
                                        value={this.state.selectedInvite}
                                        placeholder="Select gig"
                                        //onChange={this.updateEventType}
                                        onChange={value => this.setGigValues(value[0].value)}
                                    />
                                    <br></br>
                                    <Radio.Group onChange={this.handleRadioChange} value={this.state.accept}>
                                        <Radio value={true}>Accept</Radio>
                                        <Radio value={false}>Decline</Radio>
                                    </Radio.Group>
                                    <br></br><br></br>
                                    <div className="form-group">
                                        <button type="submit" className="btn btn-primary btn-block btn-lg">Accept/decline invite</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
            </React.Fragment>
        );
    }
}