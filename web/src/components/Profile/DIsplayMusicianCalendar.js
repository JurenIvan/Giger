import React from "react";
import {Card, Calendar} from "antd"
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";

export default class DisplayMusicianCalendar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            markedDates: [ new Date(), new Date() + 1],
            musicianOcassion: []
        }
        this.dateCellRender = this.dateCellRender.bind(this);
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_MUSICIAN_OCASSION, this.props.id).then(
            response => {
                if (response.ok) {
                    return response.json();
                } else {
                    alert("Something went wrong")
                }
            }
        ).then(
            json => {
                if (json) {
                    this.setState({musicianOcassion : json}, () => console.log(this.state))
                }
            }
        )
    }

    dateCellRender(value) {
       return (
        this.state.musicianOcassion.map(element => {
            if (element.localDate.substring(0,10) === value.format("YYYY-MM-DD")) {
                console.log("true")
                return (
                   <Card style = {{backgroundColor: element.personalOccasion? "darkcyan" : "red", fontWeight: "bold", color: "white"}}>
                    {element.description}
                   </Card>
                );
            } else {
                return null
            }
        })
       )
    }
    render() {
        return(
            <Card title = "My Calendar" extra = {"a"/* create legend for calendar */}>
                <Calendar fullscreen = {true} dateCellRender = {this.dateCellRender}
                />
            </Card>
        )
    }
}