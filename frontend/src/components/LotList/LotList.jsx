import React from "react";
import {Card} from "react-bootstrap";
import "./LotList.css"

const LotList = ({ lots }) => {
    const cardsArray = lots.map(lot => (
        <Card>
            <Card.Header>
                <h5>{lot.name}</h5>
            </Card.Header>
            <Card.Body>
                <Card.Text>Seller: {lot.seller.username}</Card.Text>
                <Card.Text>Current highest bid: {lot.price}</Card.Text>
                <Card.Text></Card.Text>
            </Card.Body>
            <Card.Footer className={"text-muted"}>Expiration date: {lot.expired}</Card.Footer>
        </Card>
    ));

    return (
        <div className="lot-list">
            {cardsArray}
        </div>
    );
};

export default LotList;