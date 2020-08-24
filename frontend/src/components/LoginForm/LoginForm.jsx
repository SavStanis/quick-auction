import React, {useState} from "react";
import {Redirect} from "react-router-dom";

import axiosClient from "../../config/axiosClient";

import {Form, Button} from "react-bootstrap";

const LoginForm = () => {

    const [redirect, setRedirect] = useState(false);


    const handleSubmit = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();

        if (form.checkValidity() === true) {

            const data = {
                email: form.elements.formBasicEmail.value,
                password: form.elements.formBasicPassword.value
            }
            try {
                const response = await axiosClient
                    .post("/login", data);

                localStorage.setItem("user", response.data.payload.login.username);
                localStorage.setItem("email", response.data.payload.login.email);
                localStorage.setItem("token", response.data.payload.login.token);

                setRedirect(true);
            } catch (error) {
                console.log(error);
                form.reset();
            }

        }
    }

    if (redirect === true || localStorage.getItem("user") !== null) {
        return (
            <Redirect to={"/"}/>
        );
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formBasicEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control type="email" placeholder="Enter email" />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" />
            </Form.Group>

            <Button variant="primary" type="submit"  >
                Submit
            </Button>
        </Form>
    )
}

export default LoginForm;