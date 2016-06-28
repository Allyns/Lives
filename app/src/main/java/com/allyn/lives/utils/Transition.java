package com.allyn.lives.utils;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Transition {

    public static String getClassifyName(int typeId) {
        String typeNmae = "";
        switch (typeId) {
            case 1:
                typeNmae = "孕妇育儿";
                break;
            case 2:
                typeNmae = "美容时尚";
                break;
            case 3:
                typeNmae = "健康养生";
                break;
            case 4:
                typeNmae = "两性生活";
                break;
            case 5:
                typeNmae = "美食烹饪";
                break;
            case 6:
                typeNmae = "修养心里";
                break;
            case 7:
                typeNmae = "家庭教育";
                break;
            case 8:
                typeNmae = "幽默笑话";
                break;
            case 9:
                typeNmae = "生活杂谈";
                break;
            default:
                typeNmae = "其他";
                break;
        }
        return typeNmae;
    }
}
