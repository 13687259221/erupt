package xyz.erupt.upms.model.online;

import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.OperationHandler;
import xyz.erupt.core.prop.EruptProp;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YuePeng
 * date 2021/2/16 14:34
 */
@Service
public class LogOutOperationHandler implements OperationHandler<EruptOnline, Void> {

    @Resource
    private EruptProp eruptProp;

//    @Resource
//    private EruptSessionService eruptSessionService;

    @Override
    public String exec(List<EruptOnline> data, Void v, String[] param) {
//        if (eruptProp.isRedisSession()) {
//            data.forEach(it -> eruptSessionService.remove(SessionKey.USER_TOKEN + it.getToken()));
//        }
        return null;
    }

}