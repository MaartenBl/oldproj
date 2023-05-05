import React, {useEffect, useState} from "react";

// components

import CardLineChart from "components/Cards/CardLineChart.js";

// test component
import {Nuclear} from "../../services/API/Nuclear";

export default function NuclearView() {
    const [chartConfig, setChartConfig] = useState({title: '', data: [{ label: '', dataset: []}]});


    const resolveNuclear = () => {
        const promise = Nuclear.index();
        const myPromise = new Promise((resolve, reject) => {
            resolve(promise);
        });
        myPromise
            .then(response => {
                setNuclear(response)
            }, null)
    };

    const setNuclear = (response) => {
        let arr = [];
        response.data.map((value) => {
            arr.push(value.avgValue)
        });
        setChartConfig({title: 'Nuclear production', data: [{ label: 'Nuclear', dataset: [...arr]}]});
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
                        onClick={resolveNuclear}
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
