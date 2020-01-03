import React from 'react';
import "./InviteToGig.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import * as Helpers from '../Utils/HelperMethods'

export default class CreateGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bandId = "",
            gigId = "",
            bandName = "",
            gigName = ""
        }
    }

    render () {
        return (
            <React.Fragment>
                <div className = "InviteToGig">
                    <Form onSubmit={this.handleSubmit}>
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