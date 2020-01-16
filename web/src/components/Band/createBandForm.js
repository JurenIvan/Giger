import React from "react";
import { Select, Input } from 'antd';
import './Bands.css'
import GeocodingForm from '../GeocodingForm';
import * as opencage from 'opencage-api-client';
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import {notification, Icon } from 'antd';

const { TextArea } = Input;
const { Option } = Select;

const openNotification = () => {
    notification.open({
      message: 'You have successfully created a band!',
      description:
        'You have created a band.\n Click Notification to redirect to Home',
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


export default class CreateBandForm extends React.Component{
    
    constructor(props){
        super(props)

        this.state={
            bandName: "",
            bandBio: "",
            pictureURL: "",
            acceptableGigTypes: [],
            gigTypes: [
                {value: "WEDDING", label: "Wedding"},
                {value: "BIRTHDAY", label: "Birthday"},
                {value: "BACHELORS_PARTY",label: "Bachelors party"},
                {value: "CONCERT", label: "Concert"},
                {value: "OTHER", label: "Other"}
            ],
            bandAddress: "",
            lat: "",
            lng: "",
            query: '',
            apikey: 'd77313f368154e0d8313ae506740f103',
            bandLocDesc: ""
        }
        this.handleGeoSubmit = this.handleGeoSubmit.bind(this);
        this.handleGeoChange = this.handleGeoChange.bind(this);
        this.handleCreateBand=this.handleCreateBand.bind(this)
        this.handleGigChange=this.handleGigChange.bind(this)
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
                , () => console.log(this.state.lat)
                );
            this.setState({lng: response.results[0].geometry.lng});
            this.setState({bandAddress: response.results[0].formatted}
                , () => console.log(this.state.bandAddress)
                );
            })
            .catch(err => {
            console.error(err);
            this.setState({ response: {}, isSubmitting: false });
            });
    }



    handleCreateBand(status) {
        if(status === 200) {
            window.location.href = '/';
        } else {
        this.setState({inValidRegister: true});
    }
    }   

    handleChange = event => {
        this.setState({
          [event.target.name]: event.target.value
        });
    }

    isBandNameAvailable = function(event){
        //TODO: check if band name is available
    }


    handleSubmit = event => {
        event.preventDefault();
        let x = this.state.lat;
        let y = this.state.lng;
        let address = this.state.bandAddress;
        let extraDescription = this.state.bandLocDesc;
        let location = {
            y,
            x,
            address,
            extraDescription
        };
        let params = JSON.stringify({
            "acceptableGigTypes": this.state.acceptableGigTypes,
            "bio": this.state.bandBio,
            "homeLocation": location,
            "name": this.state.bandName,
            "pictureUrl": this.state.pictureURL
        });
        console.log(params)
        fetcingFactory(endpoints.CREATE_BAND, params).then(
            response => {
                if (response.status === 200) {
                    openNotification();
                    
                } else {
                    console.log(response)
                    alert("error")
                }
            });
        
    }

    handleGigChange(value) {
        this.setState({acceptableGigTypes: value}, () => console.log(this.state.acceptableGigTypes))
    }

    render(){


        return(
            <React.Fragment>
                <div className="modal-login">
                        <div className="modal-content">

                            <div className="modal-header">				
                                <h4 className="modal-title">Create band</h4>
                            </div>

                            <div className="modal-body">
                                <form onSubmit={this.handleSubmit}>

                                <div className="form-group">
                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="bandName" placeholder="Band name" required="required">
                                            </input>
                                        </div>
                                </div>
                                <div>
                                <TextArea name="bandBio" placeholder="Biography" autoFocus type="textarea" value={this.state.bandBio}
                                                onChange={this.handleChange}required="required"/>
                                </div>
                                <div className="form-group">
                                        <div className="input-group">
                                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                                            <input type="text" 
                                            onChange={this.handleChange}
                                            className="form-control" name="pictureURL" placeholder="URL of your band picture" required="required">
                                            </input>
                                        </div>
                                </div>
                                <Select
                                    style={{width: "100%"}}
                                    mode="multiple"
                                    onChange={this.handleGigChange}
                                    placeholder="Select acceptable gig types"
                                    name="acceptableGigTypes"
                                    value={this.state.acceptableGigTypes?this.state.acceptableGigTypes:undefined}
                                >
                                        {this.state.gigTypes.map(item => (
                                            <Option key={item.value}>{item.label}</Option>
                                        ))}
                                </Select>
                                <br></br><br></br>
                                <GeocodingForm
                                        apikey={this.state.apikey}
                                        query={this.state.query}
                                        isSubmitting={this.state.isSubmitting}
                                        onSubmit={this.handleGeoSubmit}
                                        onChange={this.handleGeoChange}
                                    />
                                <div className="form-group">
                                    <button type="submit" className="btn btn-primary btn-block btn-lg">Create Band</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </React.Fragment>
        );
    }
}
