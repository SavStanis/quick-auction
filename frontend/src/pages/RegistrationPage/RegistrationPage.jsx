import React from 'react';

import RegistrationForm from "../../components/RegistrationForm/RegistrationForm";

import "./RegistrationPage.css"

const RegistrationPage = () => {
    return (
        <div className={"registration-page"}>
            <div className={"registration-form-wrapper"}>
                <RegistrationForm/>
            </div>
        </div>
    );
}

export default RegistrationPage;