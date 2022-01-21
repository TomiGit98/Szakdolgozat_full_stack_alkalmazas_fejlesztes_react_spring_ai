import sys
from deepface import DeepFace

if __name__ == '__main__':
    #img1_path = 'face1.jpg'
    #img2_path = 'face3.jpg'

    img1 = sys.argv[1]
    img2 = sys.argv[2]

    db_path = 'datasets'

    model_name = 'Facenet'

    response = DeepFace.verify(img1_path=img1, img2_path=img2, model_name=model_name)

    print(response)

    print("End")




