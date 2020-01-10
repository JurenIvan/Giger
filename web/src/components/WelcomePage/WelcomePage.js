import React from 'react'
import  "../WelcomePage/WelcomePage.css";
import { Avatar } from 'antd';

export default class WelcomePage extends React.Component{





    render(){
        
        return(
            <div>
            <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
            <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

            <section id="banner" data-video="images/banner">
					<div className="inner">
						<header>
							<h1>Welcome to Giger</h1>
							<p>&nbsp;&nbsp;&nbsp;Giger simplifies relations between organisers and musicians
                            &nbsp;&nbsp;&nbsp;
                            </p>
                            <br></br>
						</header>
						<a href="/register" className="button big alt scrolly">Get started!</a>
                        <br></br>
                        <br></br>
                        <a href="/displayGigs" className="button big alt scrolly">View public gigs</a>
					</div>

			</section>
            <div class="w3-container w3-padding-64 w3-center" id="team">

            <h2>OUR TEAM</h2>
            <p>Meet the team - our office rats:</p>

            <div class="w3-row"><br/>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Ivan Juren</h3>
            <p>Voditelj/Backend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Mario Zec</h3>
            <p>Backend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Stela Gaši</h3>
            <p>Backend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Tomislav Krmek</h3>
            <p>Frontend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Paolo Lanča</h3>
            <p>Frontend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Marin Jurić</h3>
            <p>Frontend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={100} icon="user" />
            <h3>Mihael Nosil</h3>
            <p>Frontend developer</p>
            </div>


            </div>
            </div>

            </div>

        )
    }



}