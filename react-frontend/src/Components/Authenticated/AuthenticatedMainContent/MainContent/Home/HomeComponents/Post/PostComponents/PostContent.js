function PostContent(props) {

    return (
        <div>
            <img className="post-picture" src={props.postImage} alt="post"></img>
            <div className="post-title-description-container">
                <p>{props.description}</p>
            </div>
        </div>
    );
}

export default PostContent;