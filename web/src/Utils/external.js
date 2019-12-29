import * as Types from "./Types";

const API = "https://giger-backend-dev.herokuapp.com/api";
const header = {"Content-Type" : "application/json"};
export default function fetcingFactory (endpoint, params) {
    switch (endpoint) {
        case Types.endpoints.LOGIN:
            return sendLoginInfo(params, endpoint);
        case Types.endpoints.REGISTER:
            return sendRegisterInfo(params,endpoint);
        case Types.endpoints.CREATE_GIG:
            return createGig(params, endpoint);
    }
}

function sendLoginInfo(params, endpoint) {
    return fetch(API + endpoint, 
        {
            method: "POST",
            body: params,
            headers: header
        })
}

function sendRegisterInfo(params, endpoint) {
    return fetch(API + endpoint,
        {
            method: "POST",
            body: params,
            headers: header
        })
}

function createGig(params, endpoint) {
    return fetch(API + endpoint), {
        method: "POST",
        body: params,
        headers: header
    }
}