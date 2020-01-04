import Cookies from "js-cookie";
const API = "https://giger-backend-dev.herokuapp.com/api/"

export function sendRegisterInfo (email, username, phone, password, f) {
    //let xhr = new XMLHttpRequest();
    //let url = 'https://giger-backend-dev.herokuapp.com/api/register';
    //xhr.open('POST', 'https://cors-anywhere.herokuapp.com/'+url);
    //xhr.setRequestHeader('Content-type', 'application/json');
    
    let params = JSON.stringify({
                    "email": email,
                    "username": username,
                    "phoneNumber": phone,
                    "password": password
                    });
    /*console.log(params)
    xhr.send(params);
    xhr.onload = function() {
        if (xhr.status !== 200) { // analyze HTTP status of the response
            console.log(`Error ${xhr.status}: ${xhr.statusText}`);
            f(xhr.status); 
    // e.g. 404: Not Found
        } else { // show the result
            f(xhr.status);
        }
    }*/
    fetch(API + "register", {
        method: "POST",
        headers: {"Content-Type" : "application/json"},
        body: params
    }).then(response => {
        alert(response)
    });

}

export function sendLoginInfo (email, password, f) {
    let xhr = new XMLHttpRequest();
    let url = 'https://giger-backend-dev.herokuapp.com/api/authenticate';
    xhr.open('POST', 'https://cors-anywhere.herokuapp.com/'+url);
    xhr.setRequestHeader('Content-type', 'application/json');
    
    let params = JSON.stringify({
                    "email": email,
                    "password": password
                    });
    xhr.send(params);
    xhr.onload = function() {
        if (xhr.status !== 200) { // analyze HTTP status of the response
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
        if (xhr.status !== 200) { // analyze HTTP status of the response
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
        if (xhr.status !== 200) { // analyze HTTP status of the response
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

export function getTime() {
        var now     = new Date(); 
        var year    = now.getFullYear();
        var month   = now.getMonth()+1; 
        var day     = now.getDate();
        var hour    = now.getHours();
        var minute  = now.getMinutes();
        if(month.toString().length === 1) {
             month = '0'+month;
        }
        if(day.toString().length === 1) {
             day = '0'+day;
        }   
        if(hour.toString().length === 1) {
             hour = '0'+hour;
        }
        if(minute.toString().length === 1) {
             minute = '0'+minute;
        }
        var dateTime = day+'.'+month+'.'+year+'.'+hour+':'+minute;   
    return dateTime;
}
