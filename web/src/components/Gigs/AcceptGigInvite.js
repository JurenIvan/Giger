import React from 'react';
import "./Gigs.css";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
//import Select from 'react-dropdown-select';
import { Radio , Select} from 'antd';
import 'antd/dist/antd.css';

const {Option} = Select;

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
        this.handleRadioChange = this.handleRadioChange.bind(this);
        this.handleBChange = this.handleBChange.bind(this);
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_BANDS_LEAD, "").then(
            response => response.json()
            ).then(response => {
                //console.log(response.code)
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
                    //console.log(this.state.bands)
                }
            })
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    setValues = selectedBand => {
        this.setState({invitesId: []})
        this.setState({isSearching: true});
        this.setState({ selectedBand }
            , () =>
        fetcingFactory(endpoints.GET_BAND_GIGS, this.state.selectedBand).then(
            response => response.json()
            ).then(response => {
                //console.log(response)
                if (response.code === 40001) {
                    alert("You are not in that bend")
                    this.setState({isSearching: false})
                }
                else if (response.length === 0) {
                    console.log(response)
                    alert("That band doesn't have any gig invites")
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
                                //console.log(json)
                                helperArray.push({value: inviteId, label: inviteLabel});
                            }
                        ).then(
                            async () =>
                            await this.setState({invitesId: helperArray}
                                //, () => console.log(this.state.invitesId)
                                )
                        )
                        //helperArray.push({value: inviteId, label: inviteLabel});

                        this.setState({isSearching: false})
                        
                    }
                }   
            })
         );
    }

    setGigValues = selectedInvite => {
        this.setState({ selectedInvite }
            //, () => console.log(this.state.selectedInvite)
        );
    }

    handleSubmit = event => {
        event.preventDefault();


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

    handleBChange(value) {
        this.setState({selectedBand: value}
            //, () => console.log(this.state.selectedBand)
            )
    }

    render () {
        let option = []
        this.state.invitesId.forEach( invite => {
            option.push(invite)
        })
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
                                        onChange={value => this.setValues(value)}
                                        placeholder="Select band"
                                        name="selectedBand"
                                        value={this.state.selectedBand?this.state.selectedBand:undefined}
                                    >
                                            {this.state.bands.map(item => (
                                                <Option key={item.value}>{item.label}</Option>
                                            ))}
                                    </Select>
                                    <br></br><br></br>
                                    <Select 
                                        disabled={this.state.isSearching}
                                        onChange={value => this.setGigValues(value)}
                                        placeholder="Select gig invite"
                                        name="selectedInvite"
                                        value={this.state.selectedInvite?this.state.selectedInvite:undefined}
                                    >
                                            {option.map(item => (
                                                <Option key={item.value}>{item.label}</Option>
                                            ))}
                                    </Select>
                                    <br></br><br></br>
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