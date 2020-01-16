import React from "react";
import Cookies from "js-cookie"

import ProfileSideNav from "./ProfileSideNav";
import ProfileInfo from "./ProfileInfo";
import ProfilePosts from "./ProfilePosts";
import {endpoints} from "../../Utils/Types";
import fetcingFactory from "../../Utils/external";

export default class MusicianProfile extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            id: Cookies.get("userId"),
            name: "",
            instrumentList: [],
            pictureUrl: "",
            contactNumber: ""
        }
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_MUSICIAN_BASIC, this.state.id).then(
            response => {
                if (response.ok) {
                    return response.json()
                } else {
                    alert("Something went wrong");
                }
            }
        ).then(
            json => {
                if(json) {
                    this.setState({
                        name: json.name,
                        instrumentList: json.instruments,
                        pictureUrl: json.pictureUrl,
                        contactNumber: json.contactNumber
                    }, ()=> console.log(this.state));
                }
            }
        )
    }

    render() {
        return (
            <div>
            <ProfileSideNav />
            <ProfileInfo edit = {this.props.edit} 
                name = {this.state.name} 
                pictureUrl = {this.state.pictureUrl} 
                instrumentList = {this.state.instrumentList}
                id = {this.state.id}
            />
            <ProfilePosts id = {this.state.id}
                name = {this.state.name} 
                pictureUrl = {this.state.pictureUrl} />
            </div>
        )
    }
}