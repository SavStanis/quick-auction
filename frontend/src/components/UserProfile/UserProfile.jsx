import React from "react";
import {Card} from "react-bootstrap";

import "./UserProfile.css";

const UserProfile = ({user}) => {

    return (
        <div className={"profile-wrapper"}>
            <Card id={"profile"}>
                <Card.Header>
                    <h5>{user.username}</h5>
                </Card.Header>
                <Card.Body>
                    <Card.Text>id: {user.id}</Card.Text>
                    <Card.Text>Email: {user.email}</Card.Text>
                    <Card.Text>Current balance: {user.balance}</Card.Text>
                    <Card.Text></Card.Text>
                </Card.Body>
                <Card.Footer className={"text-muted"}>Registration date: {user.creationDate}</Card.Footer>
            </Card>
        </div>
    );
}

export default UserProfile;