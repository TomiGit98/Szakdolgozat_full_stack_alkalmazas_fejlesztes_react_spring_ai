import http from "./http-common";

class UploadPostService {
    upload(file, userid, description, onUploadProgress) {
        let formData = new FormData();
        
        formData.append("fileToUpload", file);
        formData.append("description", description)

        console.log("ITT VAGY")

        return http.post("/post/single", formData, {
        //return http.post("/upload/posts/" + userid, formData, {
            withCredentials: true,
            headers: {
                'Content-Type': 'multipart/form-data',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Origin':  '*'
            },
            data: 4
            //onUploadProgress,
        });
    }
}

export default new UploadPostService();