import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import * as Helpers from "../Utils/HelperMethods";
import { Card } from "react-bootstrap";

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
            showModal: false,
            inValidRegister: false
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


    handleRegister(status) {
        if(status === 200) {
            window.location.href = '/';
        } else {
            this.setState({inValidRegister: true});
        }

    }

    validateRegister () {

    }

    //if validateRegister  returns true send form to db
    handleSubmit = event => {
        event.preventDefault();
        let shouldShow = this.state.eMail.length < 0 || this.state.userName.length < 0 || this.state.phone.length < 0 || this.state.password < 7
        if (shouldShow) {
            this.setState({showModal: true});
        } else {
            (async () => {     
                await Helpers.sendRegisterInfo(this.state.eMail, this.state.userName, this.state.phone, this.state.password, this.handleRegister);
            })();
        }
    }
      

    render() {
        return (
          
            <Card>
            <div className="container">
                <Modal show={this.state.inValidRegister} animation={false}>
                <Modal.Body style={{color:"red"}}> Something went wrong with your registration. Please, try again! </Modal.Body>
                <Modal.Footer>
                    <Button
                        variant="secondary"
                        onClick={(e) => {
                                        this.setState({inValidRegister: false})}
                                        }
                    >
                        Close
                    </Button>
                </Modal.Footer>
                </Modal>
            <Modal show={this.state.showModal} animation={false}>
                <Modal.Footer style={{color:"red", textAlign:"center"}}> 
                    <p style={{textAlign:"center"}}>You did not meet requierments to register!</p>
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
            <div className="register">
            <h2 style={{fontWeight: "bold"}}> Welcome! Would you like to register?</h2>
            <Form onSubmit={this.handleSubmit}>

                    <Form.Label controlId="firstName" > First name: </Form.Label>
                    
                    <Form.Group controlId="firstName">
                    <Form.Control
                    placeholder="Name"
                    autoFocus
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                
                    <Form.Label controlId="lastName" > Last name: </Form.Label>
                
                    <Form.Group controlId="lastName">
                    <Form.Control
                    placeholder="Last name"
                    name='lastName'
                    value={this.state.lastName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                   
               
                    <Form.Label controlId="userName" > Username: </Form.Label>
                    <Form.Group controlId="userName">
                    <Form.Control
                    placeholder="Username"
                    name='userName'
                    value={this.state.userName}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                
                    <Form.Label controlId="phone" > Phone: </Form.Label>
               
                    <Form.Group controlId="phone">
                    <Form.Control
                    placeholder="Phone"
                    name='phone'
                    value={this.state.phone}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
                
                    <Form.Label controlId="eMail" > Email: </Form.Label>
               
                    <Form.Group controlId="eMail">
                    <Form.Control
                    placeholder="Email"
                    name="eMail"
                    type="email"
                    value={this.state.eMail}
                    onChange={this.myChangeHandler}
                    />
                    </Form.Group>
           
                    <Form.Label controlId="password" > Password: </Form.Label>
               
                    <Form.Group controlId="password" >
                    <Form.Control
                    placeholder="Password"
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
            </Card>
        );
    }

}



