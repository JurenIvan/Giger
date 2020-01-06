import React from 'react'
import Link from 'next/link';
import { Typography, Icon } from 'antd'



export default class WelcomePage extends React.Component{

    render(){
        
        return(
            <div>
            <Typography.Title
                    style={{ marginBottom: '16px' }}
                >
                    Welcome to Giger! <Icon type="customer-service" /><br />
                </Typography.Title>
                <Typography.Title level={2}>
                    Giger simplifies relations between organisers and musicians.<br />
                    Here you can create,view and join gigs, ...
                </Typography.Title>

                <Typography.Title level={3}>

                    You can&nbsp;
                    <a href="/login">login</a>
                    &nbsp; or  &nbsp;
                    <a href="/register">register</a>
            </Typography.Title>
            </div>
        )
    }



}