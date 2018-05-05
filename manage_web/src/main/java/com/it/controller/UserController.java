package com.it.controller;

import com.it.model.Tb_Order;
import com.it.model.Tb_Role;
import com.it.model.Tb_User;
import com.it.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userTest")
    public String userTest(HttpServletRequest request, HttpServletResponse response) {
        //return "redirect:/jsp/user/aaa.jsp";
        //return "forward:/index.jsp";
        //return "/jsp/user/userInfo";
        //进行垃圾回收
        System.gc();
        System.runFinalization();
        return null;
    }

    @RequestMapping("/userInfo")
    public String pageTest(HttpServletRequest request, HttpServletResponse response, Integer id, Model model) {
        Tb_User user = userService.selectUserById(id);
        model.addAttribute("user", user);
        return "/jsp/user/userInfo";
    }

    @RequestMapping("/listUserByAddr")
    public void listUserByAddr(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(userService.selectUserByAddress("上海").toString());
    }

    @RequestMapping("/listUserRole")
    public void listUserRole(HttpServletRequest request, HttpServletResponse response) {
        List<Tb_User> users = userService.selectUserRole(1);
        for (Tb_User user : users) {
            System.out.println(user.toString());
            for (Tb_Role role : user.getRoles()) {
                System.out.println(role.getRoleName() + role.getId());
            }
        }
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Tb_User user) throws IOException {
        Tb_User u = userService.selectUserByName(user.getName());
        ModelAndView view = new ModelAndView();
        if (u == null) {
            view.addObject("message", "用户不存在");
            view.setViewName("redirect:/index.jsp");
        } else if (u.getPassword().equals(DigestUtils.md5Hex(user.getName() + user.getPassword()))) {
            view.addObject("message", "密码输入错误");
            view.setViewName("redirect:/index.jsp");
        } else {
            view.setViewName("/jsp/user/home");
            request.getSession().setAttribute(u.getName(), u);
            request.getSession().setMaxInactiveInterval(1000 * 60 * 60 * 24 * 3);
        }
        return view;
    }

    //退出登录
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name) {
        request.getSession().removeAttribute(name);
        return "forward:/index.jsp";
    }

    //将图片写入到输出流中,客户端读取到图片
    @ResponseBody
    @RequestMapping("/userImg")
    public byte[] userImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resource resource = new ClassPathResource("/headIcon.jpg");
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return bytes;
    }

    /**
     * 上传文件
     * 自动创建目标文件夹
     * 如果文件夹下已经含有相同文件名的文件,不上传;不同文件名的,则上传
     *
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        //目录为,用户名/年/月/日
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String datePath = format.format(new Date());
        String path = "D:/" + datePath;
        File file = new File(path);
        //判断上传文件的保存目录是否存在
        if ((!file.isDirectory()) && (!file.exists())) {
            System.out.println("目录不存在，需要创建！");
            file.mkdirs();
        }
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
        try {
            Set<String> set = request.getFileMap().keySet();
            for (String s : set) {
                MultipartFile f = request.getFile(s);
                f.transferTo(Paths.get(path + "/" + f.getOriginalFilename()).toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start));//460M,1290mills
        return "/jsp/user/home";
    }

    /**直接在Controller中写的测试*/
    @Test
    public void ss(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:config/applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        /*List<Tb_User> users = userService.selectUserRole(1);
        System.out.println(users.toString());
        for (Tb_Role role : users.get(0).getRoles()) {
            System.out.println(role.getRoleName()+role.getId());
        }*/

        Tb_User us = userService.selectUserOrder(1);
    }

}
