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
            invites: [],
            selectedInvite: ""
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
                                invites: [...prevState.invites, {value: response[i].bandId, label: response[i].bandName+"(Main member)", main: true}]
                              }))
                        }
                        else {
                            this.setState(prevState => ({
                                invites: [...prevState.invites, {value: response[i].bandId, label: response[i].bandName+"(Backup member)", main: false}]
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

    render() {
        return (
            <React.Fragment>
                <div className="AcceptBandInvite">
                    <Form onSubmit={this.handleSubmit}>
                        <Form.Label controlId="chooseInvite"> Odaberi poziv: </Form.Label>
                        <Form.Group controlId="chooseInvite">
                        <Select
                            disabled={this.state.isSearching}
                            name="selectedInvite"
                            options={this.state.invites}
                            value={this.state.selectedInvite}
                            //onChange={this.updateEventType}
                            onChange={value => {
                                this.setValues(value[0])
                                console.log(value)}
                            }
                        />
                        </Form.Group>

                        <Form.Group>
                            <Button type="submit" block> Pozovi/odbij poziv u bend </Button>
                        </Form.Group>
                    </Form>
                </div>
            </React.Fragment>
        )
    }
}