import React from "react";
import {Button} from "react-bootstrap";

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
            <React.Fragment>
                <div className="post">
                    <div className="col-sm-2">
                        <img src={this.props.usersPostImgUrl} alt="PostOwnerPhoto"></img>
                    </div>
                    <div className = "col-sm-10">
                        <div className = "row">
                                {this.props.postOwnerName + " "} 
                                <div className="col text-right">
                                    {this.props.postedTime}
                                </div>
                            </div>
                            <div className="col text-center">
                                {this.props.content}
                            </div>
                            <div className="row">
                                {this.props.commentNr}
                                <Button
                                    style={{marginLeft: "auto" }}
                                    onClick={this.handleClick}>
                                Comment 
                                </Button>
                        </div>
                     </div>
                </div>
                {
                    this.state.isCommentButtonClicked?
                        <div className="commentSection">
                            \\\\\\\\\TODO: mapirat komentare
                            <div className="row">
                                <input type="text" value={this.state.isCommentButtonClicked}></input>
                                <Button style = {{marginLeft: "auto" }} >Submit comment</Button>
                            </div>  
                        </div>: null
                }
               
            </React.Fragment>
            
        )
    }
}