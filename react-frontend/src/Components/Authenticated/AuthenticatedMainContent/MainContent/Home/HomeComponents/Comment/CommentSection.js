// CSS
import "../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/Comment/CommentSection.css";

// Resources
// import {ReactComponent as SendIcon} from "../../../../../../../Icons/Post/send.svg";

function CommentSection(props) {

    return (
        <div className="comment-section-root">
            <div className="comment-section">
                <textarea className="comment-section-user-input" />
                <div className="send-button-container">
                    <button>Send</button>
                </div>
            </div>
            <div className="comment-section-comments">
                {props.children}
            </div>
        </div>
    );
}

export default CommentSection;