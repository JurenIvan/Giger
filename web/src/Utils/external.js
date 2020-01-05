import * as Types from "./Types";
import Cookies from "js-cookie";

require('dotenv').config();
console.log(process.env.API_URL)

const API = "https://giger-backend-dev.herokuapp.com/api";

export default function fetcingFactory (endpoint, params) {
    switch (endpoint) {
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
        case Types.endpoints.CREATE_BAND:
            return createBand(params, endpoint);
        case Types.endpoints.GET_BAND:
            return getBand(params, endpoint);
        case Types.endpoints.EDIT_BAND:
            return editBand(params,endpoint);
    }
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
    return fetch(API + endpoint), {
        method: "POST",
        body: params,
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")}
    }
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

function createBand(params, endpoint) {
    return fetch(API + endpoint, {
        method: "POST",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        },
        body: params
    })
}

function getBand(params, endpoint) {
    return fetch(API + endpoint + params,{
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        }
    })
}

function editBand(params,endpoint) {
    return fetch(API + endpoint, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization" : "Bearer " + Cookies.get("Bearer")
        },
        body: params
    })
}