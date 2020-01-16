import { Modal, Button } from 'antd';
import React from 'react';

export default class MyModal extends React.Component {
  state = { visible: false };

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

  render() {
    return (
      <div>
        <Button type="primary" onClick={this.showModal}>
          Map
        </Button>
        <Modal
            title="Map"
            visible={this.state.visible}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            width="550px"
            bodyStyle={{height:"550px"}}
        >
            <iframe 
            width="500"
            height="500"
            src={this.props.url} />
        </Modal>
      </div>
    );
  }
}