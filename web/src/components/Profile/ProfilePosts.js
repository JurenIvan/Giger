import React from "react";
import {PostClass} from "../BasicComponents/Post";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
import 'antd/dist/antd.css';
import {Card, Input} from "antd";

const { TextArea } = Input;
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
                     this.setState({ProfilePostsList: []})
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
            <div style = {{margin: 5}}>
                <br></br>
                <TextArea type="text"
                    onChange={this.handleChange}
                    id="postContent"
                    className="form-control"
                    value = {this.state.postContent} />
                <br></br>
                <button 
                    onClick = {this.handlePostSubmit}
                    style ={{
                        fontFamily: 'sans-serif',
                        fontSize: "16px", 
                        fontWeight: "bold",
                        background: "#19aa8d",
                        borderRadius: "3px",
                        border: "none",
                        minWidth: "140px",
                        maxWidth: "100%",
                        outline: "none"
                    }}
                    className="btn btn-primary btn-block btn-lg">
                    Post
                </button>
                <br></br>
                {
                    this.state.ProfilePostsList.length > 0? 
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
                )) : <Card style = {{textAlign: "center", borderBlockColor: "darkcyan"}}>
                        No posts.
                    </Card>
                }
            </div>
           
        )
    }
}
