import React, { Component } from 'react'
import {GigList} from "../Display/GigList"



export class DisplayGigs extends React.Component{

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