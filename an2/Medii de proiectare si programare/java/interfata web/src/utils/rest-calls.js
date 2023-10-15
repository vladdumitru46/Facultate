import {ARTISTS_BASE_URL} from "./consts.js";

function status(response) {
    console.log('response status ' + response.status);
    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response);
    } else {
        return Promise.reject(new Error(response.statusText));
    }
}

function json(response) {
    return response.json();
}

export function GetArtists() {
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    let myInit = {
        method: 'GET',
        headers: headers,
        mode: 'cors'
    };
    let request = new Request(ARTISTS_BASE_URL, myInit);

    return fetch(request)
        .then(status)
        .then(json)
        .then(data => {
            return data;
        }).catch(error => {
            return Promise.reject(error);
        });

}

export function DeleteArtist(artistName) {
    console.log('inainte de fetch delete')
    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");
    let antet = {method: 'DELETE', headers: myHeaders, mode: 'cors'};
    const artistDelUrl = ARTISTS_BASE_URL + '/' + artistName;
    return fetch(artistDelUrl, antet).then(status).then(response => {
        return response.text();
    }).catch(e => {
        return Promise.reject(e);
    })
}

export function AddArtist(artist) {
    console.log('inainte de fetch post' + JSON.stringify(artist));
    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");
    myHeaders.append("Content-Type", "application/json");
    let antet = {method: 'POST',
        headers: myHeaders,
        mode: 'cors',
        body: JSON.stringify(artist)};
    return fetch(ARTISTS_BASE_URL, antet)
        .then(status)
        .then(response => {
            return response.text();
        }).catch(error => {
            console.log('Request failed', error);
            return Promise.reject(error);
        });
}

export function UpdateArtist(artist) {
    console.log('inainte de fetch post' + JSON.stringify(artist));
    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");
    myHeaders.append("Content-Type", "application/json");
    let antet = {method: 'PUT',
        headers: myHeaders,
        mode: 'cors',
        body: JSON.stringify(artist)};
    const artistDelUrl = ARTISTS_BASE_URL + '/' + artist.stageName;
    return fetch(artistDelUrl, antet).then(status).then(response => {
        return response.text();
    }).catch(e => {
        return Promise.reject(e);
    })
}