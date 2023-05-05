const productionObj = (name, lat, long, turbines, power) => {
    return {
        name: name,
        type: 'wind',
        turbines: turbines,
        power: power,
        coord: {lat: lat, long: long},
    };
};

///TODO: Check missing coordinates

export const productionListWind =
    [
        // productionObj("Windpark Jaap Rodenburg",52.37631307458757, 5.133258825320985,10,16.5 ),
        // productionObj("Windpark Kubbeweg",52.44977028801539, 5.6518716253231425,17,34 ),
        // productionObj("Windpark Noordoostpolder",0,0,86,429 ),
        // productionObj("Windpark Westermeerwind",52.71697303682511, 5.592366330260749,48,144 ),
        productionObj("Windpark A7",53.0846478755386, 5.390558116285688,4, 8),
        productionObj("Windpark Fryslân",53.00654899892688, 5.265390086550973,89, 382.7 ),
        productionObj("Windpark Nij Hiddum-Houw",53.094209305678056, 5.398196684862301,9, 41.85),
        // productionObj("Windpark Nijmegen Betuwe",51.89267226077435, 5.839121440648914,4, 10),
        productionObj("Windpark Delfzijl Noord",53.31276592747586, 6.984398371865309,19, 62.2),
        productionObj("Windpark Delfzijl Noord",53.31276592747586, 6.984398371865309,19, 62.2),
        // productionObj("Windpark Burgervlotbrug",52.786763338915094, 4.802333629333802,9, 7.65),
        // productionObj("Windpark Waardpolder",52.81572535165996, 4.886614110325221,	6, 21.6),
        // productionObj("Windpark Kloosterlanden",0,0,2, 2.35),
        // productionObj("Windpark Spoorwind",0,0,3, 6.9),
        // productionObj("Windpark Tolhuislanden",52.56286382152637, 6.2059317676553665,4, 9.6),
        // productionObj("Windpark De Veenwieken",52.56429008034995, 6.433744911833191,10, 23.5),
        // productionObj("Windpark Houten",0,0,3, 6),
        // productionObj("Windpark Nieuwegein",52.017594812706044, 5.122005011817052,7, 29.4),
        // productionObj("Windpark Autena",51.96915397867927, 5.1073904983222835,3, 9),
    ];

