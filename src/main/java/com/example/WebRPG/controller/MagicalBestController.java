package com.example.WebRPG.controller;


import com.example.WebRPG.Model.Characters.MagicalBest.MagicalEnemies;
import com.example.WebRPG.Model.Characters.MagicalBest.PC;
import com.example.WebRPG.Model.Characters.Person.MainHero;
import com.example.WebRPG.Model.Item.MegicalBest.Abilities;
import com.example.WebRPG.Service.Impl.dwarfImpl.PcServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
@RequestMapping("/magical_best_game/")
public class MagicalBestController {
    private final PcServiceImpl magicalService;


    @GetMapping("/magical_best")
    public String magicalBest(HttpSession session, Model model) {
        MainHero mainHero = (MainHero) session.getAttribute("main_hero");
        model.addAttribute("new_magical_best", new PC());

        return "magical/magical-best";
    }
    @PostMapping("/start")
    public String start(@ModelAttribute PC pc, HttpSession session, Model model){
        String[] enemyNames = {"Раин Гослинг", "Морган Фриман", "Киану Вич", "Чак Норрис", "Николас Кейдж", "Арнольд Шварценгер", "Дуэйн Скала Джонсон", "Сильвестр Сталлоне", "Джейсон Стейтем", "Джек Воробей"};
        PC savePc = magicalService.savePC(pc);
        model.addAttribute("hero", savePc);


        return "magical/start";
    }


}
