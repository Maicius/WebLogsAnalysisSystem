package software.hbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import software.hbase.Entity.ConditionQueryData;
import software.hbase.hbase.dataObject.LogAna;
import software.hbase.service.QueryService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class QueryData {
    @Autowired
    private QueryService queryService;
    @ResponseBody
    @RequestMapping(value="/queryAllData", method = RequestMethod.GET)
    public String queryAllData() throws Exception{
        String result = queryService.queryAllData();
        System.out.println(result);
        return result;
    }
    @RequestMapping(value="/conditionQuery", method = RequestMethod.POST)
    public String conditionQuery(@RequestBody String json) throws Exception{
        String ujson = "";
        String res = "";
        ConditionQueryData conditionQueryData = new ConditionQueryData();
        try {
            ujson = new String(json.getBytes("ISO-8859-1"),"utf-8");
            res = URLDecoder.decode(ujson,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "未知错误";
        }
        ObjectMapper mapper = new ObjectMapper();
        conditionQueryData = mapper.readValue(res, ConditionQueryData.class);
        System.out.println("请求数据：" + res);
        List<LogAna> list = queryService.conditionQuery(conditionQueryData);
        String jsonLog = mapper.writeValueAsString(list);
        System.out.println("返回数据：" + jsonLog);
        return jsonLog;
    }


    @RequestMapping(value="/queryData", method = RequestMethod.GET)
    public ModelAndView queryData() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value="/queryCourtData", method = RequestMethod.GET)
    public ModelAndView queryCourtData() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("queryData");
        return mv;
    }

    @RequestMapping(value="/queryDataGrid", method = RequestMethod.GET)
    public ModelAndView queryDataGrid() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dataGrid");
        return mv;
    }
}
