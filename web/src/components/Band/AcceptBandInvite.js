import React from 'react';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
//import Select from 'react-dropdown-select';
import { Radio, Select } from 'antd';
import 'antd/dist/antd.css';

const {Option} = Select;

export default class AcceptBandInvite extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isSearching: false,
            invites: [],
            selectedInvite: "",
            accept: true
        }
        this.handleRadioChange = this.handleRadioChange.bind(this)
        this.handleIChange = this.handleIChange.bind(this)
    }

    componentDidMount() {
        this.setState({isSearching: true})
        fetcingFactory(endpoints.GET_BAND_INVITATIONS, "").then(
            response => response.json()
            ).then(response => {
                console.log(response.code)
                if(response.code === 40001){
                    console.log(response)
                    alert("Error")
                }
                else if(response.length === 0) {
                    alert("You don't have any invites")
                }
                else {
                    for(let i=0; i<response.length;i++) {
                        if(response[i].asMember === true) {
                            this.setState(prevState => ({
                                invites: [...prevState.invites, {value: response[i].bandId, label: response[i].bandName+"(Main member)"}]
                              }))
                        }
                        else {
                            this.setState(prevState => ({
                                invites: [...prevState.invites, {value: response[i].bandId, label: response[i].bandName+"(Backup member)"}]
                              }))
                        }
                    }
                    console.log(this.state.invites)
                }
            }).then( () => 
        this.setState({isSearching: false}))
    }

    setValues = selectedInvite => {
        this.setState({ selectedInvite }
            , () => console.log(this.state.selectedInvite)
        );
    }

    handleSubmit = event => {
        event.preventDefault();
        console.log(this.state.selectedInvite)
        if(this.state.accept === true) {
            console.log("Prihvati")
            fetcingFactory(endpoints.ACCEPT_BAND_INVITE, this.state.selectedInvite).then(
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
            fetcingFactory(endpoints.DECLINE_BAND_INVITE, this.state.selectedInvite).then(
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

    handleIChange(value) {
        this.setState({selectedInvite: value}, () => console.log(this.state.selectedInvite))
    }

    render() {
        return (
            <React.Fragment>
                <div className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Manage band invite</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>
                                    <Select 
                                        disabled={this.state.isSearching}
                                        onChange={this.handleIChange}
                                        placeholder="Choose invite"
                                        name="selectedMInvite"
                                        value={this.state.selectedInvite?this.state.selectedInvite:undefined}
                                    >
                                            {this.state.invites.map(item => (
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
        )
    }
}