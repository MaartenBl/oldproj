import React, {useState} from "react";
import { createPopper } from "@popperjs/core";
import {MarketAccount} from "../../services/API/MarketAccount";
import {MarketSell} from "../../services/API/MarketSell";

const MarketSellDropdown = (props) => {
    // dropdown props
    const [dropdownPopoverShow, setDropdownPopoverShow] = React.useState(false);
    const [offer, setOffer] = useState({amount: '', price: ''});
    const [showModal, setShowModal] = React.useState(false);
    const btnDropdownRef = React.createRef();
    const popoverDropdownRef = React.createRef();
    const openDropdownPopover = () => {
        createPopper(btnDropdownRef.current, popoverDropdownRef.current, {
            placement: "left-start",
        });
        setDropdownPopoverShow(true);
    };
    const closeDropdownPopover = () => {
        setDropdownPopoverShow(false);
    };
    const onPriceChange = (e) => {
        setOffer({...offer, price: e.target.value});
        // clearValidationErr('email')
    };

    const onAmountChange = (e) => {
        setOffer({...offer, amount: e.target.value});
        // clearValidationErr('password')
    };
    return (
        <>
            <a
                className="text-gray-600 py-1 px-3"
                href="#pablo"
                ref={btnDropdownRef}
                onClick={(e) => {
                    e.preventDefault();
                    dropdownPopoverShow ? closeDropdownPopover() : openDropdownPopover();
                }}
            >
                <i className="fas fa-ellipsis-v"></i>
            </a>
            <div
                ref={popoverDropdownRef}
                className={
                    (dropdownPopoverShow ? "block " : "hidden ") +
                    "bg-white text-base z-50 float-left py-2 list-none text-left rounded shadow-lg min-w-48"
                }
            >
                <a
                    href="#pablo"
                    className={
                        "text-sm py-2 px-4 font-normal block w-full whitespace-no-wrap bg-transparent text-gray-800"
                    }
                    onClick={ () => setShowModal(true)}
                >
                    Register
                </a>

            </div>
            {showModal ? (
                <>
                    <div
                        className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none "
                        // onClick={() => setShowModal(false)}
                    >
                        <div className="relative w-auto  my-6 mx-auto max-w-sm ">
                            {/*content*/}
                            <div className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                                {/*header*/}
                                <div className="flex items-start justify-between p-5 border-b border-solid border-blueGray-200 rounded-t">
                                    <h3 className="text-3xl font-semibold">
                                        Register offer
                                    </h3>
                                    <button
                                        className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                                        onClick={() => setShowModal(false)}
                                    >
                    <span className="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
                      ×
                    </span>
                                    </button>
                                </div>
                                {/*body*/}
                                <div className="flex-auto px-4 lg:px-10 py-10 pt-0">
                                    <form>
                                        <div className="relative w-full mb-3">
                                            <label
                                                className="block uppercase text-gray-700 text-xs font-bold mb-2"
                                                htmlFor="grid-password"
                                            >
                                                Amount
                                            </label>
                                            <input
                                                name="RegisterOfferAmount"
                                                type="text"
                                                className="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full ease-linear transition-all duration-150"
                                                placeholder="Amount"
                                                onChange={e => onAmountChange(e)}
                                            />
                                        </div>
                                        <div className="relative w-full mb-3">
                                            <label
                                                className="block uppercase text-gray-700 text-xs font-bold mb-2"
                                                htmlFor="grid-password"
                                            >
                                                Price
                                            </label>
                                            <input
                                                name="RegisterOfferPrice"
                                                type="text"
                                                className="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full ease-linear transition-all duration-150"
                                                placeholder="Price"
                                                onChange={e => onPriceChange(e)}
                                            />

                                        </div>
                                    </form>
                                </div>
                                {/*footer*/}
                                <div className="flex items-center justify-end p-6 border-t border-solid border-blueGray-200 rounded-b">
                                    <button
                                        className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={() => setShowModal(false)}
                                    >
                                        Cancel
                                    </button>
                                    <button
                                        className="bg-teal-500 text-white active:bg-teal-500 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={() => {
                                            // if (user.username === '') {
                                            //     showValidationErr('email', 'E-mail cannot be empty!')
                                            // }
                                            // if (!emailValidation(user.username) && user.username !== '') {
                                            //     showValidationErr('email', 'E-mail is not correct!')
                                            // }
                                            // if (user.password === '') {
                                            //     showValidationErr('password', 'Password cannot be empty!')
                                            // } else {
                                                MarketSell.mockOffer(offer)
                                                    .then(response => {
                                                   setShowModal(false)
                                                    })
                                                    .catch((error) => {
                                                        // showValidationErr('general', 'User was not found, check email and password!');
                                                        // setErrorMessage({
                                                        //     errorShow: true,
                                                        //     errorTitle: 'Wrong credentials',
                                                        //     errorMessage: 'You have entered a wrong email or password'
                                                        // });
                                                        // if (error.response.status === 401) {
                                                        //   setErrorMessage({errorShow: true, errorTitle: 'Wrong credentials', errorMessage: 'You have entered a wrong email or passwaord'})
                                                        // } else {
                                                        //   setErrorMessage({errorShow: true, errorTitle: 'Unknown error', errorMessage: 'Unknown error has occured on login'})
                                                        // }
                                                    })
                                            // }
                                        }}
                                    >
                                        Register offer
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="opacity-25 fixed inset-0 z-40 bg-black"/>
                </>
            ) : null}
        </>
    );
};

export default MarketSellDropdown;
