import '../../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/UserProfileNavbar/UserProfileNavbar.css';
import '../../../../../../../Styles/ResponsiveGrid.css';

function UserProfileNavbarItem(props) {

    return (
        <div className="user-profile-navbar-item col-xs-4" onClick={props.onClickFunction}>
            {props.text}
        </div>
    );
}

export default UserProfileNavbarItem;