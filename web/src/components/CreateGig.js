import React from 'react';
import "./CreateGig.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import Geocode from 'react-geocode';
import DatePicker, { registerLocale} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {hr} from 'date-fns/locale';
registerLocale('hr', hr)
Geocode.setApiKey("AIzaSyDGe5vjL8wBmilLzoJ0jNIwe9SAuH2xS_0");



export default class CreateGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            eventName : "",
            selectedBandId : "",
            eventDesc : "",
            eventDate : new Date(),
            city: "Test",
            lat: 0,
            lng: 0,
            imageUrl: "",
            privateGig: false,
        }
        this.handleLocationChange = this.handleLocationChange.bind(this);
        this.updateLong = this.updateLong.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    

    handleSubmit = event => {
        event.preventDefault();
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }
    handleDateChange = date => {
        this.setState({
          eventDate: date
        });
      };

    handleInputChange = event => {
        this.setState(
            {[event.target.name] : event.target.checked}
             //, () => console.log(this.state.privateGig)
        );
    }
    handleLocationChange ({ position, city, places }) {

        // Set new location
        this.setState({ position, city, places });
      }

    updateLong() {
        this.setState({lng: this.state.city});
    }

    render() {
        return (
            <React.Fragment>
                 <div className="CreateGig">
                    <Form onSubmit={this.handleSubmit}>
                        <div className="col-2">
                            <Form.Label controlId="eventName"> Ime eventa: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="eventName">
                                <Form.Control autoFocus type="text" value={this.state.eventName}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>

                        <div className="col-2">
                            <Form.Label controlId="eventDesc"> Opis eventa: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="eventDesc">
                                <Form.Control autoFocus type="text" value={this.state.eventDesc}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>
                        <div className="col-2">
                            <Form.Label controlId="city"> Ime grada: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="city">
                                <Form.Control autoFocus type="text" value={this.state.city}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>

                        <h1>{this.state.city}</h1>
                        <h1>{this.state.lng}</h1>
                        <h1>{this.state.test}</h1>

                        <div>
                            <Form.Group>
                                <Button onClick={this.updateLong} block> Update long </Button>
                            </Form.Group>
                        </div>
                        
                        <div className="col-2">
                            <Form.Label> Vrijeme eventa: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group>
                                <DatePicker
                                    locale="hr"
                                    dateFormat="dd/MM/yyyy HH:mm"
                                    showTimeSelect
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    timeCaption="Vrijeme"
                                    selected={this.state.eventDate}
                                    onSelect={this.handleSelect}
                                    onChange={this.handleDateChange}
                                />
                            </Form.Group>
                        </div>
                        <div>
                            <Form.Group id="privateGig">
                                <Form.Check type="checkbox" class="check-space" label=" Je li ovo privatan gig?"/>
                            </Form.Group>
                        </div>
                        <div class="checkbox">
                            <label><input 
                                type="checkbox"
                                class="check-space"
                                name="privateGig"
                                checked={this.state.privateGig}
                                onChange={this.handleInputChange}
                                ></input>Je li privatan gig?</label>
                            </div>
                        <div nameClass="col-6">
                            <Form.Group>
                                <Button type="submit" block> Create Gig </Button>
                            </Form.Group>
                        </div>
                    </Form>
                </div>
            </React.Fragment>
        );
    }
}
