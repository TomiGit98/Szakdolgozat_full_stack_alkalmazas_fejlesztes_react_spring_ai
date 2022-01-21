import React from "react";

import { ReactComponent as DropdownIcon } from "../../Icons/Navbar/dropdown.svg";
// import { ReactComponent as HomeIcon } from "../../Icons/Navbar/home.svg";
// import { ReactComponent as SearchIcon } from "../../Icons/Navbar/search.svg";
import { ReactComponent as SymbolIcon } from "../../Icons/Navbar/symbol.svg";

import AuthenticatedMainContent from "./AuthenticatedMainContent/AuthenticatedMainContent";
import Navbar from "./AuthenticatedNavbar/Navbar";
import NavItem from "./AuthenticatedNavbar/NavItem";

import "../../Styles/Authenticated/AuthenticatedPage.css";
import DropdownMenu from "./AuthenticatedNavbar/DropdownMenu";
import NavbarItemHolderLeft from "./AuthenticatedNavbar/NavbarItemHolders/NavbarItemHolderLeft";
import NavbarItemHolderMiddle from "./AuthenticatedNavbar/NavbarItemHolders/NavbarItemHolderMiddle";
import NavbarItemHolderRight from "./AuthenticatedNavbar/NavbarItemHolders/NavbarItemHolderRight";
import SearchItem from "./AuthenticatedNavbar/NavbarItems/SearchItem";
import NavbarItem from "./AuthenticatedNavbar/NavbarItems/NavbarItem";



class AuthenticatedPage extends React.Component {

    render() {

        return (
            <div className="authenticated-page-container">
                <Navbar>
                    <NavbarItemHolderLeft>
                        <NavbarItem icon={<SymbolIcon />} />
                    </NavbarItemHolderLeft>
                    <NavbarItemHolderMiddle>
                        <SearchItem />
                    </NavbarItemHolderMiddle>
                    <NavbarItemHolderRight>
                        <NavItem icon={<DropdownIcon />}>
                            <DropdownMenu />
                        </NavItem>
                    </NavbarItemHolderRight>
                </Navbar>
                <AuthenticatedMainContent />
            </div>
        );
    }
}

export default AuthenticatedPage;