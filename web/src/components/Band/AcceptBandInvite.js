import React from 'react';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import { Radio, Select, notification, Icon } from 'antd';
import 'antd/dist/antd.css';

const {Option} = Select;

const openNotificationAccept = () => {
    notification.open({
      message: 'You have successfully accepted a band invite!',
      description:
        'You have accepted a band invite.\n Click Notification to redirect to Home',
      icon: <Icon type="smile" style={{ color: '#108ee9' }} 
      />,
      duration: 7,
      onClick: () => {
        window.location.href = "/home";
      },
      onClose: () => {
        window.location.href = "/home";
      }

    });
}

const openNotificationDecline = () => {
    notification.open({
      message: 'You have successfully declined a band invite!',
      description:
        'You have declined a band invite.\n Click Notification to redirect to Home',
      icon: <Icon type="smile" style={{ color: '#108ee9' }} 
      />,
      duration: 7,
      onClick: () => {
        window.location.href = "/home";
      },
      onClose: () => {
        window.location.href = "/home";
      }

    });
}

function contains(a, obj) {
    var i = a.length;
    while (i--) {
       if (a[i].value === obj.value) {
           return true;
       }
    }
    return false;
}

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
                    console.log(response)
                    for(let i=0; i<response.length;i++) {
                        let responseItem = {value: response[i].bandId, label: response[i].bandName};
                        console.log(responseItem);
                        if(!contains(this.state.invites, responseItem)) {
                            this.setState(prevState => ({
                                invites: [...prevState.invites, {value: response[i].bandId, label: response[i].bandName}]
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
                    openNotificationAccept();
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
                        openNotificationDecline();
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