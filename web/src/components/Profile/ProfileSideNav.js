import React from 'react';
import MetisMenu from 'react-metismenu';

import {endpoints} from "../../Utils/Types"
import fetcingFactory from "../../Utils/external"
import {Navbar} from "react-bootstrap"

export default class ProfileSideNav extends React.Component {
    constructor (props) {
        super(props);

        this.state = {
            roles: [],
            content:  []
        }
        
        this.checkContents = this.checkContents.bind(this);
        
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_ROLES).then(
            response => {
                if (response.ok) {
                    return response.json()
                } else {
                    alert("Something went wrong!");
                }
            }
            ).then(
                json => {
                    if(json) {
                        let helperRoles = this.state.roles;
                        for (let i = 0; i < json.length; i++) {
                            helperRoles.push(json[i]);
                        }
                        this.setState({roles: helperRoles}, () => this.checkContents())
                    }
                }
            )
    }
    checkContents() {
        let contentHelper = [{
            icon: 'icon-class-name',
            label: "My Bands",
            to: 'viewBand',
        },
        {
            icon: 'icon-class-name',
            label: 'Settings',
            content: [
                {
                    label: 'Change profile type',
                    to: 'profile_change_type'
                }
            ],
        }];

        if (this.state.roles.includes("MUSICIAN")) {
            let musicianContent = {
                icon: 'icon-class-name',
                label: 'Musician profile',
                content: [
                    {
                        icon: 'icon-class-name',
                        label: 'View',
                        to: 'profile_musician_view'
                    },
                    {
                        icon: 'icon-class-name',
                        label: 'Edit',
                        to: 'profile_musician_edit'
                    }
                ]
            }

            contentHelper.push(musicianContent)
        }

        if (this.state.roles.includes("ORGANIZER")) {
            let organiserContent = {
                icon: 'icon-class-name',
                label: 'Organizer profile',
                content: [
                    {
                        icon: 'icon-class-name',
                        label: 'View',
                        to: 'profile_organizer_view'
                    },
                    {
                        icon: 'icon-class-name',
                        label: 'Edit',
                        to: 'profile_organizer_edit'
                    }
                ]
            }

            contentHelper.push(organiserContent)
        } else {
            console.log("includes organiser false");
            console.log(this.state)
        }
       

        this.setState({content: contentHelper}, ()=> console.log(this.state))
    }


    

    
render() {

    return (
        <MetisMenu content={this.state.content} className="sideNav" />
    )
}


}