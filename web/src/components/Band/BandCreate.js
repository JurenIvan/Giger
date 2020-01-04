import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Select from 'react-dropdown-select';
import fetcingFactory from "../../Utils/external";
import * as Types from "../../Utils/Types";
import * as opencage from 'opencage-api-client';
import GeocodingForm from '../GeocodingForm';

export default class BandCreate extends React.Component {
  
    constructor(props) {
        super(props);
        this.state = {
            bandName :"",
            bandBio: "",
            acceptableGigTypes: [],
            y: "",
            x: "",
            address:"",
            extraDescription:"",
            query: '',
            apikey: 'd77313f368154e0d8313ae506740f103',
            isSubmitting: false,
            eventType: [
                {value: "WEDDING", label: "Svadba"},
                {value: "BIRTHDAY", label: "Rođendan"},
                {value: "BACHELORS_PARTY",label: "Momačka/djevojačka"},
                {value: "CONCERT", label: "Koncert"},
                {value: "OTHER", label: "Ostalo"}
            ]
        }

        this.handleChange = this.handleChange.bind(this);
        this.setValues = this.setValues.bind(this);
        this.handleSelection = this.handleSelection.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleGeoSubmit = this.handleGeoSubmit.bind(this);
        this.handleGeoChange = this.handleGeoChange.bind(this);
    }
    handleChange (event) {
        event.preventDefault();
        this.setState({
            [event.target.id]: event.target.value
        });
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
            this.setState({y: response.results[0].geometry.lat}
                //, () => console.log(this.state.y)
                );
            this.setState({x: response.results[0].geometry.lng});
            this.setState({address: response.results[0].formatted}
                //, () => console.log(this.state.address)
                );
            })
            .catch(err => {
            console.error(err);
            this.setState({ response: {}, isSubmitting: false });
            });
    }

    setValues = selectedEventType => {
        this.setState({ selectedEventType }
            //, () => console.log(this.state.selectedEventType)
        );
    }
    handleSelection(event) {
        let helperArray = this.state.acceptableGigTypes;
        if (event.target.checked) {
            helperArray.push(event.target.id)
            console.log(event.target.id)
        } else if (!event.target.checked && helperArray.includes([event.target.id])) {
            let removingIndex = helperArray.indexOf([event.target.id]);
            helperArray.splice(removingIndex, 1);
            console.log(helperArray);
        }
        this.setState({acceptableGigTypes : helperArray}, ()=>  console.log(this.state.acceptableGigTypes))
    }

    handleSubmit(event) {
        event.preventDefault();
        let y = this.state.y;
        let x = this.state.y;
        let address = this.state.address;
        let extraDescription = this.state.extraDescription;
        let location = {
            y,
            x,
            address,
            extraDescription
        }
        let params = {
            "name": this.state.bandName,
            "bio": this.state.bandBio,
            "pictureUrl": "",
            "acceptableGigTypes": this.state.acceptableGigTypes,
            "homeLocation": this.state.homeLocation
           }
           console.log(params)
        fetcingFactory(Types.endpoints.CREATE_BAND, JSON.stringify(params)).then(
            response => {
               if (response.ok) {
                   window.location.href = "/home";
               } else {
                   alert("Band creation failed")
               }
               console.log(response)
            }
        )
    }
    render() {
        return (
            <React.Fragment>
            <Form onSubmit={this.handleSubmit}>
                <div className="col-1">
                    <Form.Label controlId="bandName"> Band name: </Form.Label>
                </div>
                <div className="col-6">
                    <Form.Group controlId="bandName">
                        <Form.Control autoFocus type="text" value={this.state.bandName}
                                    onChange={this.handleChange}/>
                    </Form.Group>
                </div>

                <div className="col-2">
                    <Form.Label controlId="bandBio"> Biography: </Form.Label>
                </div>
                <div className="col-6">
                    <Form.Group controlId="bandBio">
                        <Form.Control value={this.state.bandBio} onChange={this.handleChange} type="text" as ="textarea"/>
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

                <div className="col-6">
                   {this.state.eventType.map(element => {
                    return (
                        <div>
                        <input type="checkbox" id={element.value} onChange = {(e) => {this.handleSelection(e)}} />
                        <label>{element.label}</label>
                        </div>
                    )
                })}
                </div>
                
                <Button type="submit" block> Create Band </Button>
            </Form>
            </React.Fragment>
        )
    }
}