import "../../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/UserProfileNavbar/UserProfileNavbar.css";

function UserProfileNavbar(props) {

    return (
        <div className="user-profile-navbar">
            {props.children}
        </div>
    );
}

export default UserProfileNavbar;