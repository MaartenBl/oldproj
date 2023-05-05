import React, {useState} from "react";
import DefaultSlider from "../../components/Sliders/DefaultSlider";
import {Wind} from "../../services/API/Wind";

export default function CardWindSliders() {
    const [sliderConfig, setSliderConfig] = useState({wind: 0, turbines: 0, rotar: 40, air: 0});
    const [value, setValue] = useState("Simulation");

    const onWindSliderChange = (e, newValue) => {
        setSliderConfig({...sliderConfig, wind: newValue});
    };
    const onTurbineSliderChange = (e, newValue) => {
        setSliderConfig({...sliderConfig, turbines: newValue});
    };
    const onRotarSliderChange = (e, newValue) => {
        setSliderConfig({...sliderConfig, rotar: newValue});
    };
    const onAirSliderChange = (e, newValue) => {
        setSliderConfig({...sliderConfig, air: newValue});
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
                                Windmills
                            </h2>
                        </div>
                    </div>
                </div>
                <div className="p-4 flex justify-evenly">
                    {/* Card */}
                    <div className="relative h-350-px">
                        <DefaultSlider
                            label={<span style={{display: "flex", flexFlow: "row"}}>Wind Speed<span style={{fontSize: "0.6em"}}>m/s</span></span>}
                            step={1}
                            min={0}
                            max={29}
                            onChange={onWindSliderChange}
                        />
                        <DefaultSlider
                            label={<span style={{display: "flex", flexFlow: "row"}}>Turbines<span style={{fontSize: "0.6em"}}>Units</span></span>}
                            step={1}
                            min={0}
                            max={1000}
                            onChange={onTurbineSliderChange}
                        />
                        <DefaultSlider
                            label={<span style={{display: "flex", flexFlow: "row"}}>Rotarblades<span style={{fontSize: "0.6em"}}>diameter</span></span>}
                            step={1}
                            min={40}
                            max={80}
                            onChange={onRotarSliderChange}
                        />
                        <DefaultSlider
                            label={<span style={{display: "flex", flexFlow: "row"}}>Air Density <span style={{fontSize: "0.6em"}}>kg/m3</span></span>}
                            step={0.1}
                            min={0}
                            max={2}
                            onChange={onAirSliderChange}
                        />
                        <button
                            className="bg-blue-500 text-white active:bg-blue-600 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-lg outline-none focus:outline-none lg:mr-1 lg:mb-0 mb-3 ease-linear transition-all duration-150"
                            type="button"
                            onClick={() => Wind.predict(sliderConfig.wind, sliderConfig.turbines, sliderConfig.rotar, sliderConfig.air)
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
