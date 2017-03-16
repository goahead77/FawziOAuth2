package cn.wenqi.oauth2.sparklr.controller;

import cn.wenqi.oauth2.sparklr.model.PhotoServiceUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author wenqi
 */
@RequestMapping("/me")
@Controller
public class PhotoServiceUserController {

    @ResponseBody
    @RequestMapping("")
    public PhotoServiceUser getPhotoServiceUser(Principal principal){
        return new PhotoServiceUser(principal.getName(),principal.getName());
    }
}
