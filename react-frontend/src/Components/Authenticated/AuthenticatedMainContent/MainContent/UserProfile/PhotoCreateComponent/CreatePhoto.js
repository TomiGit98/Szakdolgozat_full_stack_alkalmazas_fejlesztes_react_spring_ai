import Webcam from "react-webcam";
import { useRef, useState } from "react";
import UploadAuthenticationPhoto from "./UploadAuthenticationPhoto";

import "../../../../../Authenticated/AuthenticatedMainContent/MainContent/Friends/Friends";

import "../../../../../../Styles/Authenticated/MainContent/UserProfile/PhotoCreateComponent/PhotoCreateComponent.css";

import UploadAuthService from "../../../../../../Services/upload-auth-file-service";

function CreatePhoto() {
    const webRef = useRef(null);
    const [img, setImg] = useState(null)//img = "httpsL;';'";

    const [imgUrl, setImgUrl] = useState(null)//img = "httpsL;';'";
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

        setImg(webRef.current.getScreenshot());
    };

    const [showVideo, setShowVideo] = useState(false);
    const [showFilePicker, setShowFilePicker] = useState(false);

    const cameraClick = () => {
        setShowFilePicker(false);
        setShowVideo(true);
        setImg(null);
    }

    const fileClick = () => {
        setShowFilePicker(true);
        setShowVideo(false);
        setImg(null);
    }

    const uploadImage = () => {

        console.log("FILE: " + img);

        UploadAuthService.upload(img, ".jpg", "", "", (event) => {
            console.log("Upload")
        })
            .then((response) => {
                setImg(null)
                return UploadAuthService.getFiles();
            })
            .then((files) => {

            })
            .catch(() => {
                
            });

        console.log("Most itt vagy!");
    }

    return (
        <div className="create-photo-page">
            <div className="option-container">
                <div className="camera" onClick={cameraClick}>Camera</div>
                <div className="file" onClick={fileClick}>File</div>
            </div>
            <div className="components">
                {showVideo &&
                    <div>
                        <div className="webcam-file-container">
                            Create Authentication Photo
                            <Webcam ref={webRef} />
                            <button onClick={() => {
                                showImage()
                            }}>
                                Create preview image
                            </button>
                            <br />
                            
                        </div>
                        {imgUrl && <div className="preview-container">
                            <img id="kep" src={imgUrl} alt="upload" />
                            <button onClick={() => {
                                uploadImage()
                            }}>
                                Save authentication photo
                            </button>
                        </div>}
                    </div>}

                {showFilePicker &&
                    <div>
                        <UploadAuthenticationPhoto />
                    </div>}
            </div>
        </div>
    );
}

export default CreatePhoto;