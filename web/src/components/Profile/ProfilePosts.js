import React from "react";
import { Card } from 'antd';
import {PostClass} from "../BasicComponents/Post";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import {Row, Button} from "react-bootstrap"


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
        this.handlePostSubmit = this.handlePostSubmit.bind(this);
        this.getProfilePosts = this.getProfilePosts.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    getProfilePosts() {
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
                    let jsonList = json;
                    let sortedList = jsonList.reverse()
                    this.setState({ProfilePostsList: sortedList}, () => console.log(this.state))
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
       this.getProfilePosts();
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
    handlePostSubmit = event => {
        let params = {
            content: this.state.postContent
        }
        this.setState({postContent : ""})
        console.log(params)
        fetcingFactory(endpoints.SUBMIT_USER_POST, JSON.stringify(params)).then(
            response => {
                console.log(response);
                if (response.ok) {
                    this.getProfilePosts();
                } else {
                    alert("Post creation failed")
                }
            }
        )
        
    }

    handleChange = event => {
        event.preventDefault();
        this.setState({ postContent: event.target.value});
    }
    render() {
        return (
            <div>
            <Row className="profileInfo">
            <textarea type="text"
                onChange={this.handleChange}
                 id="postContent"
                 className="form-control"
                 value = {this.state.postContent}></textarea>
                <Button block type="submit" onClick = {this.handlePostSubmit}
                style = {{width:500 , margin: "5px"}}>
                    Post
                </Button>
        </Row>
                {
                    this.state.ProfilePostsList.map(element => (
                            <PostClass 
                                id = {element.id}
                                postOwnerName = {this.state.name}
                                content= {element.content}
                                postedTime = {element.publishedOn}
                                postOwnerImg = {this.state.pictureUrl}
                                comments = {element.comments}
                                updatePost = {this.getProfilePosts}
                            />
                    ))
                }
            </div>
           
        )
    }
}
