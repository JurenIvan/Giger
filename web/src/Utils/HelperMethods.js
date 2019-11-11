import Cookies from "js-cookie";

export function sendRegisterInfo (email, username, phone, password) {
    let xhttp = new XMLHttpRequest();
    let url = 'https://giger-backend-dev.herokuapp.com/api/register';
    xhttp.open('POST', url, false);
    xhttp.setRequestHeader('Content-type', 'application/json');
    
    let params = JSON.stringify({
                    "email": email,
                    "username": username,
                    "phoneNumber": phone,
                    "password": password
                    });
    xhttp.send(params);

}

export function sendLoginInfo (username, password, f) {
    let xhr = new XMLHttpRequest();
    let url = 'https://giger-backend-dev.herokuapp.com/api/authenticate';
    xhr.open('POST', 'https://cors-anywhere.herokuapp.com/'+url);
    xhr.setRequestHeader('Content-type', 'application/json');
    
    let params = JSON.stringify({
                    "username": username,
                    "password": password
                    });
    xhr.send(params);
    xhr.onload = function() {
        if (xhr.status != 200) { // analyze HTTP status of the response
            console.log(`Error ${xhr.status}: ${xhr.statusText}`); 
            alert("Unsuccesfull login :(");
    // e.g. 404: Not Found
        } else { // show the result
            console.log(`Done, got ${xhr.response}`); // responseText is the server
            let retVal = JSON.stringify(xhr.response);
            f(retVal);
        }
    }
}

export function isUsernameAvailible(username, f) {
    // 1. Create a new XMLHttpRequest object
    let xhr = new XMLHttpRequest();
    let url = 'https://giger-backend-dev.herokuapp.com/api/register/nickname-available?nickname=' + username;
    xhr.open('GET', 'https://cors-anywhere.herokuapp.com/'+url);
    xhr.setRequestHeader('Content-Type', 'application/json');
    
    xhr.send();

    // 4. This will be called after the response is received
    xhr.onload = function() {
        if (xhr.status != 200) { // analyze HTTP status of the response
            console.log(`Error ${xhr.status}: ${xhr.statusText}`); // e.g. 404: Not Found
        } else { // show the result
            console.log(`Done, got ${xhr.responseText}`); // responseText is the server
            let retVal = xhr.responseText === 'true' ? true: false;
            f(retVal);
        }
    };

    xhr.onerror = function() {
        console.log("Request failed");
    };
}

export function pingHelloWorld(f) {
    // 1. Create a new XMLHttpRequest object
    let xhr = new XMLHttpRequest();
    let url = 'https://giger-backend-dev.herokuapp.com/api/hello';
    xhr.open('GET', 'https://cors-anywhere.herokuapp.com/'+url);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.setRequestHeader('Authorization',
    'Bearer ' + Cookies.get('Bearer'))
    
    xhr.send();

    // 4. This will be called after the response is received
    xhr.onload = function() {
        if (xhr.status != 200) { // analyze HTTP status of the response
            console.log(`Error ${xhr.status}: ${xhr.statusText}`); // e.g. 404: Not Found
        } else { // show the result
            console.log(`Done, got ${xhr.responseText}`); // responseText is the server
            let retVal = xhr.responseText === 'Hello World' ? true: false;
            f(retVal);
        }
    };

    xhr.onerror = function() {
        console.log("Request failed");
    };
}
