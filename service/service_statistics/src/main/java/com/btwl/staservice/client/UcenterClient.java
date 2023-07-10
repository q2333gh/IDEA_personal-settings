package com.btwl.staservice.client;

import com.btwl.commonutils.Ret;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

  //查询某一天注册人数
  @GetMapping("/educenter/member/countRegister/{day}")
  public Ret countRegister(@PathVariable("day") String day);
}
