import React, {useState} from "react";
import DefaultSlider from "../../components/Sliders/DefaultSlider";
import {Sun} from "../../services/API/Sun";

export default function CardSunSliders() {
    const [sliderConfig, setSliderConfig] = useState({sunUp: 0, sun: 0});
    const [value, setValue] = useState("Simulation");

    const onWSunUpSliderChange = (e, newValue) => {
        setSliderConfig({...sliderConfig, sunUp: newValue});
    };
    const onSunSliderChange = (e, newValue) => {
        setSliderConfig({...sliderConfig, sun: newValue});
    };

    return (
        <>
            <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
                <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
                    <div className="flex flex-wrap items-center">
                        <div className="relative w-full max-w-full flex-grow flex-1">
                            <h6 className="uppercase text-gray-500 mb-1 text-xs font-semibold">
                                Simulation
                            </h6>
                            <h2 className="text-gray-800 text-xl font-semibold">
                                Solar panels
                            </h2>
                        </div>
                    </div>
                </div>
                <div className="p-4 flex justify-evenly">
                    {/* Card */}
                    <div className="relative h-350-px align-middle">
                        <DefaultSlider
                            label={<span style={{display: "flex", flexFlow: "row"}}>Sun Up Time</span>}
                            step={1}
                            min={0}
                            max={24}
                            onChange={onWSunUpSliderChange}
                        />
                        <DefaultSlider
                            label={<span style={{display: "flex", flexFlow: "row"}}>Solar Radiation</span>}
                            step={1}
                            min={0}
                            max={40}
                            onChange={onSunSliderChange}
                        />
                        <button
                            className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 mb-3 ease-linear transition-all duration-150"
                            type="button"
                            onClick={() => Sun.predict(sliderConfig.sunUp, sliderConfig.sun)
                                .then(response => setValue(response.data[0].avgValue))}
                            style={{width: '300px'}}
                        >
                            {/*<i className="fas fa-arrow-alt-circle-down"></i> */}
                            SIMULATE
                        </button>
                    </div>
                    <div className="relative min-w-300-px h-300-px" style={{
                        background: "radial-gradient(circle, transparent 50%, #3182ce 100%)",
                        borderRadius: "100%",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                    }}>
                        <span style={{
                            fontSize: "2em",
                            fontWeight: "bold"
                        }}>{value} kWh</span>
                    </div>
                </div>
            </div>

        </>
    );
}
