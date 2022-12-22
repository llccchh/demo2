package com.hikvision.ga.xpluginmgr.modules.groupc.operate;

/**
 * @author liuyinghao5
 * @date 2022/11/21 9:46
 */

import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.operate.impl.Operate;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.hikvision.ga.xpluginmgr.modules.groupc.utils.MyPropertiesUtil.OPERATE_PROPERTIES;

/**
 * 操作链
 *
 * @author liuyinghao5
 */
public class OperateChain {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(OperateChain.class);

    @Resource
    Map<String, Operate> map = new HashMap<String, Operate>(4);




    private volatile static OperateChain operateChain;


    private OperateChain() {
        getPropertiesValue();

    }


    public static OperateChain getInstance() {
        if (operateChain == null) {
            synchronized (OperateChain.class) {
                if (operateChain == null) {
                    operateChain = new OperateChain();
                }
            }
        }
        return operateChain;
    }

    public   List<Operate> getOperateChain(List<LinkActionVO> list){

        List<Operate> operates = new LinkedList<>();
        for (LinkActionVO vo : list) {
            operates.add(map.get(vo.getName()));
        }

        return operates;
    }


    private void getPropertiesValue(){
        Properties p = OPERATE_PROPERTIES;
        LOGGER.debug("读取操作配置文件");
        p.forEach((key, value) -> {
            String operateName = (String) key;
            String operateClassName = (String) value;

            try {
                LOGGER.debug("key为"  + key);
                map.put(operateName, getClassByClassName(operateClassName));
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }



    /**
     * 根据反射去获取对应的操作类
     */
    private Operate getClassByClassName(String ClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> operate = Class.forName("com.hikvision.ga.xpluginmgr.modules.groupc.operate." + ClassName);
        Method method = operate.getMethod("getInstance");
        return (Operate) method.invoke(operate);
    }

}
