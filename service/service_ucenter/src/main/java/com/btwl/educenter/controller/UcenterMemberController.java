package com.btwl.educenter.controller;


import com.btwl.commonutils.JwtUtils;
import com.btwl.commonutils.Ret;
import com.btwl.commonutils.ordervo.UcenterMemberOrder;
import com.btwl.educenter.entity.UcenterMember;
import com.btwl.educenter.entity.vo.RegisterVo;
import com.btwl.educenter.service.UcenterMemberService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

  @Autowired
  private UcenterMemberService memberService;

  //登录
  @PostMapping("login")
  public Ret loginUser(@RequestBody UcenterMember member) {
    //member对象封装手机号和密码
    //调用service方法实现登录
    //返回token值，使用jwt生成
    String token = memberService.login(member);
    return Ret.ok().data("token", token);
  }

  //注册
  @PostMapping("register")
  public Ret registerUser(@RequestBody RegisterVo registerVo) {
    memberService.register(registerVo);
    return Ret.ok();
  }

  //根据token获取用户信息
  @GetMapping("getMemberInfo")
  public Ret getMemberInfo(HttpServletRequest request) {
    //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
    String memberId = JwtUtils.getMemberIdByJwtToken(request);
    //查询数据库根据用户id获取用户信息
    UcenterMember member = memberService.getById(memberId);
    return Ret.ok().data("userInfo", member);
  }

  //根据用户id获取用户信息
  @PostMapping("getUserInfoOrder/{id}")
  public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
    UcenterMember member = memberService.getById(id);
    //把member对象里面值复制给UcenterMemberOrder对象
    UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
    BeanUtils.copyProperties(member, ucenterMemberOrder);
    return ucenterMemberOrder;
  }

  //查询某一天注册人数
  @GetMapping("countRegister/{day}")
  public Ret countRegister(@PathVariable String day) {
    Integer count = memberService.countRegisterDay(day);
    return Ret.ok().data("countRegister", count);
  }
}

