import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Select from 'react-dropdown-select';

export default class BandCreate extends React.Component {
  
    constructor(props) {
        super(props);
        this.state = {
            bandName :"",
            bandBio: "",
            acceptableGigTypes: [],
            homeLocation: {
                x: "",
                y: "",
                address: "",
                extraDescription: ""
            },
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
    }
    handleChange (event) {
        event.preventDefault();
        this.setState({
            [event.target.id]: event.target.value
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
                        <Form.Control value={this.state.password} onChange={this.bandBio} type="text" as ="textarea"/>
                    </Form.Group>
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