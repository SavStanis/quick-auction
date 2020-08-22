import React, {useState} from "react";
import {Redirect} from "react-router-dom";

import axiosClient from "../../config/axiosClient";

import {Form, Button} from "react-bootstrap";

const RegistrationForm = () => {

    const [redirect, setRedirect] = useState(false);

    const handleSubmit = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();

        if (form.checkValidity() === true) {

            const data = {
                username: form.elements.formBasicEmail.value,
                email: form.elements.formBasicEmail.value,
                password: form.elements.formBasicPassword.value
            }
            try {
                const response = await axiosClient
                    .post("/register", data);
                setRedirect(true);
            } catch (error) {
                console.log(error);
                form.reset();
            }

        }
    }

    if (redirect === true) {
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

            <Form.Group controlId="formBasicUsername">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Username" />
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

export default RegistrationForm;