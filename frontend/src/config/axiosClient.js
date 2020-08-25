import axios from "axios";
import {API_URL} from "./config";
import {buildAuthHeader} from "../helpers/tokenUtils";

export default axios.create({
    baseURL: API_URL,
    headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Authorization": buildAuthHeader()
    }
});