import React from 'react';
import { registerLocale} from "react-datepicker";
import {hr} from 'date-fns/locale';
import GeocodingForm from '../GeocodingForm';
import * as opencage from 'opencage-api-client';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import "./Gigs.css";
import { Checkbox , DatePicker, Select, notification, Icon} from 'antd';
import 'antd/dist/antd.css';
import moment from 'moment';
registerLocale('hr', hr)

const {Option} = Select;

const openNotification = () => {
    notification.open({
      message: 'You have successfully edited a gig!',
      description:
        'You have edited a gig, please make arrangements with a band to make it public.\n Click Notification to redirect to Home',
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
            idArray: [],
            selectedGig: "",
            gigId: ""
        }
        this.handleTypeChange = this.handleTypeChange.bind(this);
        this.handleGChange = this.handleGChange.bind(this);
        this.handleEChange = this.handleEChange.bind(this);
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
                    this.setState({myGigs: response})
                    for(let i = 0; i < response.length; i++) {
                        this.setState(prevState => ({
                            idArray: [...prevState.idArray, {value: response[i].id, label: response[i].name}]
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
    handleGChange(value) {
        this.setState({selectedGig: value}
            , () => {
                for(let i = 0; i<this.state.idArray.length; i++) {
                    if(this.state.idArray[i].value.toString() === this.state.selectedGig) {
                        this.setState({
                            eventName: this.state.myGigs[i].name,
                            eventDesc: this.state.myGigs[i].description,
                            lat: this.state.myGigs[i].location.x,
                            lng: this.state.myGigs[i].location.y,
                            eventAddress: this.state.myGigs[i].location.address,
                            eventLocDesc: this.state.myGigs[i].location.extraDescription,
                            eventDuration: this.state.myGigs[i].expectedDuration,
                            eventPrice: this.state.myGigs[i].proposedPrice,
                            selectedEventType: this.state.myGigs[i].gigType,
                            privateGig: this.state.myGigs[i].privateGig,
                            gigId: this.state.myGigs[i].id,
                            eventDate: this.state.myGigs[i].dateTime,
                            query: this.state.myGigs[i].location.address,
                            eventDate: this.state.myGigs[i].dateTime
                        }, () => console.log(this.state.eventDate))
                    }
                }
        })
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
            "gigName": this.state.eventName,
            "id": this.state.gigId
        });
        console.log(params)
        fetcingFactory(endpoints.EDIT_GIG, params, this.state.gigId).then(
            response => {
                if (response.status === 200) {
                    openNotification();
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
                                    onChange={this.handleGChange}
                                    placeholder="Select gig to edit"
                                    name="selectedGig"
                                    value={this.state.selectedGig?this.state.selectedGig:undefined}
                                >
                                        {this.state.idArray.map(item => (
                                            <Option key={item.value}>{item.label}</Option>
                                        ))}
                                </Select>
                                <br></br><br></br>

                                <div className="form-group">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handleChange}
                                        className="form-control" name="eventName" 
                                        value={this.state.eventName} placeholder="Event name" required="required">
                                        </input>
                                    </div>
                                </div>

                                <div className="form-group">        
                                <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handleChange}
                                        className="form-control" 
                                        name="eventDesc" value={this.state.eventDesc} placeholder="Event description" required="required">
                                        </input>
                                    </div>
                                </div>

                                <div className="form-group">        
                                <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                        <input type="text" 
                                        onChange={this.handleChange}
                                        className="form-control" 
                                        name="eventDuration" placeholder="Event duration(example: 1:30h)" 
                                        value={this.state.eventDuration} required="required">
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
                                    locale="hr"
                                    dateFormat="dd/MM/yyyy HH:mm"
                                    showTime
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    placeholder="Select date and time"
                                    selected={this.state.eventDate}
                                    onSelect={this.handleSelect}
                                    onChange={this.handleDateChange}
                                    value={moment(this.state.eventDate)}
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