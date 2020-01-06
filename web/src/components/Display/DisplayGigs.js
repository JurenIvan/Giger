import React, { Component } from 'react'
import {BandList} from "../Display/BandList"
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types"


export class DisplayBands extends React.Component{

    constructor(props)
    {
        super(props);

    }

    render()
    {
        return(
            <div>
                <GigList/>
            </div>
        )
    }
}