import { get, post, put, destroy } from './marketBase';

export const MarketAccount = {
    // basic CRUD API usage

    login: (params) =>
        post('/market/api/login', params),
    balance: () =>
        get('/market/api/balance'),
    mockLogin: (params) =>
        post('/api/login', params),
    mockBalance: () =>
        get('/api/balance'),
};