import React, {useEffect, useState} from "react";

// components

import CardLineChart from "components/Cards/CardLineChart.js";
import CardBarChart from "components/Cards/CardBarChart.js";
import CardPageVisits from "components/Cards/CardPageVisits.js";
import CardSocialTraffic from "components/Cards/CardSocialTraffic.js";

// test component
import {Sun} from "../../services/API/Sun";
import CardWeather from "../../components/Cards/CardWeather";
import CardSunSlider from "../../components/Cards/CardSunSliders";

export default function SunView() {
    const [chartConfig, setChartConfig] = useState({title: '', data: [{ label: '', dataset: []}]});

    const resolveSun = () => {
        const promise = Sun.index();
        const myPromise = new Promise((resolve, reject) => {
            resolve(promise);
        });
        myPromise
            .then(response => {
                setSun(response)
            }, null)
    };

    const setSun = (response) => {
        let arr = [];
        response.data.map((value) => {
            arr.push(value.avgValue)
        });
        setChartConfig({title: 'Sun production', data: [{ label: 'Sun', dataset: [...arr]}]});
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
                        onClick={resolveSun}
                    >
                        {/*<i className="fas fa-arrow-alt-circle-down"></i> */}
                        Refresh
                    </button>
                </div>
                {/*<div className="w-full xl:w-4/12 px-4">*/}
                {/*    <CardBarChart/>*/}
                {/*</div>*/}

            </div>

            <div className="flex flex-wrap mt-8">
                <div className="w-full xl:w-4/12 px-4">
                    <CardWeather/>
                </div>
                <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">
                    <CardSunSlider/>
                </div>
            </div>
        </>
    );
}
