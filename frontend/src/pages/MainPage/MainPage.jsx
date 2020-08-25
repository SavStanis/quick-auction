import React, {useEffect, useState} from "react";
import httpClient from '../../config/axiosClient';

import LotList from "../../components/LotList/LotList";
import PageHeader from "../../components/PageHeader/PageHeader";

function MainPage() {

    const [lotList, setLotList] = useState([]);

    useEffect(() => {

        const getActiveLots = async () => {
            try {
                const response = await httpClient.get('/lots');
                setLotList(response.data.payload.lots);
            } catch (error) {
                console.log(error);
            }
        }
        getActiveLots();
    }, []);

    return (
        <div>
            <PageHeader/>
            <LotList lots={lotList}></LotList>
        </div>
    );
}

export default MainPage;