import React, {Component} from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
//import * as Helpers from '../Utils/HelperMethods'
import Cookies from 'js-cookie';
import Modal from 'react-bootstrap/Modal';
import { Card } from "react-bootstrap";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import  "../Login/Login.css";

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            token: "",
            showModal: false,
        };
        
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 7;
    }

    handleChange = event => {
        if (event.target.name === "email") {
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



    handleSubmit = event => {
        event.preventDefault();
        if (!this.validateForm()) {
            this.setState({showModal: true})
        } else {
            let params = JSON.stringify({
                "email": this.state.email,
                "password": this.state.password
                });
            fetcingFactory(endpoints.LOGIN, params).then(
                response => {
                    return response.json()
                }
            ).then (json => {
                if (json.token && json.userId) {
                    Cookies.set("userId", json.userId);
                    Cookies.set("Bearer", json.token);
                    console.log(json);
                    console.log(json.token);
                    console.log(json.userId)
                    window.location.href = "/home"
                } else {
                    alert(json.violationErrors[0].message)
                }
            });
        }
    }

    render() {
        return (
                    <div className="modal-dialog" className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Welcome back!</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>
                                    <div className="form-group">

                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="email" placeholder="E-mail" required="required">
                                            </input>
                                            <br></br>
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
                                        <button type="submit" className="btn btn-primary btn-block btn-lg">Login</button>
                                    </div>

                                </form>
                            </div>

                            <div className="modal-footer">Don't have an account?&nbsp;&nbsp;<a href="/register">Create one</a></div>

                        </div>

                    </div>
       
                
    ); 
    }
}
