import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";
import * as opencage from 'opencage-api-client';
import GeocodingForm from '../GeocodingForm';
export default class Band extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            bandBio: "",
            location: {
                y: "",
                x: "",
                address: "",
                extraDescription: ""
            },
            query: '',
            apikey: 'd77313f368154e0d8313ae506740f103',
            isSubmitting: false
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleGeoSubmit = this.handleGeoSubmit.bind(this);
        this.handleGeoChange = this.handleGeoChange.bind(this);
    }
    
    handleGeoChange(key, value) {
        this.setState({ [key]: value });
      }

    handleGeoSubmit(event) {
        event.preventDefault();
        this.setState({ isSubmitting: true });
        opencage
            .geocode({ key: this.state.apikey, q: this.state.query })
            .then(response => {
            //console.log(response);
            this.setState({ response, isSubmitting: false });
            let helperLocation = {
                x: response.results[0].geometry.lat,
                y: response.results[0].geometry.lng,
                address: response.results[0].geometry.formatted,
                extraDescription: ""
            }
            this.setState({location: helperLocation});
            })
            .catch(err => {
            console.error(err);
            this.setState({ response: {}, isSubmitting: false });
            });
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
                        <div>
                            Current location: 
                            {this.state.location.address}
                        </div>
                        <div className="col-12">
                            <GeocodingForm
                                apikey={this.state.apikey}
                                query={this.state.query}
                                isSubmitting={this.state.isSubmitting}
                                onSubmit={this.handleGeoSubmit}
                                onChange={this.handleGeoChange}
                            />
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