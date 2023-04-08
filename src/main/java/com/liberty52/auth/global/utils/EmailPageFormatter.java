package com.liberty52.auth.global.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailPageFormatter {

    private final static String SIGN_UP_OR_DELETE_PAGE = "\n"
            + "<div style=\"width: 100%%; height: 800px; background-color: #EEEEEE;\n"
            + "display: flex; justify-content: center; align-items: center;\">\n"
            + "    <div style=\"background-color: white; width: 600px; display: flex;\n"
            + "    justify-content: center; align-items: center; flex-direction: column;\">\n"
            + "\n"
            + "        <br>\n"
            + "        <br>\n"
            + "        <div><h3>Liberty52</h3></div>\n"
            + "\n"
            + "        <!-- 주요 문구 -->\n"
            + "        <h2>%s</h2> \n"
            + "        <br/>\n"
            + "      \n"
            + "        <!-- 서브 문구 -->\n"
            + "        <div style=\"font-weight: 600; font-size: 18px; text-align: center;\">\n"
            + "        %s\n"
            + "    </div>\n"
            + "        <br/>\n"
            + "        <div>이름 : %s</div>\n"
            + "        <br/>\n"
            + "        <div>아이디 : <a href=\"#\">%s</a></div>\n"
            + "        <br/>\n"
            + "        <div>%s : %s</div>\n"
            + "        <br/>\n"
            + "        <br/>\n"
            + "        <div style=\"width : 100%%; background-color: #EEEEEE;\n"
            + "                            height: 1px;\"></div>\n"
            + "        <div>\n"
            + "            <div style=\"background-color: black;\n"
            + "                        font-size: 20px;\n"
            + "                        color: white;\n"
            + "                        text-align: center;\n"
            + "                        padding: 20px 90px;\n"
            + "                        margin: 50px 150px;\n"
            + "                        \n"
            + "            \n"
            + "\"><a href=\"http://liberty52.com\" style=\"color:white; text-decoration: none;\">Liberty52</a></div>"
            + "        </div>\n"
            + "        <div style=\"width: 100%%; background-color: #EEEEEE\n"
            + "        ; text-align: center;\n"
            + "        padding-top: 10px;\n"
            + "        color: rgba(0,0,0,0.6);\n"
            + "        font-size: 14px;\n"
            + "        \n"
            + "        \">@Liberty52</div>\n"
            + "    </div>\n"
            + "    \n"
            + "</div>\n";

    /**
     * 
     * @param mainText 주요 문구
     * @param subText 서브 문구
     * @param name 고객 이름
     * @param id 고객 아이디
     * @param dateText 날짜 텍스트 (가입 날짜, 탈퇴 날짜)
     * @param date  날짜 LocalDate toString 값을 넣어주세요
     * @return
     */
    public static String formatSignUpOrDeletePage(String mainText, String subText, String name, String id, String dateText, String date){
        return String.format(SIGN_UP_OR_DELETE_PAGE,mainText,subText,name,id
        ,dateText,date);
    }

}
