import React from "react";

export default class BandView extends React.Component {
    constructor(props) {
        super(props);

        //this.state = {}
        
    }
    componentDidMount() {
        //get all about my band
    }

    render() {
        return (
            <React.Fragment>
                <div className = "row">
                 {this.state.BandName}
                </div>
            </React.Fragment>
        )
    }
}