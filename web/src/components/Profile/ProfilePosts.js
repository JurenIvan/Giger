import React from "react";
import { Card } from 'antd';
import {PostClass} from "../BasicComponents/Post";
import {getTime} from "../../Utils/HelperMethods";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";


export default class ProfilePosts extends React.Component {
    
  
    
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.id,
            name: this.props.name,
            pictureUrl: this.props.pictureUrl,
           ProfilePostsList: [
            {
                id: "",
               content: "",
               publishedOn: "",
               comments: [
                   {
                    id: "",
                    content: "",
                    postedOn: "",
                    personPreviewDto: {
                        id: "",
                        name: "",
                        pictureUrl: ""
                    }
                   }
               ]
            }
           ]
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps) {
            this.setState({
                id: nextProps.id,
                name: nextProps.name,
                pictureUrl: nextProps.pictureUrl
            })
        }
    }

    componentWillMount() {
       fetcingFactory(endpoints.GET_MUSICIAN_POSTS, this.state.id).then(
           response => {
               if (response.ok) {
                   return response.json()
               } else {
                   alert("Something went wrong!")
               }
           }
       ).then(
           json => {
               if(json) {
                   console.log(json);
                   this.setState({ProfilePostsList: json}, () => console.log(this.state))
                } else {
                    return(
                        <Card>
                            <p>
                                User does not have posts.
                            </p>
                        </Card>
                    );
                }
               }
           
       )
    }

    /**
     * {
                    id: 1,
                    content: "Yeah",
                    postedOn: "2019-12-08T20:00:00",
                    personPreviewDto: {
                        id: 1,
                        name: "john.doe",
                    }
                }
     */
    render() {
        return (
            <div>
                {
                    this.state.ProfilePostsList.map(element => (
                            <PostClass 
                                postOwnerName = {this.state.name}
                                content= {element.content}
                                postedTime = {element.publishedOn}
                                postOwnerImg = {this.state.pictureUrl}
                            />
                    ))
                }
            </div>
           
        )
    }
}
