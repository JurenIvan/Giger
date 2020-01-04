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
           profileType: "", //Enum.Musician/Organizer
           profilePic: "https://www.resumeviking.com/wp-content/uploads/2017/11/Software-Developer-Profile-Picture-Round.jpg"
        }
    }

    componentWillMount() {
        /**get all data of profile info */
    }

    render() {
        return (
        <div>
            <Row className="profileInfo">
                    <img src={this.state.profilePic} 
                        alt={this.state.userDetails.firstName + " " + this.state.userDetails.lastName}>
                    </img>
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
                <Form onSumbit = {this.handlePostSubmit}> 
                     <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Control as="textarea" rows="3" />
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