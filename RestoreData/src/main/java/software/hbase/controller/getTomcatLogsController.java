package software.hbase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class getTomcatLogsController {

    @RequestMapping(value="getTomcatLogs", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    public void getTomcatLogs(@RequestBody String json) throws Exception {

    }

}
