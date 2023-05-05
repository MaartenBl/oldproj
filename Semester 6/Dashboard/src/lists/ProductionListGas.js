const productionObj = (name, lat, long) => {
    return {
        name: name,
        type: 'gas',
        coord: {lat: lat, long: long},
    };
};

export const productionListGas =
    [
        productionObj("", ),
    ];

