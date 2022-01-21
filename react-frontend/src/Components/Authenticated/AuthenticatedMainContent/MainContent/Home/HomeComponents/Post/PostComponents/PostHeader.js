import "../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/Post/PostComponents/PostHeader.css";

function PostHeader(props) {

    return (
        <div className="header-image-container">
            <div className="header-profile">
                <img src={props.faceImage} alt="author"></img>
                <div className="user-data">
                    <p className="user-name">{props.name}</p>
                    <p className="user-post-date">{props.postDate}</p>
                </div>
            </div>
        </div>
    );
}

export default PostHeader;