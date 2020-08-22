import React from "react";
import {Navbar, Nav} from "react-bootstrap";
import {Link} from "react-router-dom";

const PageHeader = () => {

    return (
        <Navbar className={"sticky-top"} collapseOnSelect expand="lg" bg="dark" variant="dark">
            <Link className={"navbar-brand"} to={"/"}>Quick Auction</Link>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">

                </Nav>
                <Nav>
                    <Link className={"nav-link"} to={"/registration"}>Registration</Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default PageHeader;