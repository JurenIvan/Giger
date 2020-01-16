import React from "react";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import Band from "./Band";
export default class BandView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            leaderBands: [],
            memberBands: []
        }
        
    }
    componentWillMount() {
        let leaderHelper = this.state.leaderBands;
        let memberHelper = this.state.memberBands;
        fetcingFactory(endpoints.GET_BAND, "my/leader").then(
            response => response.json()).then(
                json => {
                    for (let i= 0; i < json.length; i++){
                        let helperBand = {bandName: json[i].name, bandId: json[i].id, gigTypes: []};
                        for (let j = 0; j < json[i].gigTypes.length; j++) {
                            helperBand.gigTypes.push(json[i].gigTypes[j])
                        }
                        leaderHelper.push(helperBand)
                        console.log(leaderHelper)
                    }
                    this.setState({leaderBands: leaderHelper}, () =>  console.log(this.state.leaderBands))                   
                }
            )
            fetcingFactory(endpoints.GET_BAND, "my/member").then(
                response => response.json()).then(
                    json => {
                        for (let i= 0; i < json.length; i++){
                            let helperBand = {bandName: json[i].name, bandId: json[i].id, bandBio: json[i].bio, gigTypes: []};
                            for (let j = 0; j < json[i].gigTypes.length; j++) {
                                helperBand.gigTypes.push(json[i].gigTypes[j])
                            }
                            memberHelper.push(helperBand)
                            console.log(memberHelper)
                        }
                        this.setState({leaderBands: memberHelper}, () =>  console.log(this.state.leaderBands))                   
                    }
                )
        
        //get all about band
    }

    render() {
        return (
            <React.Fragment>
            <h1>Leader bands: </h1>
            <br></br>
            {this.state.leaderBands.map(element => {
                console.log("----------")
                console.log(element);
                return (
                    <div>
                        <Band title="Leader in bands:" bandId = {element.bandId} bandName = {element.bandName} gigTypes = {element.gigTypes} leader = {true}/>
                     </div>
                )
            })}

            <h1>Member bands: </h1>
            {this.state.memberBands.map(element => {
                return (
                    <div>
                        <Band title="Member in bands:" bandId = {element.bandId} bandName = {element.bandName} gigTypes = {element.gigTypes} leader = {false}/>
                    </div>
                )
            })}
                
            </React.Fragment>
        )
    }
}