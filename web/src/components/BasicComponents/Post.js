import React from "react";
import {Button, Col, Row} from "react-bootstrap";
import {CommentClass as Comment} from "./Comment";
import {Card, Avatar} from "antd";

export class PostClass extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            isCommentButtonClicked: false,
            comments: []
        };
        this.handleClick = this.handleClick.bind(this);
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
                }) : null }

            </div>
        )
    }
}