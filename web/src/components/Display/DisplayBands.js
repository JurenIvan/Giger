import React, { Component } from 'react'
import {BandList} from "../Display/BandList"
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types"


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