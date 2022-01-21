import http from "./http-common";

class UploadFileService {
    upload(file, userid, onUploadProgress) {
        let formData = new FormData();

        console.log("UploadFileService itt vagy");

        formData.append("fileToUpload", file);

        return http.post("/upload/file/single/" + userid, formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
            //data: 4
            //onUploadProgress,
            data: 4
        });
    }

    getFiles() {
        return http.get("/files");
    }
}

export default new UploadFileService();