import { get, post, put, destroy } from './base';

export const InvoiceBuy = {
    // basic CRUD API usage

    buy: (params) =>
        post('/market/api/buy', params),
    getOffers: (params) =>
        post('/invoice/getoffers', params),
    getHistory: (params) =>
        post('/invoice/gethistory', params),
};