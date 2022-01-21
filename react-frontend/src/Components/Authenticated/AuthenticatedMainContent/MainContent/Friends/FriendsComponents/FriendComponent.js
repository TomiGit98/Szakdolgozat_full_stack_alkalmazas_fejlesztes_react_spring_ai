import "../../../../../../Styles/Authenticated/MainContent/Friends/FriendComponents/FriendComponent.css";

function FriendComponent(props) {

    const id = props.userObject.userid;

    const state = props.friendshipState;

    const sendRequest = () => {
        console.log("Request Send to: " + id)

        if(state === "Add to friend"){
            fetch('http://localhost:8080/signfriend', {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'PUT',
                credentials: "include",
                body: id,
            })
            .then(response => {
                if(response.status === 200) {
                    console.log("Status ======= 200");
                    props.friendshipState = "Signed";
                    return response;
                } else {
                    console.log("Status !====== 200");
                    return response;
                }
            })
            .catch(error => {
                console.error('Error: ', error);
            });
        } else if(state === "Signed to be friends") {
            
        } else {
            fetch('http://localhost:8080/acceptfriendrequest', {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'PUT',
                credentials: "include",
                body: id,
            })
            .then(response => {
                if(response.status === 200) {
                    console.log("bump");
                    console.log("Status ======= 200");
                    props.friendshipState = "Signed";
                    return response;
                } else {
                    console.log("Status !====== 200");
                    return response;
                }
            })
            .catch(error => {
                console.error('Error: ', error);
            });
        }
    }

    return (
        <div className="friend-component-container">
            <div className="friend-component-thumbnail-container">
                <img src={props.thumbnail} alt="friend"></img>
            </div>
            <div className="friend-component-details">
                <div className="friend-component-data">
                        <b><a href="/">{props.name}</a></b>
                </div>
                <div className="friend-component-button-container">
                    <button onClick={sendRequest}>{props.friendshipState}</button>
                    {/*<button>Delete Request</button>*/}
                </div>
            </div>
        </div>
    );
}

export default FriendComponent;