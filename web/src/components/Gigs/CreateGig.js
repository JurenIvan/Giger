import React from 'react';
import "./Gigs.css";
import { registerLocale} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Select from 'react-dropdown-select';
import {hr} from 'date-fns/locale';
import GeocodingForm from '../GeocodingForm';
import * as opencage from 'opencage-api-client';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import { Checkbox , DatePicker} from 'antd';
import 'antd/dist/antd.css';
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


    handleCreation(status) {
        if(status === 200) {
            window.location.href = '/';
        } else {
            this.setState({inValidCreation: true});
        }

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
            "gigName": this.state.eventName
        });
        console.log(params)
        fetcingFactory(endpoints.CREATE_GIG, params).then(
            response => {
                if (response.status === 200) {
                    window.location.href = "/home";
                } else {
                    console.log(response)
                    alert(response.json())
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
             , () => console.log(this.state.privateGig)
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
                , () => console.log(this.state.eventAddress)
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
                <div className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Create gig</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>

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
                                        <button type="submit" className="btn btn-primary btn-block btn-lg">Create Gig</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
            </React.Fragment>
        );
    }
}
