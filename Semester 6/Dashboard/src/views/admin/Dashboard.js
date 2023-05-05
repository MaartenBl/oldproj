import React, {useEffect, useState} from "react";

// components

import CardLineChart from "components/Cards/CardLineChart.js";
import CardBarChart from "components/Cards/CardBarChart.js";
import CardPageVisits from "components/Cards/CardPageVisits.js";
import CardSocialTraffic from "components/Cards/CardSocialTraffic.js";

// test component
// import {Oil} from "../../services/API/Oil";
// import {Nuclear} from "../../services/API/Nuclear";
// import {Wind} from "../../services/API/Wind";
// import {Sun} from "../../services/API/Sun";
// import {Gas} from "../../services/API/Gas";
// import {productionListSun} from "../../lists/ProductionListSun";

export default function Dashboard() {
    // const [chartConfig, setChartConfig] = useState({title: '', data: [{ label: '', dataset: []}]});
    //
    // const resolveWind = () => {
    //     const promise = Wind.index();
    //     const myPromise = new Promise((resolve, reject) => {
    //         resolve(promise);
    //     });
    //     myPromise
    //         .then(response => {
    //             setWind(response)
    //         }, null)
    // };
    //
    // const setWind = (response) => {
    //     let arr = [];
    //     response.data.map((value) => {
    //         arr.push(value.avgValue)
    //     });
    //     setChartConfig({title: 'Wind production', data: [{ label: 'Wind', dataset: [...arr]}]});
    // };
    //
    // const resolveNuclear = () => {
    //     const promise = Nuclear.index();
    //     const myPromise = new Promise((resolve, reject) => {
    //         resolve(promise);
    //     });
    //     myPromise
    //         .then(response => {
    //             setNuclear(response)
    //         }, null)
    // };
    //
    // const setNuclear = (response) => {
    //     let arr = [];
    //     response.data.map((value) => {
    //         arr.push(value.avgValue)
    //     });
    //     setChartConfig({title: 'Nuclear production', data: [{ label: 'Nuclear', dataset: [...arr]}]});
    // };
    //
    // const resolveSun = () => {
    //     const promise = Sun.index();
    //     const myPromise = new Promise((resolve, reject) => {
    //         resolve(promise);
    //     });
    //     myPromise
    //         .then(response => {
    //             setSun(response)
    //         }, null)
    // };
    //
    // const setSun = (response) => {
    //     let arr = [];
    //     response.data.map((value) => {
    //         arr.push(value.avgValue)
    //     });
    //     setChartConfig({title: 'Sun production', data: [{ label: 'Sun', dataset: [...arr]}]});
    // };
    //
    // const resolveOil = () => {
    //     const promise = Oil.index();
    //     const myPromise = new Promise((resolve, reject) => {
    //         resolve(promise);
    //     });
    //     myPromise
    //         .then(response => {
    //             setOil(response)
    //         }, null)
    // };
    //
    // const setOil = (response) => {
    //     let arr = [];
    //     response.data.map((value) => {
    //         arr.push(value.avgValue)
    //     });
    //     setChartConfig({title: 'Oil production', data: [{ label: 'Oil', dataset: [...arr]}]});
    // };
    //
    // const resolveGas = () => {
    //     const promise = Gas.index();
    //     const myPromise = new Promise((resolve, reject) => {
    //         resolve(promise);
    //     });
    //     myPromise
    //         .then(response => {
    //             setGas(response)
    //         }, null)
    // };
    //
    // const setGas = (response) => {
    //     let arr = [];
    //     response.data.map((value) => {
    //         arr.push(value.avgValue)
    //     });
    //     setChartConfig({title: 'Gas production', data: [{ label: 'Gas', dataset: [...arr]}]});
    // };

    return (
        <>
            <div className="flex flex-wrap">
            {/*    <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">*/}
            {/*        <CardLineChart*/}
            {/*            title={chartConfig.title}*/}
            {/*            dataset={chartConfig.data}*/}
            {/*        />*/}
            {/*    </div>*/}
            {/*    /!*<div className="w-full xl:w-4/12 px-4">*!/*/}
            {/*    /!*    <CardBarChart/>*!/*/}
            {/*    /!*</div>*!/*/}
            {/*</div>*/}
            {/*<div className="flex flex-wrap mt-4">*/}
            {/*    /!*<div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">*!/*/}
            {/*    /!*    <CardPageVisits/>*!/*/}
            {/*    /!*</div>*!/*/}
            {/*    /!*<div className="w-full xl:w-4/12 px-4">*!/*/}
            {/*    /!*    <CardSocialTraffic/>*!/*/}
            {/*    /!*</div>*!/*/}
            {/*    <button*/}
            {/*        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"*/}
            {/*        type="button"*/}
            {/*        onClick={resolveWind}*/}
            {/*    >*/}
            {/*        /!*<i className="fas fa-arrow-alt-circle-down"></i> *!/*/}
            {/*        API-Wind*/}
            {/*    </button>*/}
            {/*    <button*/}
            {/*        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"*/}
            {/*        type="button"*/}
            {/*        onClick={resolveNuclear}*/}
            {/*    >*/}
            {/*        /!*<i className="fas fa-arrow-alt-circle-down"></i> *!/*/}
            {/*        API-Nuclear*/}
            {/*    </button>*/}
            {/*    <button*/}
            {/*        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"*/}
            {/*        type="button"*/}
            {/*        onClick={resolveOil}*/}
            {/*    >*/}
            {/*        /!*<i className="fas fa-arrow-alt-circle-down"></i> *!/*/}
            {/*        API-Oil*/}
            {/*    </button>*/}
            {/*    <button*/}
            {/*        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"*/}
            {/*        type="button"*/}
            {/*        onClick={resolveSun}*/}
            {/*    >*/}
            {/*        /!*<i className="fas fa-arrow-alt-circle-down"></i> *!/*/}
            {/*        API-Sun*/}
            {/*    </button>*/}
            {/*    <button*/}
            {/*        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"*/}
            {/*        type="button"*/}
            {/*        onClick={resolveGas}*/}
            {/*    >*/}
            {/*        /!*<i className="fas fa-arrow-alt-circle-down"></i> *!/*/}
            {/*        API-Gas*/}
            {/*    </button>*/}
            {/*    <button*/}
            {/*        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"*/}
            {/*        type="button"*/}
            {/*        onClick={  () => console.log(productionListSun)}*/}
            {/*    >*/}
            {/*        /!*<i className="fas fa-arrow-alt-circle-down"></i> *!/*/}
            {/*        production*/}
            {/*    </button>*/}
            </div>
        </>
    );
}
