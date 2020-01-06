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
            </React.Fragment>
        )
    }
}
