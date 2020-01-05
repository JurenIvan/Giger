import React, { Component } from 'react'
import {BandList} from "../Display/BandList"


export class DisplayBands extends React.Component{

    constructor(props)
    {
        super(props);

        this.state = {

            //dohvat liste bendova
           //BandList:
          Bands : []
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