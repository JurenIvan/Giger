import React, { Component } from 'react'
import {BandList} from "../Display/BandList"


export class DisplayBands extends React.Component{

    constructor(props)
    {
        super(props);

        this.state = {

            //dohvat liste bendova
           //BandList:
           BandListTest: [
            "ABBA",
            "Brkovi",
            "Daleka obala",
            "Prljavo kazaliste",
            "Parni valjak"
          ]
        };
    }

    render()
    {
        return(
            <div>
                <BandList items={this.state.BandListTest}/>
            </div>
        )
    }
}