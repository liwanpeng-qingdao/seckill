package org.seckill.Controller;
import org.seckill.dto.ExcuteSeckill;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillState;
import org.seckill.exception.RepeatSeckill;
import org.seckill.exception.SeckillCloseException;
import org.seckill.sevice.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @param
 * Created by OUC on 2016/12/12.
 */
@Controller
@RequestMapping("/seckill")
//
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SeckillService seckillService;

    //秒杀列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("list", seckills);
        return "list";
    }

    //秒杀详细
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String seckillDetail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";//重定向到seckill/list
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";

    }

    //
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;

        Exposer exposer = null;
        try {
            exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }
    @RequestMapping(value = "/{seckillId}/{md5}/excute",method =RequestMethod.POST )
    @ResponseBody
    public SeckillResult<ExcuteSeckill> excuteSeckill(@PathVariable("seckillId")Long seckillId,
                                                      @PathVariable("md5")String md5,
                                                      @CookieValue(value = "killPhone",required = false)Long userPhone
    ){
        //springmvc avlid 可以用来验证复杂的参数
        if (userPhone==null)
        {
            return new SeckillResult<ExcuteSeckill>(false,"未注册");
        }
        try {
            ExcuteSeckill excuteSeckill=seckillService.excuteSeckill(seckillId,userPhone,md5);
            return   new SeckillResult<ExcuteSeckill>(true,excuteSeckill);
        } catch (RepeatSeckill repeatSeckill) {
            ExcuteSeckill excuteSeckill=new ExcuteSeckill(seckillId, SeckillState.REPEAT_KILL);
            return new SeckillResult<ExcuteSeckill>(true,excuteSeckill);
        } catch (SeckillCloseException e) {
            ExcuteSeckill excuteSeckill=new ExcuteSeckill(seckillId, SeckillState.END);
            return new SeckillResult<ExcuteSeckill>(true,excuteSeckill);
        }
            catch (Exception e3){
                ExcuteSeckill excuteSeckill=new ExcuteSeckill(seckillId, SeckillState.INNER_ERROR);
                return new SeckillResult<ExcuteSeckill>(true,excuteSeckill);
        }

    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
