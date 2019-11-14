import React, { Component } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import * as Helpers from "../Utils/HelperMethods";


export default class register extends React.Component{

    constructor(props) {

        super(props);

        this.state = {
            firstName:"",
            lastName:"",
            userName: "" ,
            eMail: "",
            password: "",
            phone: ""
        };
        this.handleRegister = this.handleRegister.bind(this);
    }

    myChangeHandler = event => {
        this.setState({
          [event.target.id]: event.target.value
        });
      }

    
    
    isUsernameAvailable = function(event){
        //also check if userName is available
        
    }




    handleRegister(message) {
        if(message != "Registration ok!") {
            alert(message);
        } else {
            alert("We have sent confirmation email to" + this.state.eMail)
        }

    }

    validateRegister () {
        return this.state.eMail.length > 0 && this.state.userName.length > 0 && this.state.phone.length > 0 && this.state.password > 0;
    }

    //if validateRegister  returns true send form to db
    handleSubmit = event => {
        event.preventDefault();
      
        (async () => {     
           await Helpers.sendRegisterInfo(this.state.eMail, this.state.userName, this.state.phone, this.state.password, this.handleRegister);
        })();
      
      }

    render() {
        return (
            <div className="container" >
            
            <div className="register">

            <h1>Hello, welcome to Giger!</h1>
            <Form onSubmit={this.handleSubmit}>


            
                <div className="col-1">
                    <Form.Label controlId="firstName" > First name: </Form.Label>
                </div> 

                <div className="col-6">
                    <Form.Group controlId="firstName" bsSize="large">
                    <Form.Control
                    autoFocus
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>

                <div className="col-1">
                    <Form.Label controlId="lastName" > Last name: </Form.Label>
                </div> 

                <div className="col-6">
                    <Form.Group controlId="lastName" bsSize="large">
                    <Form.Control
                    autoFocus
                    name='lastName'
                    value={this.state.lastName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>


                <div className="col-1">
                    <Form.Label controlId="userName" > Username: </Form.Label>
                </div> 

                <div className="col-6">
                    <Form.Group controlId="userName" bsSize="large">
                    <Form.Control
                    autoFocus
                    name='userName'
                    value={this.state.userName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>
                <div className="col-1">
                    <Form.Label controlId="phone" > Phone: </Form.Label>
                </div> 
                <div className="col-6">
                    <Form.Group controlId="phone" bsSize="large">
                    <Form.Control
                    autoFocus
                    name='phone'
                    value={this.state.phone}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>
                <div className="col-1">
                    <Form.Label controlId="eMail" > Email: </Form.Label>
                </div> 

                <div className="col-6">
                    <Form.Group controlId="eMail" bsSize="large">
                    <Form.Control
                    autoFocus
                    name="eMail"
                    type="email"
                    value={this.state.eMail}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>

                <div className="col-1">
                    <Form.Label controlId="password" > Password: </Form.Label>
                </div> 

                <div className="col-6">
                    <Form.Group controlId="password" bsSize="large">
                    <Form.Control
                    autoFocus
                    name="password"
                    type="password"
                    value={this.state.password}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>

                <br/>


                <div className = "col-8">
                <Button
                 block
                bsSize="large"
                disabled={!this.validateRegister}
                type="submit"
                >
                Register
                </Button>
                </div>

            </Form>

            </div>
            </div>
        );
    }

}

