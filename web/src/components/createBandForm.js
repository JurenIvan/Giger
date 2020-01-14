import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {createBand} from "../Utils/createBand";


export default class CreateBandForm extends React.Component{
    
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
        if(this.state.name.length>0
            && this.state.bio.length>0
            && this.state.pictureURL>0
            && this.state.gigType>0
            &&this.state.location.length>0)
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
            await createBand(this.state.name, this.state.bio, this.state.pictureURL, this.state.gigType, this.state.location, this.handleCreateBand);
        })();

    }

    render(){


        return(
            <div className="container">
            <h2 style={{fontWeight: "bold"}}>CreateBand</h2>

            <Form onSubmit={this.handleSubmit}>
            <Form.Label controlId="name">Band Name</Form.Label>
            <Form.Group controlId="name">
            <Form.Control
            placeholder="Band name"
            autofocus
            name="name"
            value={this.state.name}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Form.Label controlId="bio">Bio</Form.Label>
            <Form.Group controlId="bio">
            <Form.Control
            placeholder="bio"
            autofocus
            name="bio"
            value={this.state.bio}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            
            <Form.Label controlId="pictureURL">Picture URL</Form.Label>
            <Form.Group controlId="pictureURL">
            <Form.Control
            placeholder="Picture URL"
            autofocus
            name="pic"
            value={this.state.pictureURL}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Form.Label controlId="gigType">Gig Type</Form.Label>
            <Form.Group controlId="gigType">
            <Form.Control
            placeholder="Gig Type"
            autofocus
            name="gigType"
            value={this.state.gigType}
            onChange={this.myChangeHandler}
            />
            </Form.Group>

            <Form.Label controlId="location">Location</Form.Label>
            <Form.Group controlId="location">
            <Form.Control
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
            disabled={this.isValidForm()}
            >
                CreateBand
            </Button>

            </Form>   
        </div>
        );
    }
}
