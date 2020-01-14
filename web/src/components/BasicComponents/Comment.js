import * as React from "react";
import * as Helpers from "../../Utils/HelperMethods";
import {Card, Avatar} from "antd";
import {Row, Col} from "react-bootstrap";

export class CommentClass extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user : "TestUser",
            time : Helpers.getTime(),
            content : "Test comment content",
            userPicUrl : ""
        };
    }

    render() {
        return (
            <Card title = {this.props.content.personPreviewDto.name} extra = {this.props.content.postedOn}>
            <Row>
            <Col xs = {1}>
                <Avatar size = {16} src={this.props.content.personPreviewDto.pictureUrl}/>
            </Col>
            <Col>
                {this.props.content.content}
            </Col>
        </Row>
            </Card>
        )
    }
}