import React from "react";
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";
import bandMemberClass from "./bandMember";

export default class DisplayBandMembers extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            bandMembers: [],
            backupBandMembers: []
        }
    }

    componentWillMount() {
        fetcingFactory(endpoints.GET_BAND, this.props.bandId).then(
            response => response.json()
        ).then(
            json => {
                console.log("Inside component will mount")
                let helperMembers = this.state.bandMembers;
                let helperBackup = this.state.backupBandMembers;
                for (let i=0; i < json.membersIds.length; i++) {
                    helperMembers.push(json.membersIds[i]);
                }
                for (let j=0; j < json.backupMembersIds.lenth; j++) {
                    helperBackup.push(json.backupMembersIds[j]);
                }

                this.setState({bandMembers: helperMembers, backupBandMembers: helperBackup}, ()=> console.log(this.state))
            }
        )
    }

    render() {
         console.log("Inside render")
         let members = this.state.bandMembers.map(element => {
             return (
                 <bandMemberClass memberId = {element} backup = {false} />
             )
         })
        return(
            <React.Fragment>
                <div>
                <h2> Band members: </h2>
                {
                   members            
                }
                </div>

                <div>
                <h2> Backup members: </h2>
                {
                    this.state.backupBandMembers.map(element => {
                        return (
                            <bandMemberClass memberId = {element} backup = {true} />
                        )
                    })
                }
                </div>
            </React.Fragment>
        )
    }
}