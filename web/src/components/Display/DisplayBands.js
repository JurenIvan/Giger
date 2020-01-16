import React from 'react'
import {BandList} from "../Display/BandList"


export class DisplayBands extends React.Component{

    constructor(props)
    {
        super(props);

        this.state = {

            bands: [
                "ACDC",
                "Beatles",
                "Beach boys"
              ],
            //dohvat liste bendova
           //BandList:
        };
    }

    render()
    {
        return(
            <div>
                <BandList/>
            </div>
        )
    }
}