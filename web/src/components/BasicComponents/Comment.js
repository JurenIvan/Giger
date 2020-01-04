import * as React from "react";
import * as Helpers from "../../Utils/HelperMethods";

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
            <div className="row">
                <div className="col-1">
                <img src = ""></img>
                </div>
                <div className="col-10">
                    <div className="row">
                    {this.state.time}
                    {this.state.user}
                    </div>
                    <div className="row">
                        content
                    </div>
                </div>
            </div>
        )
    }
}