import * as React from 'react';
import { Col, Row, Form, Button, Modal } from 'react-bootstrap';
import {Card, Avatar} from "antd"

export default class ProfileInfo extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name : "",
            description: "", 
            profilePic: "",
            newProfilePic: "",
            postContent: "",
            edit: this.props.edit
        }
        this.handleProfilePic = this.handleProfilePic.bind(this);
        this.handlePostSubmit = this.handlePostSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSave = this.handleSave.bind(this);
    }

    componentWillMount() {
        /**get all data of profile info */
    }

    handleProfilePic (e) {
       this.setState({newProfilePic: e.currentTarget.value})
    }

    handlePostSubmit = event => {
        event.preventDefault();
        
    }

    handleChange = event => {
        this.setState({ postContent: event.target.value});
    }

    handleSave() {
        let newProfilePic = this.state.newProfilePic;
        this.setState({profilePic: newProfilePic, 
            uploadModal: false,
            newProfilePic: ""
        });
    }

    render() {
        return (
        <React.Fragment>
             <Modal show={this.state.uploadModal} animation={false}>
                <Modal.Body>
                    <input onChange = {this.handleProfilePic}
                     type = "url"  
                     className="form-control" 
                     value = {this.state.newProfilePic} 
                     placeholder = "Profile picture url"
                     />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="success" onClick = {this.handleSave}> Save </Button>
                    <Button variant="secondary" onClick={(e) => {
                        this.setState({uploadModal: false, newProfilePic: ""})
                    }}>Close</Button>
                </Modal.Footer>
            </Modal>

            <Card 
            title = {"Test"} 
            style = {{width: 500, margin: "auto"}}>
                
                    <Row>
                        <Col md = {5}>
                            <Row>
                                <Avatar shape="square" size = {250} src = {this.state.profilePic}/>
                            </Row>
                                
                            <Row>
                                {
                                    this.state.edit?
                                    <Button style = {{width:250}} 
                                    onClick = {()=> this.setState({uploadModal: true})}>
                                        Upload profile picture
                                    </Button> : null
                                }
                            </Row>
                        </Col>
                        <Col>
                            Test description
                        </Col>
                        </Row>
                
            </Card>
                   
                    
            {
                this.state.edit? 
                    null :
                <Row className="profileInfo">
                    <textarea type="text"
                        onChange={this.handleChange}
                         id="postContent"
                         className="form-control"
                         as></textarea>
                        <Button block type="submit" onClick = {this.handlePostSubmit}
                        style = {{width:500 , margin: "5px"}}>
                            Post
                        </Button>
                </Row>
            }
           
        </React.Fragment>
        )
    }
}