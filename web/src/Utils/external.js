import * as Types from "./Types";
import Cookies from "js-cookie";

const API = "https://giger-backend-dev.herokuapp.com/api";

export default function fetcingFactory (endpoint, params) {
    // eslint-disable-next-line
    switch (endpoint) {
        case Types.endpoints.BANDS_FILTER:
            return getBandslist(params,endpoint)
        case Types.endpoints.LOGIN:
            return sendLoginInfo(params, endpoint);
        case Types.endpoints.REGISTER:
            return sendRegisterInfo(params,endpoint);
        case Types.endpoints.CREATE_GIG:
            return createGig(params, endpoint);
        case Types.endpoints.CREATE_MUSICIAN:
            return createMusician(params,endpoint);
        case Types.endpoints.CREATE_ORGANIZER:
            return createOrganizer(params,endpoint);
            case Types.endpoints.GET_BAND_ID:
            return getBandId(params, endpoint);
        case Types.endpoints.GET_MY_GIGS:
            return getGigs(params, endpoint);
        case Types.endpoints.INVITE_TO_GIG:
            return inviteToGig(params, endpoint);
    }
}

function getBandslist(params, endpoint) {
    console.log(API + endpoint)
    return fetch(API + endpoint, {
        method: "POST",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")

        }
    })
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
function sendLoginInfo(params, endpoint) {
    return fetch(API + endpoint, 
        {
            method: "POST",
            body: params,
            headers: {"Content-Type" : "application/json"}
        })
}

function sendRegisterInfo(params, endpoint) {
    return fetch(API + endpoint,
        {
            method: "POST",
            body: params,
            headers: {"Content-Type" : "application/json"}
        })
}

function createGig(params, endpoint) {
    return fetch(API + endpoint, {
        method: "POST",
        body: params,
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")}
    })
}

function createMusician(params,endpoint) {
    return fetch(API + endpoint, {
        method:"POST",
        body: params,
        headers: {            
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}

function createOrganizer(params, endpoint) {
    return fetch(API + endpoint + params, {
        method: "GET",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}