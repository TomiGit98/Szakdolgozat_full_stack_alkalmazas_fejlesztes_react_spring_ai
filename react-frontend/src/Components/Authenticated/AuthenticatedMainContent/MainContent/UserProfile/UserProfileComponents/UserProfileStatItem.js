// CSS
import "../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/ProfileStatItem.css";

import "../../../../../../Styles/ResponsiveGrid.css";

function ProfileStatItem(props) {
    return (
        <div className="row">
            <div className="profile-stat-item">
                <span className="stat-item-icon">{props.icon}</span>
                <b className="stat-item-value">{props.value}</b>
            </div>
        </div>
    );
}

export default ProfileStatItem;