import { get, post, put, destroy } from './base';

export const Oil = {
    // basic CRUD API usage

    index: () =>
        get('/oil/all'),

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