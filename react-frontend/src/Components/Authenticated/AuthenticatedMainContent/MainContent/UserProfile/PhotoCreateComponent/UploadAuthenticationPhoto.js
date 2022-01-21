import React, { Component } from "react";
import UploadAuthService from "../../../../../../Services/upload-auth-file-service";

import Cookies from 'universal-cookie';

import "../../../../../../Styles/Authenticated/MainContent/UserProfile/PhotoCreateComponent/UploadAuthenticationPhoto.css";


class UploadAuthenticationPhoto extends Component {

    constructor(props) {
        super(props);

        this.state = {
            showWebcam: false,
            showFileChooser: false,

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
        console.log(event.target.files)
        this.setState({
            selectedFiles: event.target.files,
            imgSrc: URL.createObjectURL(event.target.files[0])
        });
    }

    upload() {
        let currentFile = this.state.selectedFiles[0];

        this.setState({
            progress: 0,
            currentFile: currentFile,
        });

        console.log("Itt vagy!");

        UploadAuthService.upload(currentFile, "not", this.state.cookies.get('userid'), this.state.description, (event) => {
            this.setState({
                progress: Math.round((100 * event.loaded) / event.total),
            });
        })
            .then((response) => {
                this.setState({
                    message: response.data.message,
                    imgSrc: "",
                });
                return UploadAuthService.getFiles();
            })
            .then((files) => {
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
                        
                        <div onClick={this.clicked} className="upload-post-photo-container">
                            {this.state.imgSrc === "" && <label>Please click to open a photo!</label>}
                            {this.state.imgSrc !== "" && <div>
                                <img src={this.state.imgSrc} alt="upload" />
                            </div>
                            }
                        </div>
                        <input type="file" onChange={this.selectFile} ref={elem => this.myRef = elem} />
                    </div>

                    <div className="col-xs-12 upload-button-container">
                        <button
                            disabled={!selectedFiles}
                            onClick={this.upload}>
                            Upload picture
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

export default UploadAuthenticationPhoto;