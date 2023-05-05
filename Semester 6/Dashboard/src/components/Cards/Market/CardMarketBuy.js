import React, {useEffect, useState} from "react";
import PropTypes from "prop-types";
import {MarketBuy} from "../../../services/API/MarketBuy";
import {MarketSell} from "../../../services/API/MarketSell";

// components

import MarketBuyDropdown from "components/Dropdowns/MarketBuyDropdown.js";

export default function CardMarketBuy({color, token}) {
    const [array, setArray] = useState([]);

    useEffect(() => {
        tableRows()
    }, []);

    const tableRows = () => {
        MarketSell.mockGetAllOffers()
            .then(response => {
                setArray(response.data);
            })
            .catch((error) => {
                setArray([
                    {
                        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                        "seller": "string",
                        "amount": 0,
                        "amountTotal": 0,
                        "price": 0
                    }
                ])
            });
    };

    return (
        <>
            <div
                className={
                    "relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded " +
                    (color === "light" ? "bg-white" : "bg-blue-900 text-white")
                }
            >
                <div className="rounded-t mb-0 px-4 py-3 border-0">
                    <div className="flex flex-wrap items-center">
                        <div className="relative w-full px-4 max-w-full flex-grow flex-1">
                            <h3
                                className={
                                    "font-semibold text-lg " +
                                    (color === "light" ? "text-gray-800" : "text-white")
                                }
                            >
                                Buy energy
                            </h3>
                        </div>
                    </div>
                </div>
                <div className="block w-full overflow-x-auto">
                    {/* Projects table */}
                    <table className="items-center w-full bg-transparent border-collapse">
                        <thead>
                        <tr>
                            <th
                                className={
                                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-no-wrap font-semibold text-left " +
                                    (color === "light"
                                        ? "bg-gray-100 text-gray-600 border-gray-200"
                                        : "bg-blue-800 text-blue-300 border-blue-700")
                                }
                            >
                                Seller
                            </th>
                            <th
                                className={
                                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-no-wrap font-semibold text-left " +
                                    (color === "light"
                                        ? "bg-gray-100 text-gray-600 border-gray-200"
                                        : "bg-blue-800 text-blue-300 border-blue-700")
                                }
                            >
                                Amount
                            </th>
                            <th
                                className={
                                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-no-wrap font-semibold text-left " +
                                    (color === "light"
                                        ? "bg-gray-100 text-gray-600 border-gray-200"
                                        : "bg-blue-800 text-blue-300 border-blue-700")
                                }
                            >
                                Total Amount
                            </th>
                            <th
                                className={
                                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-no-wrap font-semibold text-left " +
                                    (color === "light"
                                        ? "bg-gray-100 text-gray-600 border-gray-200"
                                        : "bg-blue-800 text-blue-300 border-blue-700")
                                }
                            >
                                Price
                            </th>
                            <th
                                className={
                                    "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-no-wrap font-semibold text-left " +
                                    (color === "light"
                                        ? "bg-gray-100 text-gray-600 border-gray-200"
                                        : "bg-blue-800 text-blue-300 border-blue-700")
                                }
                            ></th>
                        </tr>
                        </thead>
                        <tbody>
                        {array.map((listing, index) => {
                            const {id, seller, amount, amountTotal, price} = listing;
                            return (
                                <tr key={id}>
                                    <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4 text-left flex items-center">
                                <span
                                    className={
                                        "ml-3 font-bold " +
                                        +(color === "light" ? "text-gray-700" : "text-white")
                                    }
                                >
                    {seller}
                  </span>
                                    </th>
                                    <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4">
                                        {amount} mW
                                    </td>
                                    <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4">
                                        {amountTotal} mW
                                    </td>
                                    <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4">
                                        € {price} EUR
                                    </td>
                                    <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4 text-right">
                                        <MarketBuyDropdown listing={listing}/>
                                    </td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    );
}

CardMarketBuy.defaultProps = {
    color: "light",
};

CardMarketBuy.propTypes = {
    color: PropTypes.oneOf(["light", "dark"]),
};
