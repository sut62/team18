import axios from "axios";

export default axios.create({
    baseURL: "172.17.0.200:9000",
    headers: {
        'Access-Control-Allow-Origin': '*',
        "Content-type": "application/json",
    }
});
