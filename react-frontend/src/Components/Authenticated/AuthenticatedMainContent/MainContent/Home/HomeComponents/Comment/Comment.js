// CSS
import "../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/Comment/Comment.css";

function Comment(props) {

    return (
        <div>
            <div className="comment">
                <div className="comment-thumbnail-container">
                    <img src={props.thumbnail} alt="thumbnail"></img>
                </div>
                <div className="comment-text">
                    {props.comment}
                </div>
            </div>
            <div className="subcomment">
                {props.children}
            </div>
        </div>
    );
}

export default Comment;