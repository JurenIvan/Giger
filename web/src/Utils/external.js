import * as Types from "./Types";
import Cookies from "js-cookie";

const API = "https://giger-backend-dev.herokuapp.com/api";

export default function fetcingFactory (endpoint, params) {
    // eslint-disable-next-line
    switch (endpoint) {
        case Types.endpoints.GET_BAND_ID:
            return getBandId(params, endpoint);
        case Types.endpoints.GET_MY_GIGS:
            return getGigs(params, endpoint);
        case Types.endpoints.INVITE_TO_GIG:
            return inviteToGig(params, endpoint);
        case Types.endpoints.GET_BAND_GIGS:
            return getInvites(params, endpoint);

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

function getGigs(params, endpoint) {
    console.log(API + endpoint + params)
    return fetch(API + endpoint + params, {
        method: "GET",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}

function getInvites(params, endpoint) {
    console.log(API + endpoint + params)
    return fetch(API + endpoint + params, {
        method: "GET",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}

function inviteToGig(params, endpoint) {
    return fetch(API + endpoint, {
        method: "POST",
        body: params,
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}