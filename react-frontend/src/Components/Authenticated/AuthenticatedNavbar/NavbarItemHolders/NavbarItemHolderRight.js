import "../../../../Styles/Authenticated/Navbar/NavbarItemHolders/NavbarItemHolderRight.css";
import "../../../../Styles/ResponsiveGrid.css";

function NavbarItemHolderRight(props) {

    return (
        <div className="navbar-item-holder-right col-xs-12 col-sm-2">
            {props.children}
        </div>
    );
}

export default NavbarItemHolderRight;