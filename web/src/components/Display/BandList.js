import React from "react"
import { Input } from 'antd';
import { Card, Modal, Button } from 'antd';
import fetcingFactory from "../../Utils/external";
import ErrorBoundary from "../ErrorBoundary/ErrorBoundary";
import {endpoints} from "../../Utils/Types";

const { Search } = Input;
const { Meta } = Card;



export class BandList extends React.Component{

    constructor(props)
    {
        super(props);
        this.state= {
            BandNames: [],
            filtered: [],
            Bands:[],
            visible:false
        }
        this.handleChange = this.handleChange.bind(this);

    }

    componentDidMount() {
      let helperArray = this.state.Bands;
      let helperNames=this.state.BandNames;
      let helperFiltered=this.state.filtered;
      fetcingFactory(endpoints.BANDS_FILTER,null).then(
        response=> response.json()
        ).then(
          json=> {
            console.log(json)
            for (let i= 0; i< json.length; i++) {
              helperArray.push(json[i]);
              helperFiltered.push(json[i].name)
              helperNames.push(json[i].name)
            }
            this.setState({Bands: helperArray,
            filtered:helperFiltered,
            BandNames:helperNames}, () => console.log(this.state.Bands))
          }
      )

    }
    showModal = () => {
      this.setState({
        visible: true,
      });
    };
  
    handleOk = e => {
      console.log(e);
      this.setState({
        visible: false,
      });
    };
  
    handleCancel = e => {
      console.log(e);
      this.setState({
        visible: false,
      });
    };



    handleChange(e) {
        // Variable to hold the original version of the list
    let currentList = [];
        // Variable to hold the filtered list before putting into state
    let newList = [];

        // If the search bar isn't empty
    if (e.target.value !== "") {
            // Assign the original list to currentList
      currentList = this.state.BandNames;

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
      newList = this.state.BandNames;
    }
        // Set the filtered state based on what our rules added to newList
    this.setState({
      filtered: newList
    });
  }

    render()
    {
        return(
        <ErrorBoundary>
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

            <div className ="band-item">

            {this.state.Bands.map(item => (
                //extra ce bit link na stranicu benda
                <div>
                {this.state.filtered.indexOf(item.name)>-1 &&
                <div style={{ background: '#ECECEC', padding: '15px' }}>

                <Card style={{ width: 350 }} 
                cover={
                  <img
                    alt="bandimage"
                    src={item.pictureURl}
                    height="200" 
                    width="50"
                  />
                }
                actions={[
                  <p>Gig type: {item.gigTypes[0]}</p>
                ]}                
                >
                    <Meta
                    title={item.name}
                    description={item.bio}
                   />

                </Card>
                </div>
                }
                </div>
            ))}
            </div>
            </ul>
        </div>
        </ErrorBoundary>
        )
    }

}