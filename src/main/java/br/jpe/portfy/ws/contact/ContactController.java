/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jpe.portfy.ws.contact;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author joaovperin
 */
@RestController
@RequestMapping("{user}/contact")
public class ContactController {

    @GetMapping
    public ModelAndView index(@PathVariable String user) {
        Map map = new HashMap<>();
        map.put("title", "Contact - " + user);
        map.put("header", " - CONTACT -");
        map.put("currentUser", user);
        return new ModelAndView("contact/index.html", map);
    }

}
