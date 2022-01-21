import { ReactComponent as HomeIcon } from "../../../Icons/Navbar/home.svg";
import { ReactComponent as SettingsIcon } from "../../../Icons/Navbar/settings.svg";
import { ReactComponent as ProfileIcon } from "../../../Icons/Navbar/user.svg";
import { ReactComponent as FriendsIcon } from "../../../Icons/Navbar/friends.svg";

// import { ReactComponent as ToplistIcon } from "../../../../Icons/Navbar/podium.svg";

import { ReactComponent as LogOutIcon } from "../../../Icons/Navbar/logout.svg";

import {logout} from "../../../Utils/user-authentication.js";

import { CSSTransition } from "react-transition-group";

import "../../../Styles/Authenticated/Navbar/Navbar.css";
import { useState, useRef, useEffect } from "react";

import Cookies from 'universal-cookie';

function DropdownMenu() {
    const [activeMenu, setActiveMenu] = useState('main');
    const [menuHeight, setMenuHeight] = useState(null);
    const dropdownRef = useRef(null);

    const [cookies, setCookies] = useState(new Cookies());

    //const [cookies, setCookie, removeCookie] = useCookies()

    useEffect(() => {
        setMenuHeight(dropdownRef.current?.firstChild.offsetHeight)
    }, [])

    function calcHeight(el) {
        const height = el.offsetHeight;
        setMenuHeight(height);
    }

    function DropdownItem(props) {
        return (
            /*<a href="/" className="menu-item" onClick={(e) => {*/
            <a href={props.href} className="menu-item" onClick={(e) => {
                    //e.preventDefault();
                    props.goToMenu && setActiveMenu(props.goToMenu);
                }}>
                <span className="icon-button">{props.leftIcon}</span>
                {props.children}
                {/*<span className="icon-button">{props.rightIcon}</span>*/}
            </a>
        );
    }

    function LogoutItem(props) {
        return (
            <a href="/" className="menu-item" onClick={(e) => {
                    //removeCookie('userid')
                    cookies.remove('access_token')
                    cookies.remove('refresh_token')
                    
                    logout()
                    
                }}>
                <span className="icon-button">{props.leftIcon}</span>
                {props.children}
                {/*<span className="icon-button">{props.rightIcon}</span>*/}
            </a>
        );
    }

    return (
        <div className="dropdown" style={{ height: menuHeight+35 }} ref={dropdownRef}>

            <CSSTransition
                in={activeMenu === 'main'}
                timeout={500}
                classNames="menu-primary"
                unmountOnExit
                onEnter={calcHeight}>
                <div className="menu">
                    <DropdownItem
                        leftIcon={<ProfileIcon />}
                        href="/userprofile">
                        My Profile
                    </DropdownItem>
                    <DropdownItem
                        leftIcon={<HomeIcon />}
                        href="/">
                        Home
                    </DropdownItem>
                    <DropdownItem
                        leftIcon={<FriendsIcon />}
                        href="/friends">
                        Friends
                    </DropdownItem>
                    <DropdownItem
                        leftIcon={<SettingsIcon />}
                        goToMenu="settings">
                        Settings
                    </DropdownItem>
                    <LogoutItem
                        leftIcon={<LogOutIcon/>}>
                        Log Out
                    </LogoutItem>
                </div>
            </CSSTransition>

            <CSSTransition
                in={activeMenu === 'settings'}
                timeout={500}
                classNames="menu-secondary"
                unmountOnExit
                onEnter={calcHeight}>
                <div className="menu">
                    <DropdownItem goToMenu="main" leftIcon={<HomeIcon />}>
                        <h2>Back</h2>
                    </DropdownItem>
                    <DropdownItem href="/settings/authentication" leftIcon={<SettingsIcon />}>Authentication Settings</DropdownItem>
                </div>
            </CSSTransition>

            <CSSTransition
                in={activeMenu === 'animals'}
                timeout={500}
                classNames="menu-secondary"
                unmountOnExit
                onEnter={calcHeight}>
                <div className="menu">
                    <DropdownItem goToMenu="main" leftIcon={<SettingsIcon />}>
                        <h2>Animals</h2>
                    </DropdownItem>
                    <DropdownItem leftIcon="🦘">Kangaroo</DropdownItem>
                    <DropdownItem leftIcon="🐸">Frog</DropdownItem>
                    <DropdownItem leftIcon="🦋">Horse?</DropdownItem>
                    <DropdownItem leftIcon="🦔">Hedgehog</DropdownItem>
                </div>
            </CSSTransition>
        </div>
    );
}

export default DropdownMenu;