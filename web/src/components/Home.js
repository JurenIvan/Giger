import React from 'react';
import "../CSS/Home.css";
import Button from 'react-bootstrap/Button';
import * as Helpers from "./../Utils/HelperMethods"


export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shouldRenderHello: false
        }
        this.renderHelloWorld = this.renderHelloWorld.bind(this);
        this.procesHello = this.procesHello.bind(this);
    }
    
    renderHelloWorld() {
        Helpers.pingHelloWorld(this.procesHello);
    }

    procesHello(value) {
        console.warn(value);
        this.setState({shouldRender: value});
    }


    
    render() {
        return (
            <React.Fragment>
            <div>
            <Button variant="primary" onClick={this.renderHelloWorld}> Hello World</Button>
            </div>
        
            {
                this.state.shouldRender?
                <div>
                    HelloWorld
                </div> : null
            }
            &nbsp;

            <div>
                <Button href='/CreateGig'>Stvori gig</Button>
            </div>
            &nbsp;

            <div>
                <Button href='/EditGig'>Uredi gig</Button>
            </div>
            &nbsp;

            <div>
                 <Button href='/InviteToGig'>Pozovi bend u gig</Button>
            </div>
            &nbsp;

            <div>
                <Button href='/AcceptGigInvite'>Prihvati ili odbij poziv za gig</Button>
            </div>
            &nbsp;

            <div>
                <Button href='/InviteToBand'>Pozovi u bend</Button>
            </div>
            &nbsp;

            <div>
                <Button href='/AcceptBandInvite'>Prihvati ili odbij poziv u bend</Button>
            </div>
            &nbsp;
            </React.Fragment>
        )
    }
}
