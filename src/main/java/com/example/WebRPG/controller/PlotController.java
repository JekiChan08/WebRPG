package com.example.WebRPG.controller;

import com.example.WebRPG.Model.Characters.Person.MainHero;
import com.example.WebRPG.Model.Item.All.Armor;
import com.example.WebRPG.Model.Item.All.Weapon;
import com.example.WebRPG.Service.MainHeroService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class PlotController {

    private final MainHeroService ms;

    @GetMapping("/set_name_form")
    public String setName(Model model, HttpSession session) {
        session.removeAttribute("hero_id");
        session.removeAttribute("enemies");
        session.removeAttribute("boss");
        session.removeAttribute("main_hero");
        model.addAttribute("new_main_hero", new MainHero());
        return "set-name-form";
    }

    @PostMapping("/set_name_form")
    public String postNewName(@ModelAttribute MainHero mainHero, Model model, HttpSession session) {
        mainHero.setWeapon(new Weapon());
        mainHero.setArmor(new Armor());
        MainHero savedHero = ms.saveMainHero(mainHero);
        session.setAttribute("hero_id", savedHero.getId());

        switch (savedHero.getRasa()) {
            case "human":
                return "redirect:/human_game/human";
            case "elf":
                return "redirect:/elf_game/elf";
            case "orc":
                return "redirect:/orc_game/orc";
            case "magical_best":
                return "redirect:/magical_best_game/magical_best";
            case "dwarf":
                return "redirect:/dwarf_game/set_stats";
            default:
                return "redirect:/human";
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/set_name_form";
    }


}
