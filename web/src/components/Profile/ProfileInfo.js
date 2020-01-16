import * as React from 'react';
// eslint-disable-next-line
import { Col, Row, Button, Modal } from 'react-bootstrap';
import {Card, Avatar} from "antd"
import DisplayInstruments from "../BasicComponents/DisplayInstruments"
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";

export default class ProfileInfo extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name : this.props.name,
            description: "", 
            profilePic: this.props.pictureUrl,
            newProfilePic: "",
            postContent: "",
            edit: this.props.edit
        }
        this.handleProfilePic = this.handleProfilePic.bind(this);
        this.handleSave = this.handleSave.bind(this);
    }

    componentWillMount() {
        /**get all data of profile info */
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps) {
            this.setState({
                name: nextProps.name,
                profilePic: nextProps.pictureUrl
            })
        }
    }

    handleProfilePic (e) {
       this.setState({newProfilePic: e.currentTarget.value})
    }

    handleSave() {
        let newProfilePic = this.state.newProfilePic;
        this.setState({profilePic: newProfilePic, 
            uploadModal: false,
            newProfilePic: ""
        }, () => {
            let params = JSON.stringify({
                pictureUrl: this.state.profilePic
            })
            fetcingFactory(endpoints.EDIT_MUSICIAN, params).then(
                response => {
                    if(response.status===200){                   
                    }else{
                        response.json().then(
                            alert("Error")
                        )
                    }         
                })       
        });

    }

    render() {
        return (
        <React.Fragment>
             <Modal show={this.state.uploadModal} animation={false} onHide={ (e) => this.setState({showModal: false})}>
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
            title = {this.state.name} 
            style = {{width: 500, margin: "auto"}}>
                
                    <Row>
                        <Col md = {5}>
                            <Row>
                                <Avatar shape="square" size = {250} src = {this.state.profilePic}/>
                            </Row>
                            <br></br>
                            <Row>
                                {
                                    this.state.edit?
                                    <Button style ={{
                                fontFamily: 'sans-serif',
                                fontSize: "14px", 
                                fontWeight: "bold",
                                background: "#19aa8d",
                                borderRadius: "3px",
                                border: "none",
                                minWidth: "70px",
                                maxWidth: "100%",
                                outline: "none"
                            }} 
                                    onClick = {()=> this.setState({uploadModal: true})}>
                                        Change profile picture
                                    </Button> : null
                                }
                            </Row>
                        </Col>
                        <Col md={7}>
                            <DisplayInstruments instrumentIds = {this.props.instrumentList}></DisplayInstruments>
                        </Col>
                        </Row>
                
            </Card>
                   
                    
            
           
        </React.Fragment>
        )
    }
}