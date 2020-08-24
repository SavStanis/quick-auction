import React from "react";

import LoginForm from "../../components/LoginForm/LoginForm";

import "./LoginPage.css"
import PageHeader from "../../components/PageHeader/PageHeader";

const LoginPage = () => {
    return (
        <div>
            <PageHeader/>
            <div className={"login-page"}>
                <div className={"login-form-wrapper"}>
                    <LoginForm/>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;