import React, {useEffect, useState} from "react";

// components

import CardLineChart from "components/Cards/CardLineChart.js";
import CardBarChart from "components/Cards/CardBarChart.js";
import CardPageVisits from "components/Cards/CardPageVisits.js";
import CardSocialTraffic from "components/Cards/CardSocialTraffic.js";

// test component
import {Gas} from "../../services/API/Gas";

export default function GasView() {
    const [chartConfig, setChartConfig] = useState({title: '', data: [{ label: '', dataset: []}]});

    const resolveGas = () => {
        const promise = Gas.index();
        const myPromise = new Promise((resolve, reject) => {
            resolve(promise);
        });
        myPromise
            .then(response => {
                setGas(response)
            }, null)
    };

    const setGas = (response) => {
        let arr = [];
        response.data.map((value) => {
            arr.push(value.avgValue)
        });
        setChartConfig({title: 'Gas production', data: [{ label: 'Gas', dataset: [...arr]}]});
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
                        onClick={resolveGas}
                    >
                        {/*<i className="fas fa-arrow-alt-circle-down"></i> */}
                        Refresh
                    </button>
                </div>
                {/*<div className="w-full xl:w-4/12 px-4">*/}
                {/*    <CardBarChart/>*/}
                {/*</div>*/}
            </div>

        </>
    );
}
