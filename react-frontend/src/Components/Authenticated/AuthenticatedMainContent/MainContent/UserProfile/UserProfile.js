// Components

// CSS
import "../../../../../Styles/ResponsiveGrid.css";

import "../../../../../Styles/Authenticated/MainContent/UserProfile/UserProfile.css";

// Resources
import { ReactComponent as MoreIcon } from "../../../../../Icons/UserProfile/more.svg"
import { ReactComponent as ViewsIcon } from "../../../../../Icons/UserProfile/views.svg"
import { ReactComponent as FollowIcon } from "../../../../../Icons/UserProfile/follower.svg"
import { ReactComponent as FollowersIcon } from "../../../../../Icons/UserProfile/followers.svg"
import UserProfileHolder from "./UserProfileComponents/UserProfileHolder";
import UserProfileNavbar from "./UserProfileComponents/UserProfileNavbar/UserProfileNavbar";
import UserProfileNavbarItem from "./UserProfileComponents/UserProfileNavbar/UserProfileNavbarItem";
import Post from "../Home/HomeComponents/Post/Post";


import profile from "../../../../../SamplePictures/profile.png";


import UserPhoto from "./UserProfileComponents/UserProfilePhoto/UserPhoto";
import { useEffect, useState } from "react";

import Cookies from 'universal-cookie';

function UserProfile() {

    const [userDetails, setUserDetails] = useState(null);
    const [followersNum, setFollowersNum] = useState(0);

    const [listPhoto, setListPhoto] = useState(false);
    const [listPost, setListPost] = useState(false);
    const [listThropy, setListThropy] = useState(false);

    const [photoList, setPhotoList] = useState([])
    const [postList, setPostList] = useState([])
    //const [responseStatus, setResponseStatus] = useState(0)

    const cookie = new Cookies();

    const showPhoto = () => {
        setListPhoto(true)
        setListPost(false)
        setListThropy(false)
    }

    const showPost = () => {
        setListPhoto(false)
        setListPost(true)
        setListThropy(false)
    }

    const showThropy = () => {
        setListPhoto(false)
        setListPost(false)
        setListThropy(true)
    }

    useEffect(() => {
        console.log("Loaded!")

        /*fetch('http://localhost:8080/api/user', {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + cookie.get('access_token'),
                'Access-Control-Allow-Credentials': true,
                'Cookie': cookie.get('access_token'),
            },
            method: 'POST',
            credentials: "include",
        })
            .then(response => {
                setResponseStatus(response.status)
                return response.json()
            })
            .then(result => {
                if (responseStatus === 200) {
                    console.log(JSON.stringify(result))
                    console.log(result["username"])
                } else {
                    if (responseStatus === 403) {
                        console.log(result)
                        let str = result["error_message"]
                        console.log(str.substring(0, 21))
                        console.log("Expired")
                    }else {
                        console.log(responseStatus)
                    }
                }

            })
            .catch(error => {
                console.error('Error: ', error);
            });*/

        fetch('http://localhost:8080/post/all', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'GET',
            credentials: "include",
        })
            .then(response => response.json())
            .then(result => {
                if(result !== null && result !== undefined){
                    setPostList(result.reverse())
                }
                //console.log("ResultPost: " + JSON.stringify(result))
                //console.log("END:")
            })
            .catch(error => {
                console.error('Error: ', error);
            });

        fetch('http://localhost:8080/getfriends', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'GET',
            credentials: "include",
        })
            .then(response => response.json())
            .then(result => {
                setUserDetails(result)
                setFollowersNum(result.friends.length)
                console.log("UserDetails: " + JSON.stringify(result))
                console.log("UserDetails END: LENGTH" + result.friends.length)
            })
            .catch(error => {
                console.error('Error: ', error);
            });


        /*fetch('http://localhost:8086/upload/file/multi/' + cookie.get('userid'), {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'GET',
        })
            .then(response => response.json())
            .then(result => {
                setPhotoList(result)
                console.log("Result: " + JSON.stringify(result))
            })
            .catch(error => {
                console.error('Error: ', error);
            });

        fetch('http://localhost:8086/upload/post/multi/' + cookie.get('userid'), {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'GET',
        })
            .then(response => response.json())
            .then(result => {
                setPostList(result)
                console.log("ResultPost: " + JSON.stringify(result))
            })
            .catch(error => {
                console.error('Error: ', error);
            });*/




    }, [])

    return (
        <div className="row user-profile-page-container">
            <div className="col-xs-12 col-sm-10 col-xl-8">
                <UserProfileHolder
                    userphoto={profile}
                    moreIcon={<MoreIcon />}
                    viewIcon={<ViewsIcon />}
                    followIcon={<FollowIcon />}
                    followersIcon={<FollowersIcon />} followersNumber={followersNum}/>
            </div>
            <div className="col-xs-12">
                <div className="row">
                    <UserProfileNavbar>
                        <UserProfileNavbarItem text="Photo" onClickFunction={showPhoto} />
                        <UserProfileNavbarItem text="Post" onClickFunction={showPost} />
                        <UserProfileNavbarItem text="Trophy" onClickFunction={showThropy} />
                    </UserProfileNavbar>
                </div>
            </div>
            {listPhoto && <div className="user-profile-photo-container col-xs-12 col-sm-12 col-xl-12">
                <div className="outer">
                    <div className="inner">
                        {
                            photoList && photoList.map((file, index) => (
                                <UserPhoto image={file.url + file.name} key={index} />
                            ))
                        }
                    </div>
                </div>
            </div>}
            {listPost && <div className="user-profile-post-container col-xs-12 col-sm-12 col-xl-12">
                {postList && postList.map((file, index) => (
                    /*<Post key={index}
                        faceImage={face1}
                        headerName="Mark Twain"
                        headerPostDate="2019.11.04"
                        description="It is a beautiful picture about a scenic view in a forest."
                        postImage={file.url + file.filename}
                        likeValue="462"
                        rateValue="4.7" />*/
                    <Post key={index}
                        faceImage={file.profilephotourl}
                        headerName={file.username}
                        headerPostDate=""
                        description={file.description}
                        postImage={file.url + file.filename}
                        likeValue="0"
                        rateValue="0" />

                ))}

            </div>}
            {listThropy && <div className="col-xs-12 col-sm-12 col-xl-12">
                <h1>Trophy</h1>
            </div>}


        </div>
    );
}

export default UserProfile;