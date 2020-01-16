import React from "react";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import  "../Register/register.css";

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

    handleChange = event => {
        if (event.target.name === "eMail") {
            this.setState({
            [event.target.name]: event.target.value.toLowerCase()
            });
        }
        else {
            this.setState({
                [event.target.name]: event.target.value
                });
        }
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
            console.log(this.state)
            this.setState({showModal: true});
        } else {
           let params = JSON.stringify({
            "email": this.state.eMail,
            "username": this.state.userName,
            "phoneNumber": this.state.phone,
            "password": this.state.password
           })
           fetcingFactory(endpoints.REGISTER, params).then(
            response => {
               if (response.status === 200) {
                    window.location.href = "/login";
               } else {
                    console.log(response)
                    this.setState({inValidRegister: true})
               }
            });
        }
    }
      

    render() {
        return (
            
            <div className="modal-login">
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
            <div className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Register</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>

                                    <div className="form-group">
                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="firstName" placeholder="First name" required="required">
                                            </input>
                                        </div>
                                    </div>

                                    <div className="form-group">        
                                    <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="lastName" placeholder="Last name" required="required">
                                            </input>
                                        </div>
                                    </div>

                                    <div className="form-group">        
                                    <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="userName" placeholder="User name" required="required">
                                            </input>
                                        </div>
                                    </div>                                  


                                    <div className="form-group">

                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="email" 
                                            onChange={this.handleChange}
                                            className="form-control" name="eMail" placeholder="E-mail" required="required">
                                            </input>
                                        </div>
                                    </div>

                                    <div className="form-group">        
                                    <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="phone" placeholder="Phone" required="required">
                                            </input>
                                        </div>
                                    </div>  


                                    <div className="form-group">
                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-lock"></i></span>

                                            <input type="password"
                                            onChange={this.handleChange}
                                            className="form-control" name="password" placeholder="Password" required="required"/>
                                            
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <button type="submit" className="btn btn-primary btn-block btn-lg">Register</button>
                                    </div>

                                </form>
                            </div>

                            <div className="modal-footer">Already have an account?&nbsp;&nbsp;<a href="/login">Login</a></div>

                        </div>

                    </div>

            </div>
            </div>
        );
    }

}



