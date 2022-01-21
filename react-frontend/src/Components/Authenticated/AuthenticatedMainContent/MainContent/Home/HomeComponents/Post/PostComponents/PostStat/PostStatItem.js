// CSS
import "../../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/Post/PostComponents/PostStat/PostStatItem.css";

function PostStatItem(props) {

    return (
        <div className={props.cssClassName}
            onClick={props.onClick}>
                <div>{props.icon}</div>
                <b>{props.value}</b>
        </div>
    );
}

export default PostStatItem;