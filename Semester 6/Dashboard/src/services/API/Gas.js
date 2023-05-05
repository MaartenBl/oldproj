import { get, post, put, destroy } from './base';

export const Gas = {
    // basic CRUD API usage

    index: () =>
        get('/gas/all'),

    predict: (params) =>
        get(`gas/predict`, params)

    // basic CRUD examples

    // create: (params) =>
    //     post('/users', params),
    // update: (id, params) =>
    //     put(`/users/${id}`, params),
    // remove: (id) =>
    //     destroy(`/users/${id}`),

    // specific API usage

    // single: (id) =>
    //     get(`/users/${id}`),
    // singleByEmail: (email) =>
    //     get(`/users?email=${email}`),
};