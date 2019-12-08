import * as React from 'react';
import { Col, Row, Form, Button } from 'react-bootstrap';

export default class ProfileInfo extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
           userDetails: {
               firstName: "John",
               lastName: "Doe",
               description: "", 
           },
           postContent: "",
           profileType: "", //Enum.Musician/Organizer
           profilePic: "https://www.resumeviking.com/wp-content/uploads/2017/11/Software-Developer-Profile-Picture-Round.jpg"
        }
        this.handleUpload = this.handleUpload.bind(this);
        this.handlePostSubmit = this.handlePostSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentWillMount() {
        /**get all data of profile info */
    }

    handleUpload (e) {
        const file = Array.from(e.target.files)
        //const formData = new FormData()
        alert(file[0].name);
    }
    handlePostSubmit = event => {
        event.preventDefault();
        alert(this.state.postContent);
    }

    handleChange = event => {
        this.setState({ postContent: event.target.value});
    }

    render() {
        return (
        <div>
            <Row className="profileInfo">
                   <div>
                    <Row>
                        <img src={this.state.profilePic} 
                                alt={this.state.userDetails.firstName + " " + this.state.userDetails.lastName}>
                        </img>
                    </Row>
                    <Row className="uploader">
                        <label for="file-upload" class="file-uploader">
                            Upload profile picture...
                        </label>
                        <input type="file" id="file-upload"></input>
                    </Row>
                   </div>
                    
                    
                    <Col style={{marginLeft: "1vw"}}>
                    <Row style={{fontSize: "38px"}}>
                            {this.state.userDetails.firstName} {this.state.userDetails.lastName}
                        </Row>
                        <Row>
                            This here is my description!
                        </Row>
                        <Row>
                            And I play this instrument(s) in band(s): -----
                            <br></br>
                            /
                            <br></br>
                            I am Organizer
                        </Row>
                    </Col>
            </Row>
            <Row className="profileInfo">
                <Form onSubmit = {this.handlePostSubmit}> 
                     <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Control as="textarea" rows="3" placeholder="Write post..." onChange = {this.handleChange} value = {this.state.postContent}/>
                     </Form.Group>
                    <Button block type="submit">
                        Post
                    </Button>
                </Form> 
            </Row>
        </div>
        )
    }
}