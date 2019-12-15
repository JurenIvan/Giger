import React, {Component} from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import * as Helpers from '../Utils/HelperMethods'
import Cookies from 'js-cookie';
import Modal from 'react-bootstrap/Modal';
import { Card } from "react-bootstrap";


export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            token: "",
            showModal: false,
        };
        this.handleLoginToken = this.handleLoginToken.bind(this);
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 7;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleLoginToken(value) {
        let substringJson = value.length - 4;
        Cookies.set('Bearer', value.substring(14, substringJson));
        console.log(Cookies.get());
        window.location.href = '/home'
    }


    handleSubmit = event => {
        event.preventDefault();
        if (!this.validateForm()) {
            this.setState({showModal: true})
        } else {
            (async () => {
                await Helpers.sendLoginInfo(this.state.email, this.state.password, this.handleLoginToken);
            })();
        }
    }


    render() {
        return (
            <Card>
                <Modal show={this.state.showModal} animation={false}>
                    <Modal.Body style={{color: "red"}}> Your password must be at least 8 characters long! </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={(e) => {
                            this.setState({showModal: false})
                        }}>Close</Button>
                    </Modal.Footer>
                </Modal>
                <div className="container">
                    <Form onSubmit={this.handleSubmit}>
                        
                        <Form.Label controlId="email"> E-mail: </Form.Label>
                        <Form.Group controlId="email">
                            <Form.Control 
                            placeholder="Email"
                            autoFocus type="text"
                            value={this.state.username}
                            onChange={this.handleChange}/>
                        </Form.Group>

                        <Form.Label controlId="password"> Password: </Form.Label>
                        <Form.Group controlId="password">
                            <Form.Control 
                            placeholder="Password"
                            value={this.state.password} 
                            onChange={this.handleChange} 
                            type="password"/>
                        </Form.Group>
                        <Button type="submit" block> Login </Button>
                    </Form>
                </div>
            </Card>
        );
    }
}