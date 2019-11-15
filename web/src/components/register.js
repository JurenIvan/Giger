import React, { Component } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import * as Helpers from "../Utils/HelperMethods";

export default class RegisterClass extends React.Component{

    constructor(props) {

        super(props);

        this.state = {
            firstName:"",
            lastName:"",
            userName: "" ,
            eMail: "",
            password: "",
            phone: "",
            showModal: false
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
        if(message !== "Registration ok!") {
            alert(message);
        } else {
            alert("We have sent confirmation email to" + this.state.eMail)
        }

    }

    validateRegister () {

    }

    //if validateRegister  returns true send form to db
    handleSubmit = event => {
        event.preventDefault();
        let shouldShow = this.state.eMail.length < 0 && this.state.userName.length < 0 && this.state.phone.length < 0 && this.state.password < 0
        if (!shouldShow) {
            this.setState({showModal: !shouldShow});
        } else {
            (async () => {     
                await Helpers.sendRegisterInfo(this.state.eMail, this.state.userName, this.state.phone, this.state.password, this.handleRegister);
            })();
        }
    }
      

    render() {
        return (
          
            
            <div className="container" >
            <Modal show={this.state.showModal} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Alert:</Modal.Title>
                </Modal.Header>
                <Modal.Body style={{color:"red"}}> You did not meet requierments to register! </Modal.Body>
                <Modal.Footer>
                    <Button
                        variant="secondary"
                        onClick={(e) => {
                                        this.setState({showModal: false})}
                                        }
                    >
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
            <div className="register">

            <h1></h1>
            <Form onSubmit={this.handleSubmit}>


            
                
                    <Form.Label controlId="firstName" > First name: </Form.Label>
               

                
                    <Form.Group controlId="firstName">
                    <Form.Control
                    autoFocus
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                

                
                    <Form.Label controlId="lastName" > Last name: </Form.Label>
               

                
                    <Form.Group controlId="lastName">
                    <Form.Control
                    autoFocus
                    name='lastName'
                    value={this.state.lastName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                


                
                    <Form.Label controlId="userName" > Username: </Form.Label>
               

                
                    <Form.Group controlId="userName">
                    <Form.Control
                    autoFocus
                    name='userName'
                    value={this.state.userName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                
                
                    <Form.Label controlId="phone" > Phone: </Form.Label>
               
                
                    <Form.Group controlId="phone">
                    <Form.Control
                    autoFocus
                    name='phone'
                    value={this.state.phone}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                
                
                    <Form.Label controlId="eMail" > Email: </Form.Label>
               

                
                    <Form.Group controlId="eMail">
                    <Form.Control
                    autoFocus
                    name="eMail"
                    type="email"
                    value={this.state.eMail}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                

                
                    <Form.Label controlId="password" > Password: </Form.Label>
               

                
                    <Form.Group controlId="password" >
                    <Form.Control
                    autoFocus
                    name="password"
                    type="password"
                    value={this.state.password}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                

                <br/>


                
                <Button
                block
                type="submit"
                >
                Register
                </Button>
                

            </Form>

            </div>
            </div>

        );
    }

}



