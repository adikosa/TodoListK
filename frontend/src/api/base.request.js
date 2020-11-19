import config from "config";

function getBearerToken() {
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.token) {
        return 'Bearer ' + user.token
    } else {
        return '';
    }
}

export const HTTP_METHOD = {
    GET: 'GET',
    POST: 'POST',
    DELETE: 'DELETE'
};

function request_with_auth(route, method, body) {
    const requestOptions = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearerToken()
        },
        body: JSON.stringify(body)
    };

    return fetch(`${config.BASE_API_URL}` + route, requestOptions).then(handleResponse);
}

function request(route, method, body) {
    const requestOptions = {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    };

    return fetch(`${config.BASE_API_URL}` + route, requestOptions)
        .then(handleResponse);
}

export function get(route, body) {
    return request(route, HTTP_METHOD.GET, body)
}

export function get_with_auth(route, body) {
    return request_with_auth(route, HTTP_METHOD.GET, body)
}

export function post(route, body) {
    return request(route, HTTP_METHOD.POST, body)
}

export function post_with_auth(route, body) {
    return request_with_auth(route, HTTP_METHOD.POST, body)
}

export function delete_with_auth(route, body) {
    return request_with_auth(route, HTTP_METHOD.DELETE, body)
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                // auto logout if 401 response returned from api
                // logout();
                // location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}