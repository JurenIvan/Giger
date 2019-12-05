import React from 'react';
import MetisMenu from 'react-metismenu';

export default class ProfileSideNav extends React.Component {
    /*constructor (props) {
        super(props);
    }*/
    content=[
        {
            icon: 'icon-class-name',
            label: "My Band",//this.props.myBandName,
            to: '/my_band',
        },
        {
            icon: 'icon-class-name',
            label: 'Settings',
            content: [
                {
                    icon: 'icon-class-name',
                    label: 'Edit my profile',
                    to: '/edit_profile',
                },
                {
                    icon: 'icon-class-name',
                    label: 'Delete profile',
                    to: '/delete_profile',
                },
            ],
        },
    ];
render() {
    return (
        <MetisMenu content={this.content} className="sideNav" />
    )
}


}