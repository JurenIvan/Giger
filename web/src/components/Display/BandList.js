import React, { Component } from "react"
import { Card } from 'antd';
import { Input } from 'antd';
const { Search } = Input;

export class BandList extends React.Component{

    constructor(props)
    {
        super(props);
        this.state= {
            filtered: []
        }
        this.handleChange = this.handleChange.bind(this);


    }

    componentDidMount() {
        this.setState({
          filtered: this.props.items
        });
      }
      
    componentWillReceiveProps(nextProps) {
        this.setState({
          filtered: nextProps.items
        });
    }

    handleChange(e) {
        // Variable to hold the original version of the list
    let currentList = [];
        // Variable to hold the filtered list before putting into state
    let newList = [];

        // If the search bar isn't empty
    if (e.target.value !== "") {
            // Assign the original list to currentList
      currentList = this.props.items;

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
      newList = this.props.items;
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

            <div className ="band-item">
            {this.state.filtered.map(item => (
                //extra ce bit link na stranicu benda
                <div>
                <div style={{ background: '#ECECEC', padding: '30px' }}>
                <Card title={item} extra={<a href="#">More</a>} style={{ width: 300 }}>
                    <p>-Picture url-</p>
                    <p>-Gig type-</p>
                </Card>
                </div>

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