import "../../../../Styles/Authenticated/Navbar/NavbarItemHolders/NavbarItemHolderMiddle.css";
import "../../../../Styles/ResponsiveGrid.css";

function NavbarItemHolderMiddle(props) {

    return (
        <div className="navbar-item-holder-middle col-xs-0 col-sm-8">
            {props.children}
        </div>
    );
}

export default NavbarItemHolderMiddle;