const API_test = process.env.NODE_ENV === 'production'?"https://giger-backend.herokuapp.com/api":"https://giger-backend-dev.herokuapp.com/api";

export function createBand (name,bio, pictureURL, acceptableGigTypes,homeLocation, f) {
    let xhr = new XMLHttpRequest();
    let url = API_test + '/band-administration/create';
    xhr.open('POST', url);
    xhr.setRequestHeader('Content-type', 'application/json');
    
    let params = JSON.stringify({
                    "name": name,
                    "bio": bio,
                    "pictureURL": pictureURL,
                    "acceptableGigTypes": acceptableGigTypes,
                    "homeLocation": homeLocation
                    });
    console.log(params)
    xhr.send(params);
    xhr.onload = function() {
        if (xhr.status !== 200) { // analyze HTTP status of the response
            console.log(`Error ${xhr.status}: ${xhr.statusText}`);
            f(xhr.status); 
    // e.g. 404: Not Found
        } else { // show the result
            f(xhr.status);
        }
    }

}