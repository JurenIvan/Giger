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
        fetcingFactory(endpoints.SUBMIT_COMMENT + this.props.id, JSON.stringify(params)).then(
            response => {
                if(response.ok) {
                    alert("OK")
                } else {
                    alert("Not ok!")
                }
            }
        )
    }
    render() {
        return (
            <div style = {{marginBottom: "2px"}}>
            <Card title= {this.props.postOwnerName}
             extra = {this.props.postedTime}
             style = {{width: 500}}
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
                <input className="form-control" onChange = {(e) => {this.handleCommentChange(e.currentTarget.value)}} value = {this.state.commentContent}>
                </input>
                <Button onClick = {this.handleSubmitComment} 
                style = {{position: "relative", bottom: 2, right: 2}}> Submit comment </Button>
                </Row> : null
            }
            
                
            </div>
        )
    }
}