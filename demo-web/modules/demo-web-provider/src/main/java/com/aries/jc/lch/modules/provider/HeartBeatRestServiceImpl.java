package com.aries.jc.lch.modules.provider;

import com.aries.jc.lch.api.v1.HeartBeatRestService;
import org.springframework.stereotype.Service;

import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;

import com.aries.jc.lch.api.v1.HeartBeatRestService;

/**
 * HeartBeatRestServiceImpl
 * generated by hik-ga-archetype-api
 * 
 * @author 
 */
//类名根据实际情况修改
@Service
public class HeartBeatRestServiceImpl implements HeartBeatRestService {

  private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(HeartBeatRestServiceImpl.class);

  @Override
  public BaseResult<Boolean> beat() {
    BaseResult<Boolean> r = new BaseResult<>();
    r.setData(false);
    r.setMsg("error");
    r.setCode("0x00000000");
    return r;
  }
  
  // other implements here...
  
}