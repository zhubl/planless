# -*- coding:utf8 -*-

"""
Hello world to opencv2 with python

"""

import cv2


def main(o_img_path):

    img = cv2.imread(o_img_path)

    # print(type(img))  # <type 'numpy.ndarray'>

    # get width and height of img
    height, width, _ = img.shape

    cv2.imwrite("./target/part1.jpeg", img[0: height/2, 0: width/2])  # upper left
    cv2.imwrite("./target/part2.jpeg", img[0: height/2, width/2: width])
    cv2.imwrite("./target/part3.jpeg", img[height/2: height, 0: width/2])
    cv2.imwrite("./target/part4.jpeg", img[height/2: height, width/2: width])

if __name__ == '__main__':
    main("./resources/r1.jpeg")