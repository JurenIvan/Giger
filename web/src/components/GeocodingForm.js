import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import './GeocodingForm.css'

class GeocodingForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLocating: false,
    };
    this.handleGeoLocation = this.handleGeoLocation.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = props.onSubmit;
  }
  handleGeoLocation() {
    const geolocation = navigator.geolocation;
    const p = new Promise((resolve, reject) => {
      if (!geolocation) {
        reject(new Error('Not Supported'));
      }
      this.setState({
        isLocating: true,
      });
      geolocation.getCurrentPosition(
        position => {
          console.log('Location found');
          resolve(position);
        },
        () => {
          console.log('Location : Permission denied');
          reject(new Error('Permission denied'));
        }
      );
    });
    p.then(location => {
      this.setState({
        isLocating: false,
      });
      this.props.onChange(
        'query',
        location.coords.latitude + ', ' + location.coords.longitude
      );
    });
  }
  handleInputChange(event) {
    const { target } = event;
    const { name } = target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    this.props.onChange(name, value);
  }
  handleSubmit(event) {
    console.log('Form was submitted with state: ', this.state);
    event.preventDefault();
  }
  render() {
    const { isSubmitting, query } = this.props;
    const { isLocating } = this.state;
    return (
        <React.Fragment>
            <div className="GeocodingForm">
                <Form onSubmit={e => {e.preventDefault();}}>
                    <div className="form-group">
                        <div className="input-group">
                            <span className="input-group-addon"><i className="fa fa-user"></i></span>
                            <input type="text" 
                            onChange={this.handleInputChange}
                            value={query}
                            className="form-control" name="query" placeholder="City, Country" required="required">
                            </input>
                        </div>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary btn-block btn-lg" 
                        disabled={isLocating || isSubmitting} onClick={this.handleSubmit}>Get location</button>
                    </div>
                </Form>
            </div>
        </React.Fragment>
      
    );
  }
}
export default GeocodingForm;