import React, { Component } from "react";
import UploadPostService from "../../../../../../../../Services/upload-post-service";

import Cookies from 'universal-cookie';

import "../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/ShareBoard/ShareBoardComponents/UploadPost.css";

//import photoHolder from "../../../../../../../../SamplePictures/upload-post-holder.jpg";
import profile from "../../../../../../../../SamplePictures/profile.png";

//import PostHeader from "../../Post/PostComponents/PostHeader";


class UploadPost extends Component {

    constructor(props) {
        super(props);

        this.state = {
            back: props.backToStandard,
            selectedFiles: undefined,
            currentFile: undefined,
            description: "",
            progress: 0,
            message: "",
            fileInfos: [],
            imgSrc: "",
            cookies: new Cookies(),
            fileOpened: false,
        };

        this.myRef = React.createRef();
        this.myPhotoRef = React.createRef();

        this.selectFile = this.selectFile.bind(this);
        this.upload = this.upload.bind(this);
        this.clicked = this.clicked.bind(this);

        this.onWriteDescription = this.onWriteDescription.bind(this);

    }

    selectFile(event) {
        console.log("Itt vagy most: " + event.target.files[0]);
        if(event.target.files[0] !== undefined){
            this.setState({
                selectedFiles: event.target.files,
                imgSrc: URL.createObjectURL(event.target.files[0])
            });
        }
    }

    upload() {
        let currentFile = this.state.selectedFiles[0];

        this.setState({
            progress: 0,
            currentFile: currentFile,
        });

        console.log("Itt vagy!");

        UploadPostService.upload(currentFile, this.state.cookies.get('userid'), this.state.description, (event) => {
            this.setState({
                progress: Math.round((100 * event.loaded) / event.total),
            });
        })
            .then((response) => {
                console.log("hali")
                this.setState({
                    message: response.data.message,
                    imgSrc: "",
                });
                return UploadPostService.getFiles();
            })
            .then((files) => {
                console.log("hó")
                this.setState({
                    //fileInfos: file.data,
                    fileInfos: files.data,
                });
            })
            .catch(() => {
                this.setState({
                    progress: 0,
                    message: "Could not upload the file!",
                    currentFile: undefined,
                });
            });

        console.log("Most itt vagy!");
        this.setState({
            selectFiles: undefined,
        })
    }
        

    /*componentDidMount() {
        UploadService.getFiles().then((response) => {
            this.setState({
                fileInfos: response.data,
            });
        });
    }*/

    clicked() {
        this.myRef.click();
    }

    onWriteDescription(e) {
        console.log("OnWriteDescription")
        this.setState({ description: e.target.value })
    }



    render() {
        const {
            selectedFiles,
            //currentFile,
            //progress,
            //message,
            //fileInfos,
            //previewPhoto,
        } = this.state;

        return (
            <div className="col-xs-12 upload-image-container">
                <div className="col-xs-12 upload-image">

                    <div className="upload-post-container">
                        <div className="upload-post-header-image-container">
                            <img src={profile} alt="author"></img>
                        </div>
                        <div onClick={this.clicked} className="upload-post-photo-container">
                            {this.state.imgSrc === "" && <label>Please click to open a photo!</label>}
                            {this.state.imgSrc !== "" && <div>
                                <img src={this.state.imgSrc} alt="upload" />
                            </div>
                            }
                        </div>
                        <input type="file" onChange={this.selectFile} ref={elem => this.myRef = elem} />
                        <div className="upload-post-description">
                            <textarea placeholder="Please leave a deascription..." onChange={this.onWriteDescription}></textarea>
                        </div>
                    </div>

                    <div className="col-xs-10 upload-button-container">
                        <button
                            disabled={!selectedFiles}
                            onClick={this.upload}>
                            Post it
                        </button>

                        <button
                            onClick={this.state.back}>
                            Back
                        </button>
                    </div>
                </div>
                <div>
                    {/*fileInfos && fileInfos.map((file, index) => (
                        <li key={index}>
                            {file.name}
                            <img src={file.url}></img>
                        </li>
                    ))*/}
                </div>
            </div>
        );
    };
}

export default UploadPost;