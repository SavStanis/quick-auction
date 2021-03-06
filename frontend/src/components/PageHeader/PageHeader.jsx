import React, {useEffect, useState} from "react";
import {Button, Navbar, Nav} from "react-bootstrap";
import {Redirect, Link} from "react-router-dom";

const PageHeader = () => {

    const [username, setUsername] = useState(localStorage.getItem("user"));
    const [redirect, setRedirect] = useState(false);


    const logout = () => {
        localStorage.removeItem("user");
        localStorage.removeItem("email");
        localStorage.removeItem("token");

        setUsername(null);
        setRedirect(true);
    }

    useEffect(() => {
        const checkUser = () => {
            const user = localStorage.getItem("user");

            if (user) {
                setUsername(user);
            } else {
                setUsername(null);
            }
        }

        window.addEventListener("storage", checkUser);

        return () => {
            window.removeEventListener('keydown', checkUser);
        };
    },[redirect]);

    const userLogin = (
        <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">

            </Nav>
            <Nav>
                <Link className={"nav-link"} to={"/user"}>{localStorage.getItem("user")}</Link>
            </Nav>
            <Nav>
                <Button className={"btn-dark"} onClick={logout}>Logout</Button>
            </Nav>
        </Navbar.Collapse>
    );

    const userLogout = (
        <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">

            </Nav>
            <Nav>
                <Link className={"nav-link"} to={"/registration"}>Registration</Link>
            </Nav>
            <Nav>
                <Link className={"nav-link"} to={"/login"}>Login</Link>
            </Nav>
        </Navbar.Collapse>
    );

    const getUserPanel = () => {
        if (username) {
            return userLogin;
        } else {
            return userLogout;
        }
    }

    if (redirect) {
        return (
            <Redirect to={"/"}/>
        );
    }

    return (
        <Navbar className={"sticky-top"} collapseOnSelect expand="lg" bg="dark" variant="dark">
            <Link className={"navbar-brand"} to={"/"}>Quick Auction</Link>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            {getUserPanel()}
        </Navbar>
    );
}

export default PageHeader;