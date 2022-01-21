// Package imports
import { useState } from "react";


// CSS
import "../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/Post/PostComponents/PostStat.css";
import "../../../../../../../../Styles/ResponsiveGrid.css";

// Resources
import { ReactComponent as RateIcon2 } from "../../../../../../../../Icons/Post/star_outlined.svg";
//import {ReactComponent as CheerIcon} from "../../../../../../Icons/Post/cheer.svg";
import { ReactComponent as CommentIcon } from "../../../../../../../../Icons/Post/comment.svg";

import { ReactComponent as ShareIcon } from "../../../../../../../../Icons/Post/share.svg";

import face1 from "../../../../../../../../SamplePictures/face1.jpg";
import face2 from "../../../../../../../../SamplePictures/face2.png";
import face3 from "../../../../../../../../SamplePictures/face3.jpg";
import PostStatItem from "./PostStat/PostStatItem";
import CommentSection from "../../Comment/CommentSection";
import Comment from "../../Comment/Comment";

function PostStat(props) {

    const [commentSection, setCommentSection] = useState(false);

    const openCommentSection = () => {
        console.log("Comment button pushed!");
        setCommentSection(false)    // Not implemented yet
    }

    return (
        <div className="row post-stat-container-root">
            <div className="col-xs-12 post-stat-container">
                <PostStatItem classname="col-xs-4" icon={<ShareIcon />} value="Share" cssClassName="post-stat-item col-xs-4"/>
                <PostStatItem classname="col-xs-4" icon={<RateIcon2 />} value={props.rateValue} cssClassName="post-stat-rate-item col-xs-4"/>
                <PostStatItem classname="col-xs-4" icon={<CommentIcon />} value="Comment" onClick={openCommentSection} cssClassName="post-stat-item col-xs-4"/>
            </div>
            {commentSection && <CommentSection>
                    <Comment thumbnail={face2} comment="It is a comment for you to read because i do not know what to write here so i just write it here.">
                        <Comment thumbnail={face2} comment="It is a comment for you to read because i do not know what to write here so i just write it here.">
                        </Comment>
                    </Comment>
                    <Comment thumbnail={face1} comment="It is a comment for you to read because i do not know what to write here so i just write it here." />
                    <Comment thumbnail={face3} comment="It is a comment for you to read because i do not know what to write here so i just write it here." />
                    <Comment thumbnail={face2} comment="It is a comment for you to read because i do not know what to write here so i just write it here." />
                    <Comment thumbnail={face3} comment="It is a comment for you to read because i do not know what to write here so i just write it here." />
                </CommentSection>
            }
        </div>
    );
}

export default PostStat;