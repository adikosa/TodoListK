import axios from 'axios'
import { logOut } from '../store/actions/authActions';
import { store } from '../store/store';
import {openSnackbar} from "../store/actions/snackbarActions";

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/',
    headers: {'Content-Type': 'application/json'}
});

instance.interceptors.response.use(response => {
    return response;
 }, error => {
    if (!error.response) {
        return Promise.reject("Server connection error");
    }

    const responseStatus = error.response.status
    if (responseStatus === 401) {
        store.dispatch(logOut())
    }

    return Promise.reject(error);
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
        return instance.delete(route, {data: null, headers: getAuthHeader()})
    }

    if(body === undefined) {
        return instance.delete(route, {data: null, headers: getAuthHeader(), params})
    }

    return instance.delete(route, {data: body, headers: getAuthHeader()})
}

export function patch_with_auth(route, body, params) {
    if(body === undefined && params === undefined) {
        return instance.patch(route, null, {headers: getAuthHeader()})
    }

    if(body === undefined) {
        return instance.patch(route, null, {headers: getAuthHeader(), params})
    }

    return instance.patch(route, body, {headers: getAuthHeader()})
}