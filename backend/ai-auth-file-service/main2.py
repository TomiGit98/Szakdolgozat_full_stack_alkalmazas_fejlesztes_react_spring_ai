import sys
from deepface import DeepFace

import cv2

if __name__ == '__main__':
    img1 = sys.argv[1]
    db_path = sys.argv[2]

    print(f'IMG1: {img1}')
    print(f'DB_PATH: {db_path}')

    #path = 'angelina.jpg'
    #image = cv2.imread(path)

    window_name = 'image'

    #model_name = 'Facenet'
    #response = DeepFace.verify(img1_path=img1, img2_path=img2, model_name=model_name)
    #print(response)

    #db_path = 'datasets'

    #cv2.imshow(window_name, image)
    #cv2.waitKey(0)

    model_name = 'Facenet'

    detectors = ["opencv", "ssd", "mtcnn", "dlib", "retinaface"]

    #response = DeepFace.find(img_path="auth/a064c093-8768-44c1-8cf8-bed9cdc64744_snapshot.jpg", db_path='files', detector_backend=detectors[1], enforce_detection=False)
    response = DeepFace.find(img_path=img1, db_path='files', detector_backend=detectors[1], enforce_detection=False)

    print("Response: ")
    print(response.head())

    print("End")

