const productionObj = (name, lat, long, panels, power) => {
        return {
                name: name,
                type: 'sun',
                panels: panels,
                power: power,
                coord: {lat: lat, long: long},
        };
};

export const productionListSun =
    [
        productionObj("Zonnepark TT Assen",52.959080346198654,6.522335332326181,	21669, 5.60),
        productionObj("Zonnepark Lange Runde",52.74357628025197,7.025121835945035, 118200, 14.10),
        // productionObj("Zonnepark Oranjepoort",52.340533328120316, 4.874429629792224, 80000, 30),
        // productionObj("Zonnepark IJsselmeerdijk Lelystad",52.56956703924612, 5.519562538243253,24000,9.6),
        // productionObj("Zonnebron",52.973654823487806, 6.297873502062846, 43500, 12.5),
        // productionObj("Zonnepark Zuyderzon Almere",52.42008478199138, 5.242155727307979, 98560, 34),
        productionObj("Home Center Wolvega",52.88717835815901, 5.994553540813589, 15000, 3.9),
        productionObj("De Kie",53.01998418406829, 5.532251065724606, 29008, 10.15),
        productionObj("Zonnepark Ameland",53.44775429162105, 5.770834074257793, 23025, 6),
        productionObj("Zonnepark Harlingen",53.17493270821364, 5.412512098492834, 3528, 0.95),
        // productionObj("Zonnepark De Vaandel",52.68744442095964, 4.840679827315203, 34000, 9.6),
        productionObj("Zonnepark Appelscha Hoog",52.946877458669704, 6.402306778632846, 36000, 12.5),
        productionObj("Zonnepark Burgum",53.209604465082904, 6.028188946116552, 18432, 5.0),
        // productionObj("Armhoede",52.17655214710495, 6.454321229087287,23000, 8.9),
        // productionObj("Solarpark Azewijn",51.8912399828951, 6.305135399212944, 36000, 1.6),
        // productionObj("Sportpark Noordveen",52.1543230448216, 6.210809040793738, 6000, 1.6),
        // productionObj("Zonnepark Avri Solar", 51.867134403201256, 5.325286284964433,34368, 9.30),
        // productionObj("Zonnepark Laarberg", 52.06536921936057, 6.6094556696270415, 6664, 2.23),
        productionObj("SunPort Delfzijl", 53.30136059891158, 6.956691383624464, 116334, 30),
        productionObj("Zonnepark Veendam", 53.08980066366024, 6.876486527326198, 57288, 15.50),
        productionObj("Zonnepark Bedrijventerrein – Stadskanaal", 52.99124963872832, 6.935903052230413,117000, 13.9),
        productionObj("Zonnepark Mercurius", 52.97464648849616, 6.9635490561586915, 0, 4.4),
        productionObj("Zonnepark Roodehaan", 53.19175098202898, 6.638255422103297, 81000, 11.4),
        productionObj("Zonnepark Duurkenakker", 53.13989947638697, 6.899349681228425, 160000, 64),
        productionObj("Zonnepark Midden-Groningen", 53.17613196653412, 6.767140927328547, 316216, 102),
        // productionObj("Zonnepark Vlagtwedde", 53.2412499079304, 6.583132197215556, 320000, 110.073),
        // productionObj("Zonnepark Wieringermeer", 52.84054049300139, 5.0504378050125265, 11000, 40),
        // productionObj("Twence", 52.39934066104257, 6.787799326852511, 61000, 17.5),
        // productionObj("Wehkamp", 52.545644463857684, 6.175409673248436, 10000, 17.5),
        // productionObj("Zonnedak Scania", 52.51324910088159, 6.066974392447216, 22000, 6.0),
        // productionObj("Zonnepark Oosterweilanden", 52.409080131418456, 6.624551254330457,400000, 12	 ),
        // productionObj("Zonnepark Aadijk Almelo",52.375637527319455, 6.638863998511913, 120808, 39.7)
    ];


