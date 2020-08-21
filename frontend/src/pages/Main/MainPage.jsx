import React, {useEffect, useState} from "react";
import httpClient from '../../config/axiosClient';


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
            {
                lotList.map((lot, i) => {
                    return (<div>
                        <div>{lot.name}</div>
                        <div>{lot.price}</div>
                    </div>);
                })
            }
        </div>
    );
}

export default MainPage;