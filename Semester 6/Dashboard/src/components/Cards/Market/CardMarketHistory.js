import React, {useEffect, useState} from "react";
import PropTypes from "prop-types";

// components
import {MarketBuy} from "../../../services/API/MarketBuy";
import {MarketSell} from "../../../services/API/MarketSell";

export default function CardMarketHistory({ color }) {
    const [array, setArray] = useState([]);
    useEffect(() => {
        tableRows()
    }, []);

    const tableRows = () => {
        MarketBuy.mockGetListed()
            .then(response => {
                setArray(response.data);
            })
            .catch((error) => {
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
                                Seller history
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
                                Buyer
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
                            {/*<th*/}
                            {/*    className={*/}
                            {/*        "px-6 align-middle border border-solid py-3 text-xs uppercase border-l-0 border-r-0 whitespace-no-wrap font-semibold text-left " +*/}
                            {/*        (color === "light"*/}
                            {/*            ? "bg-gray-100 text-gray-600 border-gray-200"*/}
                            {/*            : "bg-blue-800 text-blue-300 border-blue-700")*/}
                            {/*    }*/}
                            {/*>*/}
                            {/*    Amount total*/}
                            {/*</th>*/}
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

                        </tr>
                        </thead>
                        <tbody>
                        {array.map((listing, index) => {
                            const {buyerName, offer, amount, priceTotal} = listing;
                            return (
                                <tr key={offer.id}>
                                    <th className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4 text-left flex items-center">
                                <span
                                    className={
                                        "ml-3 font-bold " +
                                        +(color === "light" ? "text-gray-700" : "text-white")
                                    }
                                >
                    {buyerName}
                  </span>
                                    </th>
                                    <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4">
                                        {amount} mW
                                    </td>
                                    {/*<td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4">*/}
                                    {/*    {offer.} mW*/}
                                    {/*</td>*/}
                                    <td className="border-t-0 px-6 align-middle border-l-0 border-r-0 text-xs whitespace-no-wrap p-4">
                                        € {priceTotal} EUR
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

CardMarketHistory.defaultProps = {
    color: "light",
};

CardMarketHistory.propTypes = {
    color: PropTypes.oneOf(["light", "dark"]),
};
