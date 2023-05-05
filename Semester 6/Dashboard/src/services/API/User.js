import { get, post, put, destroy } from './base';

export const User = {
   // basic CRUD API usage

    // index: () =>
    //     get('/users'),
    create: (name, email, password, params) =>
        post(`/user/add?displayName=${name}&email=${email}&password=${password}`, params),
    // update: (id, params) =>
    //     put(`/users/${id}`, params),
    // remove: (id) =>
    //     destroy(`/users/${id}`),

    // specific API usage

    authenticate: (params) =>
        post('/user/authenticate', params)
    // single: (id) =>
    //     get(`/users/${id}`),
    // singleByEmail: (email) =>
    //     get(`/users?email=${email}`),
};