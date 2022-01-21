import "../../../../Styles/Authenticated/Navbar/NavbarItems/NavbarItem.css";

function NavbarItem(props) {

    return (
        <div className="navbar-item col-xs-0 col-sm-2">
            {props.icon}
        </div>
    );
}

export default NavbarItem