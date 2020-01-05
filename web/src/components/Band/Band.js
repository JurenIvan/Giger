import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";
export default class Band extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            bandBio: "",
            bandLocation: {
                x: "",
                y: "",
                address: "",
                extraDescription: ""
            }
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    componentDidMount() {
        fetcingFactory(endpoints.GET_BAND, this.props.bandId).then(
            response=>{
                return response.json()
            }
        ).then(
            json =>{
                console.log("********")
                console.log(json);
                this.setState({bandBio: json.bio, bandLocation: json.location}, () => console.log(this.state))
            }
        )
    }
    handleEdit() {
        this.setState({showModal: true})
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleSubmit = event => {
        event.preventDefault();

    }
    render() {
        return (
            <React.Fragment>
            <Modal show={this.state.showModal} animation={false}>
                <Modal.Body>
                    <Form onSubmit = {this.handleSubmit}>
                        <div className="col-3">
                            <Form.Label controlId="bandBio"> Biography: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="bandBio">
                                <Form.Control autoFocus type="text" value={this.state.bandBio}
                                                onChange={this.handleChange}/>
                            </Form.Group>
                        </div>
                    </Form>
                </Modal.Body>
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
                <div>
                    <div className = "row">
                    {this.props.bandName}
                    </div>
                    <div>
                    Gig Types: 
                    {this.props.gigTypes.map(
                        element => {
                            return(
                                <div>
                                    {element}
                                </div>
                            )
                        }
                    )}
                    {this.props.leader? 
                    <Button onClick = {this.handleEdit}> Edit</Button> : null}
                    </div>
                </div>
            </React.Fragment>
        )
    }
}