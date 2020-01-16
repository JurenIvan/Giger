import React from "react";
import {Button, Col, Row} from "react-bootstrap";
import {CommentClass as Comment} from "./Comment";
import {Card, Avatar} from "antd";
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";

export class PostClass extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            isCommentButtonClicked: false,
            comments: [],
            commentContent: ""
        };
        this.handleClick = this.handleClick.bind(this);
        this.handleSubmitComment = this.handleSubmitComment.bind(this);
        this.handleCommentChange = this.handleCommentChange.bind(this);
    };

    componentWillReceiveProps(nextProps) {
        if (nextProps.comments) {
            this.setState({comments: nextProps.comments})
        }
    }
    
    handleClick () {
        if (this.state.isCommentButtonClicked) {
            this.setState({isCommentButtonClicked: false});
        } else {
            this.setState({isCommentButtonClicked: true});
        }
        
    }
    handleCommentChange(value) {
        this.setState({commentContent: value})
    }

    handleSubmitComment() {
        let params = {
            content: this.state.commentContent
        }
        this.setState({commentContent: ""})
        fetcingFactory(endpoints.SUBMIT_COMMENT, JSON.stringify(params), this.props.id).then(
            response => {
                console.log(response);
                if(response.ok) {
                    this.props.updatePost();
                } else {
                    alert("Not ok!")
                }
            }
        )
    }
    render() {
        return (
            <Card style = {{width:550, marginBottom: "2px", backgroundColor:"darkcyan"}}>
            <Card title= {this.props.postOwnerName}
             extra = {this.props.postedTime}
             style = {{width: 500, marginBottom: 4}}
             >
                <Row>
                    <Col xs = {1}>
                        <Avatar size = {32} src={this.props.postOwnerImg}/>
                    </Col>
                    <Col>
                        {this.props.content}
                    </Col>
                </Row>
                <br></br>
               <Button variant="success"
               style= {{position: "absolute",
                right:    2,
                bottom:   2}}
               onClick = {this.handleClick}> Comment ({this.state.comments.length}) </Button>
               
            </Card>
            {this.state.isCommentButtonClicked? 
                this.state.comments.map(element => {
                    return (
                        <Comment content = {element}/>
                    )
                }) : null
            }

            {
                this.state.isCommentButtonClicked?
                <Row>
                <input className = "form-control"
                       style={{width:420, marginRight:5, marginLeft:22}}
                       placeholder="Write comment"
                       onChange = {(e) => {this.handleCommentChange(e.currentTarget.value)}} value = {this.state.commentContent}>
                </input>
                <Button onClick = {this.handleSubmitComment}style ={{
                  fontFamily: 'sans-serif',
                  fontSize: "16px",
                  fontWeight: "bold",
                  background: "#19aa8d",
                  borderRadius: "3px",
                  border: "none",
                  minWidth: "70px",
                  maxWidth: "50%",
                  outline: "none"
                }}
                > Submit </Button>
                </Row> : null
            }
            
                
            </Card>
        )
    }
}