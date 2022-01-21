import "../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/ShareBoard/ShareBoardComponents/ShareBoardItem.css";

import "../../../../../../../../Styles/ResponsiveGrid.css";

function ShareBoardItem(props) {

    return (
        <div className="share-board-item-container">
            <div onClick={props.funct}>
                {props.icon}
            </div>
            <p>{props.name}</p>
        </div>
    );

}

export default ShareBoardItem;