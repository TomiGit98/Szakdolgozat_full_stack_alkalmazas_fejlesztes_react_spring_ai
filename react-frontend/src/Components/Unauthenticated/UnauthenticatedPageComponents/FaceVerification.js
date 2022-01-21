import { useRef, useState } from "react";

import Webcam from "react-webcam";

import { Redirect} from "react-router-dom";

import "../../../Styles/Unauthenticated/FaceVerification.css";

import UploadAuthService from "../../../Services/upload-verification-photo";

import { login } from "../../../Utils/user-authentication.js";

import Cookies from 'universal-cookie';

function CreatePhoto() {
    const webRef = useRef(null);
    const [img, setImg] = useState(null)//img = "httpsL;';'";
    //const [image, setImage] = useState(null)//img = "httpsL;';'";
    const [imgUrl, setImgUrl] = useState(null)//img = "httpsL;';'";

    const [cookies, setCookies] = useState(new Cookies());
    const [redirect, setRedirect] = useState(false);

    const [verificationFailed, setVerificationFailed] = useState(false);

    const [showVideo, setShowVideo] = useState(true);

    const showImage = () => {
        setImgUrl(webRef.current.getScreenshot())
        const url = webRef.current.getScreenshot();
        fetch(url)
            .then(res => res.blob())
            .then(blob => {
                var file = new File([blob], "snapshot", { type: "image/jpg" })
                setImg(file);
                console.log("IMG: " + img)
            })

        /*var image2 = new Image();
        image2.src = webRef.current.getScreenshot();
        setImage(image2);*/

        setImg(webRef.current.getScreenshot());
        //console.log(img);
    };

    const uploadImage = () => {

        //var element = document.getElementById("kep").src;
        //console.log("Hali: " + element);

        

        console.log("FILE: " + img);

        {img && UploadAuthService.upload(img, ".jpg", "", "", (event) => {
            console.log("MOst Upload!");
            console.log("Upload")
        })
            .then((response) => {
                console.log(JSON.stringify(response.data["accessToken"]))
                console.log(JSON.stringify(response.data["refreshToken"]))

                if(response.data["accessToken"] !== "-"){
                    console.log("ITT1")
                    console.log("R: " + response)
                    cookies.set('access_token', response.data["accessToken"], { path: '/' })
                    cookies.set('refresh_token', response.data["refreshToken"], { path: '/' })

                    login(response.data["userId"])
                    setVerificationFailed(false);
                    setRedirect(true);

                    return response;
                } else {

                    console.log("ITT2")
                    setVerificationFailed(true);
                    setRedirect(false);
                    return response;
                }

                
                //setImg(null)
                //return UploadAuthService.getFiles();
            })
            .catch((error) => {
                console.log("Error: " + error)
            });}

        console.log("Most itt vagy!");
    }

    const renderRedirect = () => {
        if (redirect) {
            return <Redirect to='/' push />
        }
    }

    const loginFailed = () => {
        if (verificationFailed) {
            return <label>Verification Failed</label>
        }
    }

    return (
        <div className="create-photo-page">
            <div>
                {renderRedirect()}
            </div>
            <div className="components">
                <div className="error-div">
                    {loginFailed()}
                </div>
                {showVideo &&
                    <div>
                        <div className="webcam-file-container">
                            <Webcam ref={webRef} />
                            <button onClick={() => {
                                showImage()
                                uploadImage()
                            }}>
                                Verify
                            </button>
                            <br />

                        </div>
                    </div>}
            </div>
        </div>
    );
}

export default CreatePhoto;