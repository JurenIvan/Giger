import * as React from 'react';
import ProfileInfo from '../Profile/ProfileInfo';
import ProfileSideNav from './ProfileSideNav';
import ProfilePosts from './ProfilePosts';
import BasicProfile from "./BasicProfile";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";

export default class ProfileClass extends React.Component {
    
    constructor(props) {
        super(props);

        this.state = {
            name: "",
            pictureUrl: "",
        }

    };
    componentDidMount() {
        fetcingFactory(endpoints.GET_USER_INFO, this.props.id).then(
            response => {
                if (response.ok) {
                    return response.json();
                } else {
                    alert("Something went wrong");
                }
            }
        ).then(
            json => {
                if (json) {
                    this.setState({name: json.name, pictureUrl: json.pictureUrl})
                }
            }
        )



    }
    render() {
        return (
            <React.Fragment>
                <ProfileSideNav />
                <BasicProfile name={this.state.name} pictureUrl = {this.state.pictureUrl}/>
            </React.Fragment>
        )
    }

    

}