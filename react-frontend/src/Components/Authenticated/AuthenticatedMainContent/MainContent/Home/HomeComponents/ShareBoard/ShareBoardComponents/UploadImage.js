// import { file } from "@babel/types";
import React, { Component } from "react";
import UploadService from "../../../../../../../../Services/upload-file-service";

// import { ReactComponent as DeleteIcon } from "../../../../../../../../Icons/ShareBoard/close.svg";

import { ReactComponent as PlusIcon } from "../../../../../../../../Icons/ShareBoard/plus.svg";

import "../../../../../../../../Styles/Authenticated/MainContent/Home/HomeComponents/ShareBoard/ShareBoardComponents/UploadImage.css";

import "../../../../../../../../Styles/ResponsiveGrid.css";

// import { getId } from "../../../../../../../../Utils/user-authentication.js";

import Cookies from 'universal-cookie';

class UploadImage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            back: props.backToStandard,
            selectedFiles: undefined,
            currentFile: undefined,
            progress: 0,
            message: "",
            fileInfos: [],
            imgSrc: "",
            cookies: new Cookies(),
        };

        this.myRef = React.createRef();
        this.myPhotoRef = React.createRef();

        this.selectFile = this.selectFile.bind(this);
        this.upload = this.upload.bind(this);
        this.clicked = this.clicked.bind(this);

    }

    selectFile(event) {
        this.setState({
            selectedFiles: event.target.files,
            imgSrc: URL.createObjectURL(event.target.files[0])
        });

        console.log("File selected: " + this.selectFiles);
    }

    upload() {
        let currentFile = this.state.selectedFiles[0];

        /*this.setState({
            progress: 0,
            currentFile: currentFile,
        });*/

        console.log("Not working yet!")

        /*UploadService.upload(currentFile, this.state.cookies.get('userid'), (event) => {
            this.setState({
                progress: Math.round((100 * event.loaded) / event.total),
            });
        })
            .then((response) => {
                console.log("Response: " + response)
                console.log("Response2: " + JSON.stringify(response))
                this.setState({
                    message: response.data.message,
                    imgSrc: "",
                });
                //return UploadService.getFiles();
            })
            .then((files) => {
                console.log("Files: " + files)
                this.setState({
                    //fileInfos: file.data,
                    fileInfos: files.data,
                });
            })
            .catch(() => {
                console.log("catch")
                this.setState({
                    progress: 0,
                    message: "Could not upload the file!",
                    currentFile: undefined,
                });
            });

        this.setState({
            selectFiles: undefined,
        })*/
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
                    <div className="col-xs-12 upload-image-button-container">
                        {this.state.imgSrc === "" && <button onClick={this.clicked}>
                            <PlusIcon />
                        </button>}
                        {this.state.imgSrc !== "" && <div className="preview">
                            <img src={this.state.imgSrc} alt="upload" />
                        </div>}
                        <input type="file" onChange={this.selectFile} ref={elem => this.myRef = elem} />
                    </div>

                    {<p>{this.state.message}</p>}

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

export default UploadImage;