import http from "./http-common";

class UploadPostService {
    upload(file, blob, userid, description, onUploadProgress) {
        let formData = new FormData();
        
        formData.append("fileToUpload", file);
        formData.append("blob", blob);

        console.log(file)

        return http.post("/verify", formData, {
        //return http.post("/upload/posts/" + userid, formData, {
            withCredentials: true,
            headers: {
                'Content-Type': 'multipart/form-data',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Origin':  '*'
            },
            data: 4,
            //onUploadProgress,
        });
    }
}

export default new UploadPostService();