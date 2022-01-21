import "../../../../../Styles/ResponsiveGrid.css";
import "../../../../../Styles/Authenticated/MainContent/Home/Home.css";

import face1 from "../../../../../SamplePictures/face1.jpg";


import { useEffect, useState } from "react";
import Post from "./HomeComponents/Post/Post";
import ShareBoard from "./HomeComponents/ShareBoard/ShareBoard";

function Home() {

    const [wantShare, setWantShare] = useState(false)
    const [feeds, setFeeds] = useState([])

    const backToStandard = () => {
        setWantShare(false)
    }

    useEffect(() => {
        console.log("Loaded!")

        fetch('http://localhost:8080/feed', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'GET',
            credentials: "include",
        })
            .then(response => {
                if(response.status === 200) {
                    return response.json()
                } else {
                    return response;
                }
            })
            .then(result => {
                if(result.status !== 404) {
                    console.log("RESULT: " + JSON.stringify(result["postinfos"]))
                    setFeeds(result["postinfos"].reverse())
                }
            })
            .catch(error => {
                console.error('Error: ', error);
            });

    }, [])


    return (
        <div className="row home-page-container">
            <div className="row col-xs-0 col-sm-0 col-md-3 col-lg-3 col-xl-3 col-xxl-3">

            </div>
            <div className="row col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-9 col-xxl-9">
                {!wantShare && <ShareBoard backToStandard={backToStandard} />}

                {feeds && feeds.map((file, index) => (
                    <Post key={index}
                        faceImage={file.profilephotourl}
                        headerName={file.username}
                        headerPostDate=""
                        description={file.description}
                        postImage={file.url + file.filename}
                        likeValue="0"
                        rateValue="0" />
                ))}

            </div>
            <div className="row col-xs-0 col-sm-0 col-md-3 col-lg-3 col-xl-3 col-xxl-3">

            </div>
        </div>
    );
}

export default Home;