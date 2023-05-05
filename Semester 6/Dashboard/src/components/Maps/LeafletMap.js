import React from "react"
import 'leaflet/dist/leaflet.css';
import {renderToStaticMarkup} from 'react-dom/server';
import {divIcon} from 'leaflet';
import {MapContainer, TileLayer, Marker, Popup} from 'react-leaflet'
import {productionListSun} from "../../lists/ProductionListSun.js";


function LeafletMap(props) {

    const iconSunMarkup = renderToStaticMarkup(<div><i className="relative fa fa-map-marker fa-3x"/><i
        style={{marginTop: "-28px", marginLeft: "60%"}} className=" absolute far fa-sun text-white"/></div>);

    const iconWindMarkup = renderToStaticMarkup(<div><i className="relative fa fa-map-marker fa-3x"/><i
        style={{marginTop: "-28px", marginLeft: "60%"}} className=" absolute fas fa-wind text-white"/></div>);

    const customMarkerIcon = (type) => {
        let iconMarkup = null;
        switch (type) {
            case 'sun':
                iconMarkup = iconSunMarkup;
                break;
            case 'wind':
                iconMarkup = iconWindMarkup;
                break;
            default:
                iconMarkup = iconSunMarkup;
        }

        return divIcon({
            html: iconMarkup,
            className: 'border-none'
        });
    };


    return (
        <MapContainer className="relative w-full rounded h-600-px" center={[51.505, -0.09]} zoom={5}
                      scrollWheelZoom={true}>
            <TileLayer
                attribution='&copy; <a href="https://stadiamaps.com/">Stadia Maps</a>, &copy; <a href="https://openmaptiles.org/">OpenMapTiles</a> &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors'
                url="https://tiles.stadiamaps.com/tiles/alidade_smooth/{z}/{x}/{y}{r}.png"
            />
            {props.markerArr.map((elm) => {
                return (
                    <Marker position={[elm.coord.lat, elm.coord.long]} icon={customMarkerIcon(elm.type)}>
                        <Popup>
                            <div className="flex flex-col">
                                <div className="flex">
                                    {elm.name}
                                </div>
                                <div className="flex">
                                    {elm.turbines === undefined ? null : `Turbines: ${elm.turbines}`}
                                </div>
                                <div className="flex">
                                    {elm.panels === undefined ? null : `Panels: ${elm.panels}`}
                                </div>
                                <div className="flex">
                                    {elm.power === undefined ? null : `Rated power: ${elm.power} MW`}
                                </div>
                            </div>
                        </Popup>
                    </Marker>
                )
            })}
        </MapContainer>
    );
}

export default LeafletMap;
