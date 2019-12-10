import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import * as createBand from "../Utils/createBand";


export default class createBandForm extends React.Component{
    
    constructor(props){
        super(props)

        this.state={
            name: "",
            bio: "",
            pictureURL: "",
            gigType: "",
            location: "",
            showButton: false,
            isValidForm: false,
        }
        this.handleCreateBand=this.handleCreateBand.bind(this)
    }

    //to disable/enable button
    isValidForm = event => {
        if(this.name.length>0
            && this.bio.length>0
            && this.pictureURL>0
            && this.gigType>0
            &&this.location.length>0)
        {
            return true;
        }
        return false;
    }



    handleCreateBand(status) {
        if(status === 200) {
            window.location.href = '/';
        } else {
        this.setState({inValidRegister: true});
    }
    }   

    myChangeHandler = event => {
        this.setState({
          [event.target.id]: event.target.value
        });
    }

    isBandNameAvailable = function(event){
        //TODO: check if band name is available
    }


    //send createBandinfo
    handleSubmit = event => {
        event.preventDefault();
        (async () => {     
            await Helpers.sendRegisterInfo(this.state.eMail, this.state.userName, this.state.phone, this.state.password, this.handleCreateBand);
        })();

    }

    render(){


        return(
            <div className="container">
            <h2 style={{fontWeight: "bold"}}>CreateBand</h2>

            <Form onSubmit={this.handleSubmit}>
            <Form.Label controlId="name">Band Name</Form.Label>
            <Form.Group controlId="name">
            <Form.control
            placeholder="Band name"
            autofocus
            name="name"
            value={this.state.name}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Form.Label controlId="bio">Bio</Form.Label>
            <Form.Group controlId="bio">
            <Form.control
            placeholder="bio"
            autofocus
            name="bio"
            value={this.state.bio}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            
            <Form.Label controlId="pic">Picture URL</Form.Label>
            <Form.Group controlId="pic">
            <Form.control
            placeholder="Picture URL"
            autofocus
            name="pic"
            value={this.state.pictureURL}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Form.Label controlId="gigType">Gig Type</Form.Label>
            <Form.Group controlId="gigType">
            <Form.control
            placeholder="Gig Type"
            autofocus
            name="gigType"
            value={this.state.gigType}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Form.Label controlId="location">Location</Form.Label>
            <Form.Group controlId="gigType">
            <Form.control
            placeholder="Location"
            autofocus
            name="location"
            value={this.state.location}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Button
            block
            type="submit"
            disabled={!this.isValidForm()}
            >
                Register
            </Button>

            </Form>   
        </div>
        );
    }
}
