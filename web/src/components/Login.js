import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import * as Helpers from '../Utils/HelperMethods'
import {Link} from "react-router-dom";
import Cookies from 'js-cookie';


export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: "",
      token: ""
    };
    this.handleLoginToken = this.handleLoginToken.bind(this);
  }

  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
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
  
    (async () => {     
       await Helpers.sendLoginInfo(this.state.username, this.state.password, this.handleLoginToken);
    })();
  
  }

   
  


  render() {
    return (
      <div className="container" >
        <div className="Login">
          <Form onSubmit={this.handleSubmit}>
            <div className = "col-1">
                <Form.Label controlId="username" > username: </Form.Label>
            </div>
            <div className = "col-6">
            <Form.Group controlId="username">
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
              <Form.Group controlId="password" bsSize="large">
                <Form.Control
                  value={this.state.password}
                  onChange={this.handleChange}
                  type="password"
                />
              </Form.Group>
            </div>
            
            <div className = "col-8">
              <Button
                block
                bsSize="large"
                disabled={!this.validateForm()}
                type="submit"
              >
                Login
              </Button>
            </div>

            <div className = "col-8">
              <Button
                block
                bsSize="large"
              >
                Continue as Guest
              </Button>
            </div>
          </Form>
        </div>
        {
          this.state.token == '' ? null:
          <div className="container"> 
           {this.state.token}
          </div>
        }
      </div>
    );
  }
}