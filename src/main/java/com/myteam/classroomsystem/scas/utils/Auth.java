package com.myteam.classroomsystem.scas.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.myteam.classroomsystem.scas.Entity.Student;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope(value = "prototype")
public class Auth {
    private Logger logger = LoggerFactory.getLogger(Auth.class);
    //登录入口
    private final String startUrl = "http://class.sise.com.cn:7001/sise/login.jsp";
    //个人信息页面入口
    private final String personalIndexUrl = "http://class.sise.com.cn:7001/sise/module/student_states/student_select_class/main.jsp";

    //这里就是全部的个人信息
    private final String personalUrl = "http://class.sise.com.cn:7001/SISEWeb/pub/course/courseViewAction.do?method=doMain&studentid=";
    private String personalId;
    private Student student;
    private WebClient webClient;
    private String status;
    private boolean statusCode = true;

    public boolean isStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public Auth() {
//        org.apache.log4j.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(org.apache.log4j.Level.FATAL);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
        this.init();
        student = new Student();
    }

    /*  *
     * @Description: 初始化爬虫
     * @Param: []
     * @return: void
     * @Author: king
     * @Date: 2020/4/11
     */
    @SneakyThrows
    public void init() {
        webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    }

    /*  *
     * @Description: 根据学号密码登录进去主页，完成授权登录
     * @Param: [auth]
     * @return: com.gargoylesoftware.htmlunit.html.HtmlPage
     * @Author: king
     * @Date: 2020/4/11
     */
    public void login(String username, String password) throws IOException {
        HtmlPage loginPage = webClient.getPage(startUrl);
        HtmlInput usernameInput = (HtmlInput) loginPage.getByXPath("//*[@id=\"username\"]").get(0);
        HtmlPasswordInput passwordInput = (HtmlPasswordInput) loginPage.getByXPath("//*[@id=\"password\"]").get(0);
        HtmlButtonInput submintInput = (HtmlButtonInput) loginPage.getByXPath("//*[@id=\"Submit\"]").get(0);
        usernameInput.setValueAttribute(username);
        passwordInput.setValueAttribute(password);
        HtmlPage login = submintInput.click();
        HtmlTitle font = (HtmlTitle) login.getByXPath("/html/head/title").get(0);
        String tip = font.asText();
        if (tip.equals("系统错误提示页面")) {
            statusCode = false;
            status = "账号或密码错误";
        }
    }

    /*  *
     * @Description: 解析学生个人信息唯一标识
     * @Param: []
     * @return: void
     * @Author: king
     * @Date: 2020/4/11
     */
    public void parsePersonalId() throws IOException {
        if (!statusCode) return;
        HtmlPage personalPage = webClient.getPage(personalIndexUrl);
        //解析个人信息的标识
        HtmlTableCell personalInfoTd = (HtmlTableCell) personalPage.getByXPath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td").get(0);
        String td = personalInfoTd.getAttribute("onclick");
        this.personalId = td.substring(td.length() - 13, td.length() - 1);
    }

    /*  *
     * @Description: 拿到所有想要的数据
     * @Param: []
     * @return: void
     * @Author: king
     * @Date: 2020/4/11
     */
    public void findAllInfo() throws IOException {
        if (!statusCode) return;
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage personalIndexPage = webClient.getPage(this.personalUrl + this.personalId);
        //学号
        HtmlDivision sno = (HtmlDivision) personalIndexPage.getByXPath("/html/body/form/table[3]/tbody/tr/td/table/tbody/tr[1]/td[2]/div").get(0);
        //姓名
        HtmlDivision name = (HtmlDivision) personalIndexPage.getByXPath("/html/body/form/table[3]/tbody/tr/td/table/tbody/tr[1]/td[4]/div").get(0);
        //班级
        HtmlTableCell _class = (HtmlTableCell) personalIndexPage.getByXPath("/html/body/form/table[3]/tbody/tr/td/table/tbody/tr[3]/td[2]").get(0);
        //专业
        HtmlDivision profession = (HtmlDivision) personalIndexPage.getByXPath("/html/body/form/table[3]/tbody/tr/td/table/tbody/tr[1]/td[8]/div").get(0);
        student.setSid(sno.asText());
        student.setName(name.asText());
        student.setMy_class(_class.asText().substring(0, 9));
        student.setDepartment(profession.asText().substring(0, 4));
        webClient.close();
    }

    /*  *
     * @Description: 开始所有工作
     * @Param: [username, password]
     * @return: void
     * @Author: king
     * @Date: 2020/4/11
     */
    public Student start(String username, String password) throws IOException {
        this.login(username, password);
        this.parsePersonalId();
        this.findAllInfo();
        return student;
    }

    public static void main(String[] args) throws IOException {
        Auth auth = new Auth();
//        auth.login("1740129222", "13465");
        Student student = auth.start("1740129222", "17440981109034");
        System.out.println(student);
    }

}
