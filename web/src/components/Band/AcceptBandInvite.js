import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import Select from 'react-dropdown-select';

export default class AcceptBandInvite extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isSearching: false,
            mainInvites: [],
            backupInvites: []
        }
        //this.handleRadioChange = this.handleRadioChange.bind(this)
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
                else {
                    for(let i=0; i<response.length;i++) {
                        if(response[i].asMember === true) {
                            this.setState(prevState => ({
                                mainInvites: [...prevState.mainInvites, {value: response[i].bandId, label: response[i].bandId}]
                              }))
                        }
                        else {
                            this.setState(prevState => ({
                                backupInvites: [...prevState.backupInvites, {value: response[i].bandId, label: response[i].bandId}]
                              }))
                        }
                    }
                    console.log(this.state.mainInvites)
                    console.log(this.state.backupInvites)
                }
            }).then( () => 
        this.setState({isSearching: false}))
    }

    render() {
        return (
            <React.Fragment>
                <div className="AcceptBandInvite">
                    <Form onSubmit={this.handleSubmit}>
                        
                    </Form>
                </div>
            </React.Fragment>
        )
    }
}