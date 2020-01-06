import React from "react";
import { Card } from 'react-bootstrap';
import {PostClass} from "../BasicComponents/Post";
import {PostClass as Post} from "../BasicComponents/Post";
import {getTime} from "../../Utils/HelperMethods";

export default class ProfilePosts extends React.Component {
    
  
    
    constructor(props) {
        super(props);

        this.state = {
           userDetails: {
               firstName: "John",
               lastName: "Doe",
               description: "", 
           },
           profilePic: "https://www.resumeviking.com/wp-content/uploads/2017/11/Software-Developer-Profile-Picture-Round.jpg",
           ProfilePostsList : [
                {
                    content: "This is test content 1",
                },
                {
                    content: "This is test content 2",
                },
                {
                    content: "This is test content 3",
                },
                {
                    content: "This is test content 4",
                }
            ]
        }
    }

    componentWillMount() {
        //fetch current user / provuc kroz propove?
    }
    render() {
        return (
            <div>
                {
                    this.state.ProfilePostsList.map(element => (
                        <Card className="profilePosts">
                            <PostClass 
                                postOwnerName = {"TestMap"}
                                content= {element.content}
                                postedTime = {getTime()}
                                usersPostImgUrl = {this.state.profilePic}
                            />
                       </Card>
        ))
                }
            </div>
           
        )
    }
}
