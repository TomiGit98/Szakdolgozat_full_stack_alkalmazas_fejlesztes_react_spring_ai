import "../../../../Styles/Authenticated/Navbar/NavbarItemHolders/NavbarItemHolderLeft.css";
import "../../../../Styles/ResponsiveGrid.css";

function NavbarItemHolderLeft(props) {

    return (
        <div className="navbar-item-holder-left col-xs-0 col-sm-2">
            {props.children}
        </div>
    );
}

export default NavbarItemHolderLeft;