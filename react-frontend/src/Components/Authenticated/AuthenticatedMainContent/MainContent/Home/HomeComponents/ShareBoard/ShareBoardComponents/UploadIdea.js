import "../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/ShareBoard/ShareBoardComponents/UploadIdea.css";
import "../../../../../../../../Styles/ResponsiveGrid.css";

function UploadIdea(props) {


    return (
        <div className="col-xs-12 upload-idea-container">
            <div className="col-xs-9 upload-idea">
                <textarea></textarea>
            </div>
            <div className="col-xs-9 upload-button-container">
                <button>
                    Post it
                </button>

                <button
                    onClick={props.backToStandard} >
                    Back
                </button>
            </div>
        </div>
    );
}

export default UploadIdea;