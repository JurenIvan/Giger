import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import * as Helpers from '../Utils/HelperMethods'
import Cookies from 'js-cookie';
import Modal from 'react-bootstrap/Modal';


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
    Cookies.set('Bearer',value.substring(14, substringJson));
    console.log(Cookies.get());
    window.location.href='/home'
    
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
      <div className="container" >
        <Modal show={this.state.showModal} animation={false}>
                <Modal.Footer>
                  <p style={{color:"red"}}>Your password must be at least 8 characters long!</p>
                    <Button
                        variant="danger"
                        onClick={(e) => {
                                        this.setState({showModal: false})}
                                        }
                    >
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        <div className="Login">
          <Form onSubmit={this.handleSubmit}>
            <div className = "col-1">
                <Form.Label controlId="email" > E-mail: </Form.Label>
            </div>
            <div className = "col-6">
            <Form.Group controlId="email">
                <Form.Control
                  autoFocus
                  type="text"
                  value={this.state.username}
                  onChange={this.handleChange}
                />
              </Form.Group>
            </div>

            <div className = "col-2">
                <Form.Label controlId="password" > Password: </Form.Label>
            </div>
            <div className = "col-6">
              <Form.Group controlId="password">
                <Form.Control
                  value={this.state.password}
                  onChange={this.handleChange}
                  type="password"
                />
              </Form.Group>
            </div>
              <Button
                type="submit"
                block
              >
                Login
              </Button>

          </Form>
        </div>
      </div>
    );
  }
}