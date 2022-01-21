import http from "./http-common";

class GetFilesService {
    getFiles(id) {
        return http.get("/upload/file/multi/"+id)
    }
}