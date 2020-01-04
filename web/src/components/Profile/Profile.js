import * as React from 'react';
import ProfileInfo from '../BasicComponents/ProfileInfo';
import ProfileSideNav from './ProfileSideNav';
import ProfilePosts from './ProfilePosts'
import './Profile.css';

export default class ProfileClass extends React.Component {
    
    constructor(props) {
        super(props);

        this.state = {
            /** */
        }
    };

    render() {
        return (
            <div className="profile">
                <ProfileSideNav />
                <ProfileInfo />
                <ProfilePosts />
            </div>
        )
    }

    

}