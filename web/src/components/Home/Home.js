import React from 'react';
//import  "../Home/Home.css";


export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shouldRenderHello: false
        }
        this.procesHello = this.procesHello.bind(this);
    }
    
    procesHello(value) {
        console.warn(value);
        this.setState({shouldRender: value});
    }


    
    render() {
        return (
        <div>
            <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
            <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

            <section id="banner" data-video="images/banner">
					<div className="inner">
						<header>
							<h1>&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;
                            </h1>
							<p>
                            </p>
                            <br></br>
						</header>

                        <div>
						<a href="/displayGigs" className="button big alt scrolly"style={{width:"400px"}} >View public gigs</a>
                        </div>

                        <br></br>
                        <br></br>     

                        <div>
						<a href="/CreateGig" className="button big alt scrolly" style={{width:"400px"}}>Create gig</a>
                        </div>

                        <br></br>
                        <br></br>

                        <div>
						<a href="/EditGig" className="button big alt scrolly" style={{width:"400px"}} >Edit gig</a>
                        </div>

                        <br></br>
                        <br></br>                        

                        <div>
						<a href="/InviteToGig" className="button big alt scrolly" style={{width:"400px"}}>Invite people to gig</a>
                        </div>

                        <br></br>
                        <br></br>

                        <div>
						<a href="/AcceptGigInvite" className="button big alt scrolly" style={{width:"400px"}} >Manage gig invites</a>
                        </div>

                        <br></br>
                        <br></br>

                        <div>
						<a href="/displayBands" className="button big alt scrolly" style={{width:"400px"}} >View bands</a>
                        </div>

                        <br></br>
                        <br></br>

                        <div>
						<a href="/InviteToBand" className="button big alt scrolly" style={{width:"400px"}} >Invite people to your band</a>
                        </div>

                        <br></br>
                        <br></br>

                        <div>
						<a href="/AcceptBandInvite" className="button big alt scrolly"style={{width:"400px"}} >Manage band invites</a>
                        </div>

                        <br></br>
                        <br></br>
                   


					</div>

			</section>
  


            </div>
          

        )
    }
}
