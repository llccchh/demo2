package com.aries.jc.lch.api.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aries.jc.common.BaseResult;

/**
 * HeartBeatRestService
 * Generated by hik-ga-archetype-api
 * Implementation is appname-provider/src/main/java/modules/provider/HeartBeatRestServiceImpl.java
 * 接口类名和路径都需要根据实际情况修改，完整url为http://localhost:17150/${context}/service/rs/heartbeat/v1/beat
 *
 * @author lichanghao6
 * @Date x
 * @since y
 */

@Path("heartbeat/v1")
@Produces(MediaType.APPLICATION_JSON)
@Deprecated
public interface HeartBeatRestService {

  /**
   * 心跳检测
   * @return
   */
  @GET
  @Path("/beat")
  @Deprecated
  BaseResult<Boolean> beat();
  
  //Api interfaces...
  //若要使用插件生成api doc，请参考：https://wiki.hikvision.com.cn/pages/viewpage.action?pageId=47018670

}
