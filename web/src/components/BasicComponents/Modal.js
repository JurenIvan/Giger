import React from "react";

import {Modal} from "react-bootstrap";
import { Button } from "react-bootstrap";

export default class ModalClass extends React.Component {


    render() {
        return (
            <Modal show={true} animation={false}>
                <Modal.Footer>
                  <p  style={{color:"red"}}> You are not logged in! </p>
                    <Button
                        variant="danger"
                        href="/login"
                    >
                        Go to login...
                    </Button>
                </Modal.Footer>
                </Modal>
        )
    }
}