import React, {useEffect, useState} from "react";

// components

import CardLineChart from "components/Cards/CardLineChart.js";
import CardBarChart from "components/Cards/CardBarChart.js";
import CardPageVisits from "components/Cards/CardPageVisits.js";
import CardSocialTraffic from "components/Cards/CardSocialTraffic.js";

// test components

import CardWindSlider from "../../components/Cards/CardWindSliders";
import CardSunSlider from "../../components/Cards/CardSunSliders";
import CardWindPredictions from "../../components/Cards/CardWindPredictions";
import CardWeather from "../../components/Cards/CardWeather";
import {productionListSun} from "../../lists/ProductionListSun";
import {Wind} from "../../services/API/Wind";

export default function WindView() {

    const [chartConfig, setChartConfig] = useState({title: '', data: [{ label: '', dataset: []}]});

    const resolveWind = () => {
        const promise = Wind.index();
        const myPromise = new Promise((resolve, reject) => {
            resolve(promise);
        });
        myPromise
            .then(response => {
                setWind(response)
            }, null)
    };

    const setWind = (response) => {
        let arr = [];
        response.data.map((value) => {
            arr.push(value.avgValue)
        });
        setChartConfig({title: 'Wind production', data: [{ label: 'Wind', dataset: [...arr]}]});
    };
    return (
        <>
            <div className="flex flex-wrap">
                <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">
                    <CardLineChart
                        title={chartConfig.title}
                        dataset={chartConfig.data}
                    />
                    <button
                        className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3 ease-linear transition-all duration-150"
                        type="button"
                        onClick={resolveWind}
                    >
                        {/*<i className="fas fa-refresh"></i> */}
                        Refresh
                    </button>
                </div>

            </div>

            <div className="flex flex-wrap mt-8">
            <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4 ">
                <CardWindSlider/>
            </div>
            <div className="w-full xl:w-4/12 px-4">
                <CardWindPredictions/>
            </div>
        </div>

            {/*<div className="flex flex-wrap mt-4">*/}
            {/*    <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">*/}
            {/*        <CardPageVisits/>*/}
            {/*    </div>*/}
            {/*    <div className="w-full xl:w-4/12 px-4">*/}
            {/*        <CardSocialTraffic/>*/}
            {/*    </div>*/}
            {/*</div>*/}
        </>
    );
}
