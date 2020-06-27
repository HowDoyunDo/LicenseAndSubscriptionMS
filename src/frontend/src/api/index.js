import axios from 'axios';

function createInstance() {
    return axios.create({
        baseURL: 'http://localhost:9000/api/',
        changeOrigin: true,
    });
}

export const instance = createInstance();

