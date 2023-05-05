import { get, post, put, destroy } from './base';

export const Wind = {
    // basic CRUD API usage

    index: () =>
        get('/wind/all'),

    // basic CRUD examples

    // create: (params) =>
    //     post('/users', params),
    // update: (id, params) =>
    //     put(`/users/${id}`, params),
    // remove: (id) =>
    //     destroy(`/users/${id}`),

    // specific API usage

    predict: (speed, turbines, rotar, air, params) =>
        get(`/wind/predict?windspeed=${speed}&turbines=${turbines}&rotar=${rotar}&air=${air}`, params),

    prediction: () =>
        get('/wind/prediction')

    // single: (id) =>
    //     get(`/users/${id}`),
    // singleByEmail: (email) =>
    //     get(`/users?email=${email}`),
};