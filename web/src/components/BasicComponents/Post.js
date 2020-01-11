import React from "react";
import {Button, Col, Row} from "react-bootstrap";
import {CommentClass as Comment} from "./Comment";
import {Card, Avatar} from "antd"

export class PostClass extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            currentUser: "",
            curentUserImgUrl: "",
            content: "",
            isCommentButtonClicked: false
        };
        this.handleClick = this.handleClick.bind(this);
    };
    
    handleClick () {
        if (this.state.isCommentButtonClicked) {
            this.setState({isCommentButtonClicked: false});
        } else {
            this.setState({isCommentButtonClicked: true});
        }
        
    }
    render() {
        return (
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
            </Card>
        )
    }
}