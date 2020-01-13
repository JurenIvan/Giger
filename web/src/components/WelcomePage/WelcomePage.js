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

            <section id="banner" data-video="images/banner" className="WelcomePage-Styling">
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
            <Avatar size={120} icon="user"
            src="https://media-exp1.licdn.com/dms/image/C4E03AQGK1fho6igebg/profile-displayphoto-shrink_200_200/0?e=1584576000&v=beta&t=TpLmx7YSYhpSuZxDqOaZHc8HrVD4mzhL4Je3usZs7no"/>
            <h3>Ivan Juren</h3>
            <p>The Boss/Backend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={120} icon="user"
            src="https://files.slack.com/files-pri/TP038T9EJ-FSJM5MN84/mario-zec_web-864x600_c.jpg"/>
            <h3>Mario Zec</h3>
            <p>Backend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={120} icon="user"
            src="https://scontent.fzag1-1.fna.fbcdn.net/v/t1.0-9/22450044_1884489498234130_5436258312850309420_n.jpg?_nc_cat=111&_nc_ohc=A1sxWAh-wMMAQkfTzW3JkpNg-9x8I894rbpx4RoUoJiDrsrwQEFQbsFEA&_nc_ht=scontent.fzag1-1.fna&oh=040ad6af47e253d61fe42cdc37055efd&oe=5E9A0B20" />
            <h3>Stela Gaši</h3>
            <p>Backend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={120} icon="user" />
            <h3>Tomislav Krmek</h3>
            <p>Frontend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={120} icon="user"
            src="https://scontent.fzag1-1.fna.fbcdn.net/v/t1.0-9/34985158_10156388982186081_110968945472700416_o.jpg?_nc_cat=105&_nc_ohc=-tH4Gg14XxsAQngQ67LWRgj3VMXTUoRSKgGpc0aW_mppO785BLCxjU3SQ&_nc_ht=scontent.fzag1-1.fna&oh=5b8b6c8287a437010d0f5fed880c6615&oe=5E9F31BF" />
            <h3>Paolo Lanča</h3>
            <p>Frontend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={120} icon="user"
             src="https://i.ibb.co/b7zVjnB/Moj-Avatar.jpg"/>
            <h3>Marin Jurić</h3>
            <p>Frontend developer</p>
            </div>

            <div class="w3-quarter">
            <Avatar size={120} icon="user" 
            src="https://expresstabloid.ba/wp-content/uploads/2019/04/1-369.jpg"/>
            <h3>Mihael Nosil</h3>
            <p>Frontend developer</p>
            </div>


            </div>
            </div>

            </div>

        )
    }



}