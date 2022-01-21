import "../../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/UserPhotoHolder.css";
import "../../../../../../../Styles/ResponsiveGrid.css";

function UserPhotoHolder(props) {

    return (
        <div className="user-photo-holder">
            <div className="col-xs-10">
                {props.children}
            </div>
        </div>
    );
}

export default UserPhotoHolder;