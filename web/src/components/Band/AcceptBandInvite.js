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
            musicians: [],
            selectedBand: "",
            musicianName: "",
            bands: [],
            selectedMusician: "",
            isSearching: false,
            main: true
        }
        this.handleRadioChange = this.handleRadioChange.bind(this)
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