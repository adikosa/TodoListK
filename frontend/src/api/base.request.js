import axios from 'axios'
import { store } from '../store/store';

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/',
    headers: {'Content-Type': 'application/json'}
});

function getBearerToken() {
    const credentials = store.getState().auth.userCredentials;

    if (credentials) {
        return 'Bearer ' + credentials.token
    } else {
        return '';
    }
}

function getAuthHeader(){
    return {'Authorization': getBearerToken(), 'Content-Type': 'application/json'}
}

export function get(route, body) {
    return instance.get(route, body)
}

export function get_with_auth(route, body) {
    if(body === undefined) {
        return instance.get(route, {headers: getAuthHeader()})
    }
    return instance.get(route, body, {headers: getAuthHeader()})
}

export function post(route, body) {
    return instance.post(route, body)
}

export function post_with_auth(route, body, params) {
    if(body === undefined && params === undefined) {
        return instance.post(route, null, {headers: getAuthHeader()})
    }

    if(body === undefined) {
        return instance.post(route, null, {headers: getAuthHeader(), params})
    }

    return instance.post(route, body, {headers: getAuthHeader()})
}

export function delete_with_auth(route, body, params) {
    if(body === undefined && params === undefined) {
        return instance.delete(route, null, {headers: getAuthHeader()})
    }

    if(body === undefined) {
        return instance.delete(route, {data: null, headers: getAuthHeader(), params})
    }

    return instance.delete(route, {data: body, headers: getAuthHeader()})
}

// function handleResponse(response) {
//     return response.text().then(text => {
//         const data = text && JSON.parse(text);
//         if (!response.ok) {
//             if (response.status === 401) {
//                 // auto logout if 401 response returned from api
//                 // logout();
//                 // location.reload(true);
//             }

//             const error = (data && data.message) || response.statusText;
//             return Promise.reject(error);
//         }

//         return data;
//     });
// }