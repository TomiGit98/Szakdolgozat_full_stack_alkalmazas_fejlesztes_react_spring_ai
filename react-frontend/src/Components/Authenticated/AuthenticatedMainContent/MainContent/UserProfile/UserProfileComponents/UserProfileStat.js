

// CSS
import "../../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfileComponents/ProfileStat.css";


import "../../../../../../Styles/ResponsiveGrid.css";

import UserProfileStatItem from "./UserProfileStatItem";

function UserProfileStat(props) {
    return (
        <div className="row">
            <div className="profile-stat-holder col-xs-12">
                <UserProfileStatItem
                    icon={props.viewIcon}
                    text="Views"
                    value="0" />
                <UserProfileStatItem
                    icon={props.followIcon}
                    text="Follow"
                    value="0" />
                <UserProfileStatItem
                    icon={props.followersIcon}
                    text="Followers"
                    value={props.followersNumber} />
            </div>
        </div>
    );
}

export default UserProfileStat;