import "../../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/UserProfilePhoto/UserProfilePhoto.css";

import "../../../../../../../Styles/ResponsiveGrid.css";

function UserPhoto(props) {

    return (
        <div className="user-photo-container col-xs-12 col-xs-6 col-sm-4 col-md-3">
            <img src={props.image} alt="user"></img>
        </div>
    );
}

export default UserPhoto;