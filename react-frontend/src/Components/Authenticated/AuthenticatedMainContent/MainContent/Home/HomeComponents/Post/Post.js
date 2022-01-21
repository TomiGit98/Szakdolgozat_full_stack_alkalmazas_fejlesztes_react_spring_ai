import "../../../../../../../Styles/ResponsiveGrid.css";
import "../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/Post/Post.css";
import PostHeader from "./PostComponents/PostHeader";
import PostContent from "./PostComponents/PostContent";
import PostStat from "./PostComponents/PostStat";


function Post(props) {
    return (
        <div className="row post-container">
            <div className="post col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 col-xxl-12">
                <PostHeader faceImage={props.faceImage} name={props.headerName} postDate={props.headerPostDate}/>
                <PostContent postImage={props.postImage} description={props.description}/>
                <PostStat likeValue={props.likeValue} rateValue={props.rateValue} />
            </div>
        </div>
    );
}

export default Post;