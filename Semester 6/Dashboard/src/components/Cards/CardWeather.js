import React, {useState, useEffect} from "react";
import {Weather} from "../../services/API/Weather";

export default function CardWeather() {

    const [predictionWeather, setPredictionWeather] = useState();

    useEffect(() => {
        async function fetchData() {
            const promise = await Weather.index();
            const myPromise = new Promise((resolve, reject) => {
                resolve(promise);
            });
            await myPromise
                .then(response => {
                    setPredictionWeather(response.data);
                }, null)
        }
        if (predictionWeather === undefined) {
            fetchData()
        }
    }, [predictionWeather]);

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
                                Weather
                            </h2>
                        </div>
                    </div>
                </div>
                <div className="p-4 flex-auto">
                    {/* Chart */}
                    <div className="relative h-350-px">
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            Maximum Temperature
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            {predictionWeather !== undefined ? predictionWeather[0].maxTemp + " °C" : " °C"}
                        </div>
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            Minimum Temperature
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            {predictionWeather !== undefined ? predictionWeather[0].minTemp + " °C" : " °C"}
                        </div>
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            Wind Speed
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            {predictionWeather !== undefined ? predictionWeather[0].windSpeed + " m/s" : " m/s"}
                        </div>
                        <h3 className="text-xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            Note
                        </h3>
                        <div className="text-sm leading-normal mt-0 mb-2 text-red-500 font-bold uppercase">
                            {predictionWeather !== undefined ? predictionWeather[0].note  : null}
                        </div>
                    </div>
                </div>

            </div>
        </>
    );
}
