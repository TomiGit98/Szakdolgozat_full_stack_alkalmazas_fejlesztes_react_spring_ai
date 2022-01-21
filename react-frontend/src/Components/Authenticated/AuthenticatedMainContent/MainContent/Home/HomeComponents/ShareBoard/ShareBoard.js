import "../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/ShareBoard/ShareBoard.css";
import "../../../../../../../Styles/ResponsiveGrid.css";

import { ReactComponent as PhotoUploadIcon } from "../../../../../../../Icons/ShareBoard/photo.svg"
import { ReactComponent as PostUploadIcon } from "../../../../../../../Icons/ShareBoard/post.svg"
import { ReactComponent as IdeaUploadIcon } from "../../../../../../../Icons/ShareBoard/idea.svg"

// import {ReactComponent as IdeaUpload} from "../../../../../../../Icons/Home/ShareBoard/idea.svg"
import { useState } from "react";
import ShareBoardItem from "./ShareBoardComponents/ShareBoardItem";
import UploadImage from "./ShareBoardComponents/UploadImage";
import UploadPost from "./ShareBoardComponents/UploadPost";

//////
import { useCookies } from "react-cookie";
import UploadIdea from "./ShareBoardComponents/UploadIdea";
//////

function ShareBoard(props) {

    const [postImage, setPostImage] = useState(false)
    const [postPost, setPostPost] = useState(false)
    const [postIdea, setPostIdea] = useState(false)
    const [cookie] = useCookies('userid')

    const backToStandardAfterPhoto = () => {
        setPostImage(!postImage)
    }

    const backToStandardAfterPost = () => {
        setPostPost(!postPost)
    }

    const backToStandardAfterIdea = () => {
        setPostIdea(!postIdea)
    }

    return (
        <div className="row share-board-container-root">
            <div className="share-board-container col-xs-12">
                <div>
                    {!postImage && !postPost && !postIdea && <h3>Share a moment of your life!</h3>}
                    {postImage && !postPost && !postIdea && <h3>Share a moment of your life as a photo! <p style={{ color:"#FF0000" }}>(NOT WORKING YET!!!!!)</p></h3>}
                    {postPost && !postImage && !postIdea && <h3>Share a moment of your life as a post!</h3>}
                    {postIdea && !postImage && !postPost && <h3>Share a moment of your life as an idea! <p style={{ color:"#FF0000" }}>(NOT WORKING YET!!!!!)</p></h3>}
                </div>
                <div className="col-xs-12">
                    {/*!postImage && <ShareBoardItem icon={<PhotoUpload />} name="Photo" funct={setPostImage} />*/}
                    {!postImage && !postPost && !postIdea &&
                        <div className="share-board-item-containers">
                            <ShareBoardItem icon={<PhotoUploadIcon />} name="Photo" funct={setPostImage} />
                            <ShareBoardItem icon={<PostUploadIcon />} name="Post" funct={setPostPost} />
                            <ShareBoardItem icon={<IdeaUploadIcon />} name="Idea" funct={setPostIdea} />
                        </div>
                    }
                    {postImage && !postPost && !postIdea && <UploadImage backToStandard={backToStandardAfterPhoto} cookieValue={cookie} />}
                    {postPost && !postImage && !postIdea && <UploadPost backToStandard={backToStandardAfterPost} />}
                    {postIdea && !postImage && !postPost && <UploadIdea backToStandard={backToStandardAfterIdea} />}
                </div>
            </div>
        </div>
    );
}

export default ShareBoard;