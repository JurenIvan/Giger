import React from 'react';
import "./Gigs.css";
import GeocodingForm from '../GeocodingForm';
import * as opencage from 'opencage-api-client';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import { Checkbox , DatePicker, Select} from 'antd';
import 'antd/dist/antd.css';
import {notification, Icon } from 'antd';

const {Option} = Select;

const openNotification = () => {
    notification.open({
      message: 'You have successfully created a gig!',
      description:
        'You have created a gig, please make arrangements with a band to make it public.\n Click Notification to redirect to Home',
      icon: <Icon type="smile" style={{ color: '#108ee9' }} 
      />,
      duration: 7,
      onClick: () => {
        window.location.href = "/home";
      },
      onClose: () => {
        window.location.href = "/home";
      }

    });
}


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
                {value: "WEDDING", label: "Wedding"},
                {value: "BIRTHDAY", label: "Birthday"},
                {value: "BACHELORS_PARTY",label: "Bachelors party"},
                {value: "CONCERT", label: "Concert"},
                {value: "OTHER", label: "Other"}
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
        this.handleEChange = this.handleEChange.bind(this);
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
        let x = this.state.lat;
        let y = this.state.lng;
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
                    openNotification();
                    
                } else {
                    console.log(response)
                    alert("error")
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
    handleEChange(value) {
        this.setState({selectedEventType: value}, () => console.log(this.state.selectedEventType))
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
                                        onChange={this.handleEChange}
                                        placeholder="Select gig type"
                                        name="selectedEventType"
                                        value={this.state.selectedEventType?this.state.selectedEventType:undefined}
                                    >
                                            {this.state.eventType.map(item => (
                                                <Option key={item.value}>{item.label}</Option>
                                            ))}
                                    </Select>
                                    <br></br><br></br>
                                    <DatePicker
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
