package com.cdplayer.cdplayer.controller;

import com.cdplayer.cdplayer.model.History;
import com.cdplayer.cdplayer.repository.FileRepository;
import com.cdplayer.cdplayer.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("")
    public String historyHome(Model model) {

        List<History> histories = historyRepository.findAll();
        Collections.sort(histories);

        model.addAttribute("histories", histories);

        return "history";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        if (id == null) {
            model.addAttribute("history", new History());
        } else {
            History history = historyRepository.findById(id).orElse(null);
            model.addAttribute("history", history);
        }

        return "history-form";
    }

    @PostMapping("/form")
    public RedirectView greetingSubmit(@RequestParam("file") MultipartFile files,
                                       @Valid History history,
                                       Principal principal,
                                       BindingResult bindingResult) {

        String origName = files.getOriginalFilename();
        String savePath="/Users/hyukiii/documents/java_projects/cdplayer/src/main/resources/static/img/"
                + origName;

        FileOutputStream fos = null;
        try {
            byte fileData[] = files.getBytes();
            fos = new FileOutputStream(savePath);
            fos.write(fileData);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String photo = "/img/"+origName;

        if (bindingResult.hasErrors()) {
            return new RedirectView("/blog/form");
        } else {

            java.util.Date nowUtil = new java.util.Date();
            Date now = new Date(nowUtil.getTime());

            history.setRegDate(now);
            history.setPhoto(photo);

            historyRepository.save(history);

            return new RedirectView("/history");

        }

    }
}
