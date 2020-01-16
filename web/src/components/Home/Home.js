import React from 'react';
import {endpoints} from "../../Utils/Types"
import fetcingFactory from "../../Utils/external"
//import  "../Home/Home.css";


export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shouldRenderHello: false,
            roles: [],
            person: false,
            musician: false,
            organizer: false
        }
        this.procesHello = this.procesHello.bind(this);
    }
    
    procesHello(value) {
        console.warn(value);
        this.setState({shouldRender: value});
    }

    componentDidMount() {
        fetcingFactory(endpoints.GET_ROLES).then(
            response => {
                if (response.ok) {
                    return response.json()
                } else {
                    alert("Something went wrong!");
                }
            }
            ).then(
                json => {
                    if(json) {
                        let helperRoles = this.state.roles;
                        for (let i = 0; i < json.length; i++) {
                            helperRoles.push(json[i]);
                        }
                        this.setState({roles: helperRoles}, () => {
                            if(this.state.roles.includes("PERSON")){
                                this.setState({person: true})
                            }
                            if(this.state.roles.includes("ORGANIZER")){
                                this.setState({organizer: true})
                            }
                            if(this.state.roles.includes("MUSICIAN")){
                                this.setState({musician: true})
                            }
                        })
                    }
                }
                
            )
            console.log(this.state)
    }

    
    render() {
        let person = this.state.person?
            <div>
                <div>
                    <a href="/displayGigs" className="button big alt scrolly"style={{width:"400px"}} >View public gigs</a>
                </div>
                <br></br>
                <div>
                    <a href="/displayBands" className="button big alt scrolly" style={{width:"400px"}} >View bands</a>
                </div>
                <br></br>
            </div> 
            :
            []
        let musician = this.state.musician?
            <div>
                <div>
                    <a href="/create_band" className="button big alt scrolly"style={{width:"400px"}} >Create band</a>
                </div>
                <br></br>
                <div>
                    <a href="/AcceptBandInvite" className="button big alt scrolly"style={{width:"400px"}} >Manage band invites</a>
                </div>
                <br></br>
                <div>
                    <a href="/InviteToBand" className="button big alt scrolly" style={{width:"400px"}} >Invite people to your band</a>
                </div>
                <br></br>
                <div>
                    <a href="/AcceptGigInvite" className="button big alt scrolly" style={{width:"400px"}} >Manage gig invites</a>
                </div>
                <br></br>
            </div>
            :
            []
        let organizer = this.state.organizer?
            <div>
                <div>
                    <a href="/CreateGig" className="button big alt scrolly" style={{width:"400px"}}>Create gig</a>
                </div>
                <br></br>
                <div>
                    <a href="/EditGig" className="button big alt scrolly" style={{width:"400px"}} >Edit gig</a>
                </div>
                <br></br>
                <div>
                    <a href="/InviteToGig" className="button big alt scrolly" style={{width:"400px"}}>Invite band to gig</a>
                </div>
            </div>
            : []
        return (
        <div style={{width: "100%"}}>
            <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
            <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

            <section id="banner" data-video="images/banner" style={{width: "100%"}}>
					<div className="inner" style={{width: "100%"}}>
						
                        {person}
                        {musician} 
                        {organizer}
					</div>
			</section>
            </div>
          

        )
    }
}
