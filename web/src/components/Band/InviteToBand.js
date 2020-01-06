import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import Select from 'react-dropdown-select';

export default class InviteToBand extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bandId: "",
            selectedBand: "",
            bandName: "",
            bands: []
        }
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_BANDS_LEAD, "").then(
            response => response.json()
            ).then(response => {
                console.log(response.code)
                if(response.code === 40001){
                    if(response.violationErrors[0].code === 40003) {
                        alert("You are not a leader of any bands")
                    }
                }
                else {
                    for(let i=0; i<response.length;i++) {
                        this.setState(prevState => ({
                            bands: [...prevState.bands, {value: response[i].id, label: response[i].name}]
                          }))
                    }
                    console.log(this.state.bands)
                }
            })
    }

    setValues = selectedBand => {
        this.setState({isSearching: true});
        this.setState({ selectedBand }
        , () => console.log(this.state.selectedBand)
        )
    }

    render () {
        return (
            <React.Fragment>
                <div className = "InviteToGig">
                    <Form onSubmit={this.handleSubmit}>
                        <div className="col-2">
                            <Form.Label controlId="chooseBand"> Odaberi bend: </Form.Label>
                        </div>

                        <div className="col-6">
                            <Form.Group controlId="chooseBand">
                            <Select
                                disabled={this.state.isSearching}
                                name="selectedBand"
                                options={this.state.bands}
                                value={this.state.selectedBand}
                                //onChange={this.updateEventType}
                                onChange={value => this.setValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>

                        <div className="col-6">
                            <Form.Group>
                                <Button type="button" block onClick={this.handleGetMyGigs}> Dohvati svoje gigove </Button>
                            </Form.Group>
                        </div>

                        <div className="col-2">
                            <Form.Label controlId="gigName"> Odaberi gig: </Form.Label>
                        </div>
                        <div className="col-6">
                            <Form.Group controlId="gigName">
                            <Select
                                name="selectedGigName"
                                options={this.state.myGigs}
                                value={this.state.selectedGigName}
                                //onChange={this.updateEventType}
                                onChange={value => this.setValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>

                        <div nameClass="col-6">
                            <Form.Group>
                                <Button type="submit" block> Pozovi bend u gig </Button>
                            </Form.Group>
                        </div>
                    </Form>
                </div>
            </React.Fragment>
        );
    }

}