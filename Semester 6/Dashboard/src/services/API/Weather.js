import { get, post, put, destroy } from './base';

export const Weather = {
    // basic CRUD API usage

    // index: () =>
    //     get('/wind/all'),

    // basic CRUD examples

    // create: (params) =>
    //     post('/users', params),
    // update: (id, params) =>
    //     put(`/users/${id}`, params),
    // remove: (id) =>
    //     destroy(`/users/${id}`),

    // specific API usage

    index: () =>
        get('/meteoserver/weather'),

    regionWeather: (location, params) =>
        get(`/meteoserver/forecast?location=${location}`)

    // single: (id) =>
    //     get(`/users/${id}`),
    // singleByEmail: (email) =>
    //     get(`/users?email=${email}`),
};