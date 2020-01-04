import React from 'react';
import "./CreateGig.css";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
//import * as Helpers from '../Utils/HelperMethods'
//import Geocode from 'react-geocode';
import DatePicker, { registerLocale} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Select from 'react-dropdown-select';
import {hr} from 'date-fns/locale';
import GeocodingForm from './GeocodingForm';
//import GeocodingResults from './GeocodingResults';
import * as opencage from 'opencage-api-client';
import InputGroup from 'react-bootstrap/InputGroup'
registerLocale('hr', hr)



export default class CreateGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            eventName : "",
            eventDesc : "",
            eventDate : new Date(),
            eventAddress: "",
            lat: "",
            lng: "",
            imageUrl: "",
            privateGig: false,
            eventDuration: "",
            eventPrice: "",
            eventType: [
                {value: "WEDDING", label: "Svadba"},
                {value: "BIRTHDAY", label: "Rođendan"},
                {value: "BACHELORS_PARTY",label: "Momačka/djevojačka"},
                {value: "CONCERT", label: "Koncert"},
                {value: "OTHER", label: "Ostalo"}
            ],
            selectedEventType: "",
            query: '',
            apikey: 'd77313f368154e0d8313ae506740f103',
            isSubmitting: false,
            response: {},
            eventLocDesc: "",
            inValidCreation: false
        }
        this.handleTypeChange = this.handleTypeChange.bind(this);
        //this.getCoord = this.getCoord.bind(this);
        this.updateEventType = this.updateEventType.bind(this);
        this.setValues = this.setValues.bind(this);
        this.handleGeoSubmit = this.handleGeoSubmit.bind(this);
        this.handleGeoChange = this.handleGeoChange.bind(this);
        this.handleCreation = this.handleCreation.bind(this);
    }
/*
    getAddress() {
            // Get address from latidude & longitude.
            Geocode.setApiKey("AIzaSyCMFNBJGpzyBM0jKj0ekrF4iQUD7F21K04");
            Geocode.fromLatLng("48.8583701", "2.2922926").then(
                response => {
                this.state.address = response.results[0].formatted_address;
                console.log(this.state.address);
                },
                error => {
                console.error(error);
                }
    );
    }

    getCoord() {
            // Get latidude & longitude from address.
            Geocode.setApiKey("AIzaSyDGe5vjL8wBmilLzoJ0jNIwe9SAuH2xS_0");
            Geocode.fromAddress(this.state.address).then(
                response => {
                [this.state.lat, this.state.lng] = response.results[0].geometry.location;
                console.log(this.state.lat, this.state.lng);
                },
                error => {
                console.error(error);
                }
    );
    }
*/

    handleCreation(status) {
        if(status === 200) {
            window.location.href = '/';
        } else {
            this.setState({inValidCreation: true});
        }

    }

    /*handleSubmit = event => {
        event.preventDefault();
        (async () => {     
            await Helpers.sendCreateGigInfo(
                this.state.eventName, 
                this.state.eventDesc, 
                this.state.eventDate, 
                this.state.eventAddress,
                this.state.lat,
                this.state.lng,
                this.state.privateGig,
                this.state.eventDuration,
                this.state.eventPrice,
                this.state.selectedEventType,
                this.state.eventLocDesc,
                this.handleCreation
                );
        })();
    }*/

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }
    handlePriceChange = event => {
        this.setState({
            [event.target.id]: event.target.value.replace(/\D/,'')
        });
    }
    handleDateChange = date => {
        this.setState({eventDate: date}
            //, () => console.log(this.state.eventDate)
        );
      };

    handleTypeChange = event => {
        this.setState(
            {[event.target.name] : event.target.checked}
             //, () => console.log(this.state.privateGig)
        );
    }
    setValues = selectedEventType => {
        this.setState({ selectedEventType }
            //, () => console.log(this.state.selectedEventType)
        );
    }
    updateEventType = event => {
        this.setState(
            {[event.target.name]: event.target.value}
        //, () => console.log(this.state.selectedEventType)
        );

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
            this.setState({lat: response.results[0].geometry.lat}
                //, () => console.log(this.state.lat)
                );
            this.setState({lng: response.results[0].geometry.lng});
            this.setState({eventAddress: response.results[0].formatted}
                //, () => console.log(this.state.eventAddress)
                );
            })
            .catch(err => {
            console.error(err);
            this.setState({ response: {}, isSubmitting: false });
            });
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
                                <Form.Control autoFocus as="textarea" value={this.state.eventDesc}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>
                        <div className="col-2">
                            <Form.Label controlId="eventDuration"> Trajanje eventa: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="eventDuration">
                                <Form.Control autoFocus type="text" placeholder="Npr. 02:30h" value={this.state.eventDuration}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>
                        <div className="col-2">
                            <Form.Label controlId="eventPrice"> Cijena giga: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="eventPrice">
                                <InputGroup>
                                    <Form.Control autoFocus type="text" aria-describedby="eventPriceAppend" value={this.state.eventPrice}
                                                onChange={this.handlePriceChange}/>
                                </InputGroup>
                            </Form.Group>
                        </div>
                        <div className="col-2">
                            <GeocodingForm
                                apikey={this.state.apikey}
                                query={this.state.query}
                                isSubmitting={this.state.isSubmitting}
                                onSubmit={this.handleGeoSubmit}
                                onChange={this.handleGeoChange}
                            />
                        </div>
                        <h1>{this.state.eventAddress}</h1> 
                        <h1>{this.state.lat} {this.state.lng}</h1>                      

                        <div className="col-2">
                            <Form.Label controlId="gigType"> Tip giga: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="gigType">
                            <Select
                                name="selectedEventType"
                                options={this.state.eventType}
                                value={this.state.selectedEventType}
                                //onChange={this.updateEventType}
                                onChange={value => this.setValues(value[0].value, () => console.log(this.state.selectedEventType))}
                            />
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
                        <div class="checkbox">
                            <label><input 
                                type="checkbox"
                                class="check-space"
                                name="privateGig"
                                checked={this.state.privateGig}
                                onChange={this.handleTypeChange}
                                ></input>Je li privatan gig?</label>
                            </div>
                        <div nameClass="col-6">
                            <Form.Group>
                                <Button type="submit" block> Stvori gig </Button>
                            </Form.Group>
                        </div>
                    </Form>
                </div>
            </React.Fragment>
        );
    }
}
