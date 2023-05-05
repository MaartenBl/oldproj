import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";

// components

import AdminNavbar from "components/Navbars/AdminNavbar.js";
import Sidebar from "components/Sidebar/Sidebar.js";
import HeaderStats from "components/Headers/HeaderStats.js";
import FooterAdmin from "components/Footers/FooterAdmin.js";

// views

import Dashboard from "views/admin/Dashboard.js";
import Maps from "views/admin/Maps.js";
import Settings from "views/admin/Settings.js";
import Tables from "views/admin/Tables.js";
import WindView from "../views/admin/Wind.js";
import SunView from "../views/admin/Sun.js";
import OilView from "../views/admin/Oil.js";
import GasView from "../views/admin/Gas.js";
import NuclearView from "../views/admin/Nuclear.js";
import Market from "../views/admin/Market";

export default function Admin() {
  return (
    <>
      <Sidebar />
      <div className="relative md:ml-64 bg-gray-200">
        <AdminNavbar />
        {/* Header */}
        <HeaderStats />
        <div className="px-4 md:px-10 mx-auto w-full -m-24">
          <Switch>
            <Route path="/admin/market" exact component={Market} />
            <Route path="/admin/nuclear" exact component={NuclearView} />
            <Route path="/admin/gas" exact component={GasView} />
            <Route path="/admin/oil" exact component={OilView} />
            <Route path="/admin/wind" exact component={WindView} />
            <Route path="/admin/sun" exact component={SunView} />
            <Route path="/admin/dashboard" exact component={Dashboard} />
            <Route path="/admin/maps" exact component={Maps} />
            <Route path="/admin/settings" exact component={Settings} />
            <Route path="/admin/tables" exact component={Tables} />
            <Redirect from="/admin" to="/admin/dashboard" />
          </Switch>
          <FooterAdmin />
        </div>
      </div>
    </>
  );
}
