import React from 'react';

import RegistrationForm from "../../components/RegistrationForm/RegistrationForm";

import "./RegistrationPage.css"
import PageHeader from "../../components/PageHeader/PageHeader";

const RegistrationPage = () => {
    return (
    <div>
        <PageHeader/>
        <div className={"registration-page"}>
            <div className={"registration-form-wrapper"}>
                <RegistrationForm/>
            </div>
        </div>
    </div>
    );
}

export default RegistrationPage;