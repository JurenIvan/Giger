import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'


export default class register extends React.Component{

    constructor(props) {

        super(props);

        this.state = {
            firstName:"",
            lastName:"",
            userName: "" ,
            eMail: "",
            password: "",
        };
    }

    myChangeHandler = event => {
        this.setState({
          [event.target.id]: event.target.value
        });
      }

    
    
    isUsernameAvailable = function(event){
        //also check if userName is available
        
    }




    validateRegister = function () {}

    //if validateRegister  returns true send form to db
    handleSubmit= function(){}

    





    render() {
        return (
            <div className="container" >
            
            <div class="register">

            <h1>Hello, welcome to Giger!</h1>
            <Form onSubmit={this.handleSubmit}>


            
                <div class="col-1">
                    <Form.Label controlId="firstName" > First name: </Form.Label>
                </div> 

                <div class="col-6">
                    <Form.Group controlId="firstName" bsSize="large">
                    <Form.Control
                    autoFocus
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>

                <div class="col-1">
                    <Form.Label controlId="lastName" > Last name: </Form.Label>
                </div> 

                <div class="col-6">
                    <Form.Group controlId="lastName" bsSize="large">
                    <Form.Control
                    autoFocus
                    name='lastName'
                    value={this.state.lastName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>


                <div class="col-1">
                    <Form.Label controlId="userName" > Username: </Form.Label>
                </div> 

                <div class="col-6">
                    <Form.Group controlId="userName" bsSize="large">
                    <Form.Control
                    autoFocus
                    name='userName'
                    value={this.state.lastName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                </div>

                <div class="col-1">
                    <Form.Label controlId="eMail" > Email: </Form.Label>
                </div> 

                <div class="col-6">
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

                <div class="col-1">
                    <Form.Label controlId="password" > Password: </Form.Label>
                </div> 

                <div class="col-6">
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
                disabled={!this.validateRegister()}
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

