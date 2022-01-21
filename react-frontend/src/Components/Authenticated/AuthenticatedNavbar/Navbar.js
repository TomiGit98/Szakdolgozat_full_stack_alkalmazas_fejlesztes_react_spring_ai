import "../../../Styles/Authenticated/Navbar/Navbar.css";

import "../../../Styles/ResponsiveGrid.css";

function Navbar(props) {
    return (
        <nav className="navbar col-xs-12 col-sm-12">
            <ul className="navbar-nav">{props.children}</ul>
        </nav>
    );
}

export default Navbar;