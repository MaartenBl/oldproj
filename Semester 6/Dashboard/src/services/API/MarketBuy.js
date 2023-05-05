import { get, post, put, destroy } from './marketBase';

export const MarketBuy = {
    // basic CRUD API usage

    buy: (params) =>
        post('/market/api/buy', params),
    getBuyHistory: () =>
        get('/market/service/buy'),
    getListed: () =>
        get('/market/service/buy/sales'),
    mockBuy: (params) =>
        post('/api/buy', params),
    mockGetBuyHistory: () =>
        get('/api/buy'),
    mockGetListed: () =>
        get('/api/buy/sales'),
};