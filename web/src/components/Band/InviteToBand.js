 
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
            musicians: [],
            selectedBand: "",
            musicianName: "",
            bands: [],
            selectedMusician: "",
            isSearching: false,
            main: true
        }
        this.handleRadioChange = this.handleRadioChange.bind(this)
    }

    componentDidMount() {
        this.setState({isSearching: true})
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
            }).then( () => 
        this.setState({isSearching: false}))
    }

    setBandValues = selectedBand => {
        this.setState({ selectedBand }
        , () => console.log(this.state.selectedBand)
        )
    }

    setMusicianValues = selectedMusician => {
        this.setState({ selectedMusician}
        , () => console.log(this.state.selectedMusician)
        )
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        }
        );
    }

    handleGetMusician = event => {
        event.preventDefault();
        console.log(this.state.musicianName)
        this.setState({isSearching: true})
        fetcingFactory(endpoints.GET_MUSICIAN, JSON.stringify({"userName": this.state.musicianName})).then(
            response => response.json()
            ).then(response => {
                if (response.length === 0) {
                    alert("No musician with that name")
                }
                else {
                    //console.log(response)
                    this.setState({musicians: []})
                    for(let i = 0; i < response.length; i++) {
                        this.setState(prevState => ({
                            musicians: [...prevState.musicians, {value: response[i].id, label: response[i].username}]
                          }))
                    }
                    console.log(this.state.musicians)
                }   
            }).then( () => 
            this.setState({isSearching: false}))
    }

    handleSubmit = event => {
        event.preventDefault();
        let params = JSON.stringify({
            "bandId": this.state.selectedBand,
            "musicianId": this.state.selectedMusician
        });
        console.log(params)
        if(this.state.main === true){
        fetcingFactory(endpoints.INVITE_MAIN_MEMB, params).then(
            response => {
                if (response.status === 200) {
                    window.location.href = "/home";
                } else {
                    console.log(response)
                    alert(response.json())
                }
            });
        }
        else {
            fetcingFactory(endpoints.INVITE_BACKUP_MEMB, params).then(
                response => {
                    if (response.status === 200) {
                        window.location.href = "/home";
                    } else {
                        console.log(response)
                        alert(response.json())
                    }
                });
        }
    }

    handleRadioChange(event) {
        const main = event.currentTarget.value === 'true' ? true: false;
        this.setState({ main }, () => console.log(this.state.main)
            );
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
                                onChange={value => this.setBandValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>



                        <div className="col-2">
                            <Form.Label controlId="musicianName"> Unesi ime glazbenika: </Form.Label>
                        </div>

                        <div className="col-6">
                            <Form.Group controlId="musicianName">
                                <Form.Control autoFocus type="text" value={this.state.musicianName}
                                              onChange={this.handleChange}/>
                            </Form.Group>
                        </div>

                        <div className="col-6">
                            <Form.Group>
                                <Button type="button" block disabled={this.state.isSearching} onClick={this.handleGetMusician}> Dohvati glazbenike </Button>
                            </Form.Group>
                        </div>

                        <div className="col-2">
                            <Form.Label controlId="chooseMusician"> Odaberi glazbenika: </Form.Label>
                        </div>

                        <div className="col-6">
                            <Form.Group controlId="chooseMusician">
                            <Select
                                disabled={this.state.isSearching}
                                name="selectedMusician"
                                options={this.state.musicians}
                                value={this.state.selectedMusician}
                                //onChange={this.updateEventType}
                                onChange={value => this.setMusicianValues(value[0].value)}
                            />
                            </Form.Group>
                        </div>

                        <div className="col-6">
                            <label><input 
                                type="radio"
                                name="main"
                                value="true"
                                checked={this.state.main === true}
                                onChange={this.handleRadioChange}
                                ></input>Glavni clan</label>
                                <label><input 
                                type="radio"
                                name="main"
                                value="false"
                                checked={this.state.main === false}
                                onChange={this.handleRadioChange}
                                ></input>Pomocni clan</label>
                        </div>

                        <div nameClass="col-6">
                            <Form.Group>
                                <Button type="submit" disabled={this.state.isSearching} block> Pozovi osobu u bend </Button>
                            </Form.Group>
                        </div>
                    </Form>
                </div>
            </React.Fragment>
        );
    }

}
