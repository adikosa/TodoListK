import axios from "axios";
import {API_URL} from "./Api";

const TODOS_ENDPOINT_URL = API_URL + "todos/";

export function getAll(){
    return axios
        .get(TODOS_ENDPOINT_URL);
}

export function getById(id){
    return axios
        .get(TODOS_ENDPOINT_URL + id);
}