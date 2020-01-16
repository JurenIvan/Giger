import React from "react"
import { Card } from 'antd';
import { Input } from 'antd';
import { Avatar } from 'antd';
import "../../CSS/GigList.css"
import fetcingFactory from "../../Utils/external";
import {endpoints} from "../../Utils/Types";
const { Search } = Input;



export class GigList extends React.Component{

    constructor(props)
    {
        super(props);
        this.state= {
            GigNames: [],
            filtered: [],
            Gigs:[]
        }
        this.handleChange = this.handleChange.bind(this);

    }

    componentDidMount() {
      let helperArray = this.state.Gigs;
      let helperNames=this.state.GigNames;
      let helperFiltered=this.state.filtered;
      fetcingFactory(endpoints.PUBLIC_GIGS_VIEW,null).then(
        response=> response.json()
        ).then(
          json=> {
            console.log(json)
            for (let i= 0; i< json.length; i++) {
              helperArray.push(json[i]);
              helperFiltered.push(json[i].name);
              helperNames.push(json[i].name);
            }
            this.setState({Gigs: helperArray,
            filtered:helperFiltered,
            GigNames:helperNames}, () => console.log(this.state.Gigs));
          }
      )
    }

    handleChange(e) {
        // Variable to hold the original version of the list
    let currentList = [];
        // Variable to hold the filtered list before putting into state
    let newList = [];

        // If the search bar isn't empty
    if (e.target.value !== "") {
            // Assign the original list to currentList
      currentList = this.state.GigNames;

            // Use .filter() to determine which items should be displayed
            // based on the search terms
      newList = currentList.filter(item => {
                // change current item to lowercase
        const lc = item.toLowerCase();
                // change search term to lowercase
        const filter = e.target.value.toLowerCase();
                // check to see if the current list item includes the search term
                // If it does, it will be added to newList. Using lowercase eliminates
                // issues with capitalization in search terms and search content
        return lc.includes(filter);
      });
    } else {
            // If the search bar is empty, set newList to original task list
      newList = this.state.GigNames;
    }
        // Set the filtered state based on what our rules added to newList
    this.setState({
      filtered: newList
    });
  }

    render()
    {
        return(
        <div>
          
            <div style ={{position:"relative",left:"23px", top:"2px"}}>
            <Search
            placeholder="input search text"
            enterButton="Search"
            size="large"
            onChange={this.handleChange}
            />
            </div>


            <br></br>
            <br></br>
            <br></br>

            <ul>

            <div className ="gig-item">
            
            {this.state.Gigs.map(item => (
                //extra ce bit link na stranicu benda
                
                <div>
                {this.state.filtered.indexOf(item.name)>-1 &&
                <div style={{ background: '#179b81', padding: '15px' }}>
                <Card title={item.name} style={{ width: 600 }}>
                <table style={{width:"100%"}}>
                  <tr>
                    <th><p><Card.Grid 
                    style={{width: '90%',
                  textAlign: 'center',}}>
                  <p>Description: {item.description}</p>
                                    <br></br>
                                    <p>Address: {item.location.address} </p>
                                    <br></br>
                                    <p>Date and time : {item.dateTime}</p>
                                    <br></br></Card.Grid></p></th>
                    <th><iframe 
                  height="150%" 
                    src={"https://maps.google.com?q="+item.location.x+","+item.location.y+"&output=svembed"} /></th> 
                  </tr>
                </table>
                <table style={{width:"100%"}}>
                  <tr>
                  <th>
                  <Card title={"Band playing - " + item.bandDto.name} bordered={false}  style={{width: '90%'}}>
                  <p>{item.bandDto.bio}</p>
                  </Card>
                  </th>
                  <th>
                  <Avatar shape="square" size={128} icon="user" src={item.bandDto.pictureURl} />
                  </th>
                  </tr>
                </table>
                </Card>
                </div>
                }
                <div>
                <br></br>
                <br></br>
                <br></br>
                </div>
                </div>
            ))}
            </div>
            </ul>
        </div>
        )
    }

}