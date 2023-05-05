import React, {useState, useEffect} from "react";
import {Wind} from "../../services/API/Wind";

export default function CardWindPredictions() {

    const [predictionWind, setPredictionWind] = useState();

    useEffect(() => {
        async function fetchData() {
            const promise = await Wind.prediction();
            const myPromise = new Promise((resolve, reject) => {
                resolve(promise);
            });
            await myPromise
                .then(response => {
                    setPredictionWind(response.data);
                }, null)
        }
        if (predictionWind === undefined) {
            fetchData()
        }
    }, [predictionWind]);

    return (
        <>
            <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
                <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
                    <div className="flex flex-wrap items-center">
                        <div className="relative w-full max-w-full flex-grow flex-1">
                            <h6 className="uppercase text-gray-500 mb-1 text-xs font-semibold">
                                Prediction
                            </h6>
                            <h2 className="text-gray-800 text-xl font-semibold">
                                Wind Energy
                            </h2>
                        </div>
                    </div>
                </div>
                <div className="p-4 flex-auto">
                    {/* Chart */}
                    <div className="relative h-350-px">
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            TODAY
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            {predictionWind !== undefined ? predictionWind[0].avgValue + " kWh" : " kWh"}
                        </div>
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            TOMMOROW
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            {predictionWind !== undefined ? predictionWind[1].avgValue + " kWh" : " kWh"}
                        </div>
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            OVERMORROW
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            {predictionWind !== undefined ? predictionWind[2].avgValue + " kWh" : " kWh"}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
