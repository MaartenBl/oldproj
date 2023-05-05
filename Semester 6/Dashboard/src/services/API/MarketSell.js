import { get, post, put, destroy } from './marketBase';

export const MarketSell = {
    // basic CRUD API usage

    offer: (params) =>
        post('/market/service/offer', params),
    deleteOffer: (params) =>
        destroy('/market/service/offer', params),
    getOfferById: (params) =>
        get('/market/service/offer', params),
    getAllOffers: () =>
        get('/market/service/offer/all'),
    getUserOffers: () =>
        get('/market/service/offer/own'),

    mockOffer: (params) =>
        post('/api/offer', params),
    mockDeleteOffer: (params) =>
        destroy('/api/offer', params),
    mockGetOfferById: (params) =>
        get('/api/offer', params),
    mockGetAllOffers: () =>
        get('/api/offer/all'),
    mockGetUserOffers: () =>
        get('/api/offer/own'),
};