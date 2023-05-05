import React from "react";

export default function FooterAdmin() {
  return (
    <>
      <footer className="block py-4">
        <div className="container mx-auto px-4">
          <hr className="mb-4 border-b-1 border-gray-300" />
          <div className="flex flex-wrap items-center md:justify-between justify-center">
            <div className="w-full md:w-4/12 px-4">
              <div className="text-sm text-gray-600 font-semibold py-1 text-center md:text-left">
                Copyright © {new Date().getFullYear()}{" "}
                <a
                  href="/"
                  className="text-gray-600 hover:text-gray-800 text-sm font-semibold py-1"
                >
                  North
                </a>
              </div>
            </div>
            <div className="w-full md:w-8/12 px-4">
              <ul className="flex flex-wrap list-none md:justify-end  justify-center">
                <li>
                  <a
                    href="/"
                    className="text-gray-700 hover:text-gray-900 text-sm font-semibold block py-1 px-3"
                  >
                    North
                  </a>
                </li>
                <li>
                  <a
                    href="https://www.fontys.nl"
                    className="text-gray-700 hover:text-gray-900 text-sm font-semibold block py-1 px-3"
                  >
                    About Us
                  </a>
                </li>
                <li>
                  <a
                    href="https://www.github.com/S63-1?ref=nr-footer-admin"
                    className="text-gray-700 hover:text-gray-900 text-sm font-semibold block py-1 px-3"
                  >
                    Github
                  </a>
                </li>
                <li>
                  <a
                    href="https://github.com/S63-1/Dashboard/blob/main/LICENSE?ref=nr-footer-admin"
                    className="text-gray-700 hover:text-gray-900 text-sm font-semibold block py-1 px-3"
                  >
                    MIT License
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </footer>
    </>
  );
}