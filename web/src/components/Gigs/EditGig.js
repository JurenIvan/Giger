import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import { registerLocale} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Select from 'react-dropdown-select';
import {hr} from 'date-fns/locale';
import GeocodingForm from '../GeocodingForm';
import * as opencage from 'opencage-api-client';
import InputGroup from 'react-bootstrap/InputGroup'
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import "./Gigs.css";
import { Checkbox , DatePicker} from 'antd';
import 'antd/dist/antd.css';
registerLocale('hr', hr)

export default class EditGig extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            eventName : "",
            eventDesc : "",
            eventDate : "",
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
            inValidCreation: false,
            myGigs: [],
            selectedGig: "",
            gigId: ""
        }
        this.handleTypeChange = this.handleTypeChange.bind(this);
        //this.getCoord = this.getCoord.bind(this);
        this.updateEventType = this.updateEventType.bind(this);
        this.setValues = this.setValues.bind(this);
        this.setGigValues = this.setGigValues.bind(this);
        this.handleGeoSubmit = this.handleGeoSubmit.bind(this);
        this.handleGeoChange = this.handleGeoChange.bind(this);
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_MY_GIGS, "my").then(
            response => response.json()
            ).then(response => {
                if(response.code === 40001) {
                    alert("You have to be an organizer")
                }
                else if (response.length === 0) {
                    alert("No gigs")
                }
                else {
                    console.log(response)
                    for(let i = 0; i < response.length; i++) {
                        this.setState(prevState => ({
                            myGigs: [...prevState.myGigs, {value: response[i], label: response[i].name}]
                          }))
                    }
                }   
            });
    }

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }
    handlePriceChange = event => {
        this.setState({
            [event.target.name]: event.target.value.replace(/\D/,'')
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

    setGigValues = selectedGig => {
        this.setState({ selectedGig }
            , () => {
            this.setState({
                eventName: selectedGig.name,
                eventDesc: selectedGig.description,
                lat: selectedGig.location.x,
                lng: selectedGig.location.y,
                eventAddress: selectedGig.location.address,
                eventLocDesc: selectedGig.location.extraDescription,
                eventDuration: selectedGig.expectedDuration,
                eventPrice: selectedGig.proposedPrice,
                selectedEventType: selectedGig.gigType,
                privateGig: selectedGig.privateGig,
                gigId: selectedGig.id
            }, () => console.log(this.state.eventName))

        })
        
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

    handleSubmit = event => {
        event.preventDefault();
        let y = this.state.lat;
        let x = this.state.lng;
        let address = this.state.eventAddress;
        let extraDescription = this.state.eventLocDesc;
        let location = {
            y,
            x,
            address,
            extraDescription
        };
        let params = JSON.stringify({
            "dateTime": this.state.eventDate,
            "location": location,
            "description": this.state.eventDesc,
            "expectedDuration": this.state.eventDuration,
            "proposedPrice": this.state.eventPrice,
            "gigType": this.state.selectedEventType,
            "privateGig": this.state.privateGig,
            "gigName": this.state.eventName,
            "id": this.state.gigId
        });
        console.log(params)
        fetcingFactory(endpoints.EDIT_GIG, params, this.state.gigId).then(
            response => {
                if (response.status === 200) {
                    window.location.href = "/home";
                } else {
                    console.log(response)
                    alert(response.json())
                }
            });
    }

    render() {
        return(
            <React.Fragment>
                <div className="modal-login">
                    <div className="modal-content">
                        <div className="modal-header">				
                            <h4 className="modal-title">Edit gig</h4>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={this.handleSubmit}>
                                <Select
                                        disabled={this.state.isSearching}
                                        name="selectedGig"
                                        options={this.state.myGigs}
                                        value={this.state.selectedGig}
                                        placeholder="Choose gig"
                                        //onChange={this.updateEventType}
                                        onChange={value => this.setGigValues(value[0].value)}
                                />
                                <br></br>

                                <div className="form-group">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handleChange}
                                        className="form-control" name="eventName" placeholder="Event name" required="required">
                                        </input>
                                    </div>
                                </div>

                                <div className="form-group">        
                                <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handleChange}
                                        className="form-control" name="eventDesc" placeholder="Event description" required="required">
                                        </input>
                                    </div>
                                </div>

                                <div className="form-group">        
                                <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handleChange}
                                        className="form-control" name="eventDuration" placeholder="Event duration(example: 1:30h)" required="required">
                                        </input>
                                    </div>
                                </div>                                  


                                <div className="form-group">

                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handlePriceChange}
                                        className="form-control" name="eventPrice" placeholder="Contract price"  value={this.state.eventPrice} required="required">
                                        </input>
                                    </div>
                                </div>

                                <GeocodingForm
                                    apikey={this.state.apikey}
                                    query={this.state.query}
                                    isSubmitting={this.state.isSubmitting}
                                    onSubmit={this.handleGeoSubmit}
                                    onChange={this.handleGeoChange}
                                />
                                
                                <Select
                                    name="selectedEventType"
                                    options={this.state.eventType}
                                    value={this.state.selectedEventType}
                                    placeholder="Select gig type"
                                    //onChange={this.updateEventType}
                                    onChange={value => this.setValues(value[0].value, () => console.log(this.state.selectedEventType))}
                                />
                                <br></br>
                                <DatePicker
                                    locale="hr"
                                    dateFormat="dd/MM/yyyy HH:mm"
                                    showTime
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    placeholder="Select date and time"
                                    selected={this.state.eventDate}
                                    onSelect={this.handleSelect}
                                    onChange={this.handleDateChange}
                                />
                                <br></br><br></br>
                                <Checkbox onChange={this.handleTypeChange} name="privateGig" checked={this.state.privateGig}>Private gig?</Checkbox>
                                <br></br><br></br>
                                <div className="form-group">
                                    <button type="submit" className="btn btn-primary btn-block btn-lg">Edit gig</button>
                                </div>
                                </form>
                             </div>
                        </div>
                    </div>
            </React.Fragment>
        )
    }
}