import "../../../../../Styles/Authenticated/MainContent/Friends/Friends.css";
import "../../../../../Styles/ResponsiveGrid.css";
import FriendComponent from "./FriendsComponents/FriendComponent";

import face1 from "../../../../../SamplePictures/face1.jpg";

import { useEffect, useState } from "react";

function Friends() {

    const [friends, setFriends] = useState([])
    const [signedTo, setSignedTo] = useState([])
    const [signedBy, setSignedBy] = useState([])
    const [users, setUsers] = useState([])

    useEffect(() => {
        console.log("Loaded!")

        fetch('http://localhost:8080/friends/all', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'GET',
            credentials: "include",
        })
            .then(response => {
                console.log(response)
                return response.json()
            })
            .then(result => {
                setFriends(result["friends"])
                setSignedTo(result["signedTo"])
                setSignedBy(result["signedBy"])
                setUsers(result["users"])
                
                console.log("ResultPost: " + JSON.stringify(result))
                console.log("END:")

                console.log("Friends: " + JSON.stringify(friends))
                console.log("SignedTo: " + JSON.stringify(signedTo))
                console.log("SignedBy: " + JSON.stringify(signedBy))
                console.log("Users: " + JSON.stringify(users))
            })
            .catch(error => {
                console.error('Error: ', error);
            });
        
        console.log("End Loaded!")
    }, [])


    return (
        <div className="row friends-page-container">
            <div className="row col-xs-0 col-sm-0 col-md-5 col-lg-5 col-xl-5 col-xxl-5">

            </div>
            <div className="col-xs-12 col-sm-12 col-md-7 col-lg-7 col-xl-7 col-xxl-7">
                <div className="friends-container">

                {friends && friends.map((file, index) => (
                    <FriendComponent
                        key={index}
                        thumbnail={face1} name={file.username} 
                        residence="New York" 
                        numberOfFriends="1567" 
                        friendshipState={file.label}
                        userObject={file}/>
                ))}
                {signedTo && signedTo.map((file, index) => (
                    <FriendComponent
                        key={index}
                        thumbnail={face1} name={file.username} 
                        residence="New York" 
                        numberOfFriends="1567" 
                        friendshipState={file.label}
                        userObject={file}/>
                ))}
                {signedBy && signedBy.map((file, index) => (
                    <FriendComponent
                        key={index}
                        thumbnail={face1} name={file.username} 
                        residence="New York" 
                        numberOfFriends="1567" 
                        friendshipState={file.label}
                        userObject={file}/>
                ))}
                {users && users.map((file, index) => (
                    <FriendComponent
                        key={index}
                        thumbnail={face1} name={file.username} 
                        residence="New York" 
                        numberOfFriends="1567" 
                        friendshipState={file.label}
                        userObject={file}/>
                ))}
                </div>
            </div>
            <div className="row col-xs-0 col-sm-0 col-md-3 col-lg-3 col-xl-3 col-xxl-3">

            </div>
        </div>
    );
}

export default Friends;