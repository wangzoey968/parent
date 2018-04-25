package com.it.controller;

import com.it.model.Tb_User;
import com.it.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, Tb_User user) throws IOException {
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
            request.getSession().setAttribute(u.getName(),u);
            request.getSession().setMaxInactiveInterval(1000 * 60 * 60 * 24 * 3);
        }
        return view;
    }

    //退出登录
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,String name){
        request.getSession().removeAttribute(name);
        return "forward:/index.jsp";
    }

    @RequestMapping("/userInfo")
    public String userInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id, Model model,
                           @CookieValue(value = "sessionId", required = true) String sessionId) {
        List<Tb_User> tb_Users = userService.selectUser(id);
        model.addAttribute("users", tb_Users);
        //return "redirect:/jsp/user/aaa.html";
        return "/jsp/user/userInfo";
    }

    @RequestMapping("/userCookie")
    public void userCookie(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
        List<Tb_User> user = userService.selectUser(id);
        response.addCookie(new Cookie("user", user.toString()));

    }

    //将图片写入到输出流中,客户端读取到图片
    @ResponseBody
    @RequestMapping("/userImg")
    public byte[] userImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resource resource = new ClassPathResource("/headIcon.jpg");
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return bytes;
    }

    @RequestMapping(value = "/userRole")
    public ModelAndView selectUserRole(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        List<Tb_User> tb_Users = userService.selectUser(id);
        mv.addObject("users", tb_Users);
        mv.setViewName("/jsp/user/userInfo");
        return mv;
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

}
