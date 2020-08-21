import React from "react";
import {Card} from "react-bootstrap";

const CardList = ({ lots }) => {
    const cardsArray = lots.map(lot => (
        <Card>

        </Card>
    ));

    return (
        <div>
            {cardsArray}
        </div>
    );
};

export default CardList;