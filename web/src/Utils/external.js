import * as Types from "./Types";
import Cookies from "js-cookie";

const API = "https://giger-backend-dev.herokuapp.com/api";

export default function fetcingFactory (endpoint, params) {
    // eslint-disable-next-line
    switch (endpoint) {
        case Types.endpoints.GETBANDID:
            return getBandId(params, endpoint);

    }
}

function getBandId(params, endpoint) {
    console.log(API + endpoint + params)
    return fetch(API + endpoint + params, {
        method: "GET",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}
