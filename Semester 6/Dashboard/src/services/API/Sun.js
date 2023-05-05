import { get, post, put, destroy } from './base';

export const Sun = {
    // basic CRUD API usage

    index: () =>
        get('/sun/all'),

    predict: (sunUp, sun, params) =>
        get(`/sun/predict?sunUp=${sunUp}&sun=${sun}`, params),

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