import axios from 'axios'

const AUTH_HEADER = {
    'Authorization': getBearerToken()
}

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/',
    headers: {'Content-Type': 'application/json'}
});

function getBearerToken() {
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.token) {
        return 'Bearer ' + user.token
    } else {
        return '';
    }
}

export function get(route, body) {
    return instance.get(route, body)
}

export function get_with_auth(route, body) {
    return instance.get(route, {headers: {AUTH_HEADER}}, body)
}

export function post(route, body) {
    return instance.post(route, body)
}

export function post_with_auth(route, body) {
    return instance.post(route, {headers: {AUTH_HEADER}}, body)
}

export function delete_with_auth(route, body) {
    return instance.delete(route, {headers: {AUTH_HEADER}}, body)
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