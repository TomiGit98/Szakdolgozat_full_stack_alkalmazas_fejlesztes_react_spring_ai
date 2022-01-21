import "../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/ProfileHolder.css";
// CSS
import "../../../../../../Styles/ResponsiveGrid.css";

// import profile from "../../../../../../SamplePictures/profile.png";
import face1 from "../../../../../../SamplePictures/face1.jpg";
import UserProfileStat from "./UserProfileStat";


function UserProfileHolder(props) {
    return (
        <div className="row">
            <div className="profile-holder">
                <div className="more-icon-container">
                    <span className="more-icon">{props.moreIcon}</span>
                </div>
                <div className="profile-picture-holder">
                    <img src={props.userphoto} alt="profile" />
                </div>
                <div className="col-xs-12 col-sm-12">
                    <UserProfileStat
                        viewIcon={props.viewIcon}
                        followIcon={props.followIcon}
                        followersIcon={props.followersIcon}
                        followersNumber={props.followersNumber} />
                </div>
            </div>
        </div>
    );
}

export default UserProfileHolder;