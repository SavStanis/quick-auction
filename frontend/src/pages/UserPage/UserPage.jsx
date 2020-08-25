import React, {useEffect, useState} from "react";
import {Redirect} from "react-router-dom";

import PageHeader from "../../components/PageHeader/PageHeader";
import httpClient from "../../config/axiosClient";
import UserProfile from "../../components/UserProfile/UserProfile";

const UserPage = () => {

    const [user, setUser] = useState({});

    useEffect(() => {

        const getUser = async () => {
            const url = "/users/" + localStorage.getItem("id");

            try {
                const response = await httpClient.get(url);
                const responseUser = response.data.payload.user;

                setUser(
                    {
                        id: responseUser.id,
                        username: responseUser.username,
                        email: responseUser.email,
                        balance: responseUser.balance,
                        creationDate: responseUser.creationDate
                    }
                );
            } catch (err) {
                console.log(err);
            }
        }

        getUser();
    },[])

    if (!localStorage.getItem("user")) {
        return <Redirect to={"/"}/>
    }

    return(
        <div className={"user-page"}>
            <PageHeader/>
            <UserProfile user={user}/>
        </div>
    )
}

export default UserPage;